package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.service.IValidateCodeService;
import xz.fzu.util.Constants;
import xz.fzu.util.SHA;
import xz.fzu.util.TokenUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/19 23:19
 */
//    @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IValidateCodeService iValidateCodeService;
    @Autowired
    public UserController(IUserService iUserService, IValidateCodeService iValidateCodeService) {
        this.iUserService =iUserService;
        this.iValidateCodeService = iValidateCodeService;
    }

    /**
     * @param user
     * @param code 验证码
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 3:40
     * @description 注册方法 //TODO code需要修改
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)@ResponseBody
    public Map register(@RequestBody User user, @RequestParam int code) {
        user.setPasswd(SHA.encrypt(user.getPasswd()));
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            if (!iValidateCodeService.validateCode(user.getEmail(), code)) {
                throw new RuntimeException("验证码错误");
            }
            iUserService.register(user);
            String token = TokenUtil.sign(user.getUserId(), user.getPasswd());
            returnMap.put(Constants.resultObject, token);
        } catch (NullPointerException e) {
            returnMap.put(Constants.resultCode, Constants.validateCodeError);
            returnMap.put(Constants.resultMsg, e.getMessage());
        } catch (Exception e) {
            returnMap.put(Constants.resultCode, Constants.accountUsed);
            returnMap.put(Constants.resultMsg, e.getMessage());
        }
        return returnMap;
    }

    /**
     * @param token
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 20:53
     * @description 验证token有效
     */
    @RequestMapping(value = "/vertifytoken", method = RequestMethod.POST)
    @ResponseBody
    public Map loginWithToken(@RequestParam String token) {
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            if (TokenUtil.verify(token)) ;
            //TODO  更新Token吗？
            returnMap.put(Constants.resultObject, token);
        } catch (Exception e) {
            returnMap.put(Constants.resultCode, Constants.TOKEN_EXPIRED);
            returnMap.put(Constants.resultMsg, e.getMessage());
        }
        return returnMap;
    }

    /**
     * @param user
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 21:12
     * @description 使用账户密码登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user) {
        user.setPasswd(SHA.encrypt(user.getPasswd()));
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            iUserService.vertifyUser(user);
            String token = TokenUtil.sign(user.getUserId(), user.getPasswd());
            returnMap.put(Constants.resultObject, token);
        } catch (Exception e) {
            returnMap.put(Constants.resultCode, Constants.PASSWD_FAULT);
            returnMap.put(Constants.resultMsg, e.getMessage());
        }
        return returnMap;
    }
    /**
     * @param
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/20 3:40
     * @description 测试方法
     */
    @RequestMapping(value = "/*",method = RequestMethod.POST)@ResponseBody
    public String other(String args) {
        return null;
    }
}
