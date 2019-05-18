package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;

/**
 * 用户相关的控制器
 *
 * @author Murphy
 * @date 2019/4/19 23:19
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IVerificationCodeService iVerificationCodeService;

    @Autowired
    public UserController(IUserService iUserService, IVerificationCodeService iValidateCodeService) {
        this.iUserService = iUserService;
        this.iVerificationCodeService = iValidateCodeService;
    }

    /**
     * @param user 用户实例
     * @param code 验证码
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 3:40
     * @description 注册方法 //TODO code需要修改
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData register(@RequestBody User user, @RequestParam int code) throws ValidationException, NoVerificationCodeException, AccountUsedException {

        ResponseData<String> responseData = new ResponseData<>();
        iVerificationCodeService.verifyCode(user.getEmail(), code);
        String token = iUserService.register(user);
        responseData.setResultObject(token);

        return responseData;
    }

    /**
     * @param token token
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 20:53
     * @description 验证token有效
     */
    @RequestMapping(value = "/vertifytoken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> loginWithToken(@RequestParam String token) throws TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<>();
        iUserService.verifyToken(token);
        responseData.setResultObject(token);
        return responseData;
    }

    /**
     * @param user 用户实例
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 21:12
     * @description 使用账户密码登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> login(@RequestBody User user) throws PasswordErrorException {

        ResponseData<String> responseData = new ResponseData<>();
        String token = iUserService.verifyUser(user);
        responseData.setResultObject(token);
        return responseData;
    }


    /**
     * @param token token
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 2:06
     * @description 根据用户token获得用户
     */
    @RequestMapping(value = "/getuserbytoken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<User> getUser(@RequestParam String token) throws TokenExpiredException {

        ResponseData<User> responseData = new ResponseData<>();
        User user = iUserService.selectUserByToken(token);
        responseData.setResultObject(user);
        return responseData;
    }

    /**
     * @param token     token
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:34
     * @description 更新密码
     */
    @RequestMapping(value = "/updatepasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws PasswordErrorException, TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<>();
        String newToken = iUserService.updatePasswd(token, oldPasswd, newPasswd);
        responseData.setResultObject(newToken);
        return responseData;
    }

    /**
     * @param user  用户实例
     * @param token token
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:18
     * @description 更新信息
     */
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> updateInfo(@RequestBody User user, @RequestParam String token) throws TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<>();
        iUserService.updateInfo(user, token);
        return responseData;
    }

    /**
     * @param code   验证码
     * @param passwd 密码
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:24
     * @description 重置用户密码
     */
    @RequestMapping(value = "/resetpasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) throws ValidationException, NoVerificationCodeException {

        ResponseData<String> responseData = new ResponseData<>();
        iVerificationCodeService.verifyCode(email, code);
        iUserService.resetPasswd(email, passwd);
        return responseData;
    }

}
