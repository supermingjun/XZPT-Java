package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.NoVerfcationCodeException;
import xz.fzu.exception.PasswordErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.ValidationExceprion;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.Constants;

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

        Map<Object, Object> returnMap = initResultMap();
        try {
            iValidateCodeService.validateCode(user.getEmail(), code);
            String token = iUserService.register(user);
            returnMap.put(Constants.resultObject, token);
        } catch (ValidationExceprion | NoVerfcationCodeException e) {

            resultPutInformation(returnMap, e.getErrorCode(), e.getMessage());
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {

            resultPutInformation(returnMap, Constants.databaseConnectError, e.getMessage());
        } catch (Exception e) {

            resultPutInformation(returnMap, Constants.accountUsed, e.getMessage());
        }
        return returnMap;
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

        Map<Object, Object> returnMap = initResultMap();
        try {
            iUserService.verifyToken(token);
        } catch (TokenExpiredException e) {
            resultPutInformation(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            resultPutInformation(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
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

        Map<Object, Object> returnMap = initResultMap();
        try {
            String token = iUserService.vertifyUser(user);
            returnMap.put(Constants.resultObject, token);
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
        return "Nothing! URL errorCode!";
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
        Map<Object, Object> returnMap = initResultMap();
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

    @RequestMapping(value = "/updatepasswd", method = RequestMethod.POST)
    @ResponseBody
    private Map<Object, Object> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) {

        Map<Object, Object> returnMap = initResultMap();
        try {
            String newToken = iUserService.updatePasswd(token, oldPasswd, newPasswd);
            returnMap.put(Constants.resultObject, newToken);
        } catch (PasswordErrorException | TokenExpiredException e) {
            resultPutInformation(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            resultPutInformation(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
    }

    private Map<Object, Object> initResultMap() {

        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        return returnMap;
    }
}
