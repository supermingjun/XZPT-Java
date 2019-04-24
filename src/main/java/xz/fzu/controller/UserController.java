package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.service.IVerificationCodeService;
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
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IVerificationCodeService iValidateCodeService;

    @Autowired
    public UserController(IUserService iUserService, IVerificationCodeService iValidateCodeService) {
        this.iUserService = iUserService;
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestBody User user, @RequestParam int code) {

        user.setPasswd(SHA.encrypt(user.getPasswd()));
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            if (!iValidateCodeService.validateCode(user.getEmail(), code)) {
                throw new ValidateErrorException("验证码错误");
            }
            // 此处传引用，自动更新user的id。
            String token = iUserService.register(user);
            returnMap.put(Constants.resultObject, token);
        } catch (ValidateErrorException e) {

            resultPutInformation(returnMap, Constants.validateCodeError, e.getMessage());
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {

            resultPutInformation(returnMap, Constants.databaseConnectError, e.getMessage());
        } catch (NullPointerException e) {

            resultPutInformation(returnMap, Constants.noVerfcationCode, e.getMessage());
        } catch (Exception e) {

            resultPutInformation(returnMap, Constants.accountUsed, e.getMessage());
        }
        return returnMap;
    }

    class ValidateErrorException extends RuntimeException {
        ValidateErrorException(String value) {
            super(value);
        }
    }

    ;
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
            String userId = iUserService.verifyToken(token);
            if (userId != null) {
                returnMap.put(Constants.resultObject, token);
                // TODO 这里需要添加重新签证，但是要重新获取账号密码太浪费资源了。
            }
        } catch (Exception e) {
            resultPutInformation(returnMap, Constants.TOKEN_EXPIRED, e.getMessage());
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
            String token = TokenUtil.createToken(user.getUserId(), user.getPasswd());
            returnMap.put(Constants.resultObject, token);
            //TODO 怎么让原来的token失效
        } catch (Exception e) {
            resultPutInformation(returnMap, Constants.PASSWD_FAULT, e.getMessage());
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
    @RequestMapping(value = "/*", method = RequestMethod.POST)
    @ResponseBody
    public String other() {
        return "Nothing! URL error!";
    }

    /**
     * @param user
     * @return void
     * @author Murphy
     * @date 2019/4/23 0:05
     * @description 加密用户密码
     */
    private void encryPasswd(User user) {
    }

    /**
     * @param token
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 2:06
     * @description 根据用户token获得用户
     */
    @RequestMapping(value = "/getuserbytoken", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getUser(@RequestParam String token) {
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            User user = iUserService.selectUserByToken(token);
            user.setPasswd(null);
            user.setToken(null);
            returnMap.put(Constants.resultObject, user);
        } catch (Exception e) {
            resultPutInformation(returnMap, Constants.TOKEN_EXPIRED, e.getMessage());
        }
        return returnMap;
    }

    private void resultPutInformation(Map<Object, Object> map, Integer code, String msg) {
        if (code != null) {
            map.put(Constants.resultCode, code);
        }
        if (msg != null) {
            map.put(Constants.resultMsg, msg);
        }
    }
}
