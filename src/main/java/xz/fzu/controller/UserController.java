package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseVO;

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

    @Resource
    ApplicationContext applicationContext;
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
    public ResponseVO register(@RequestBody User user, @RequestParam int code) throws ValidationException, NoVerificationCodeException, AccountUsedException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        iVerificationCodeService.verifyCode(user.getEmail(), code);
        String token = iUserService.register(user);
        responseVO.setResultObject(token);
        // 初始化推荐和热度算法
        try {
            RecommendController recommendController = applicationContext.getBean(RecommendController.class);
            HotSpotController hotSpotController = applicationContext.getBean(HotSpotController.class);
            recommendController.runThread();
            hotSpotController.runThread();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseVO;
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
    public ResponseVO<String> loginWithToken(@RequestParam String token) throws TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        responseVO.setResultObject(token);
        return responseVO;
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
    public ResponseVO<String> login(@RequestBody User user) throws PasswordErrorException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String token = iUserService.verifyUser(user);
        responseVO.setResultObject(token);
        return responseVO;
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
    public ResponseVO<User> getUser(@RequestParam String token) throws TokenExpiredException {

        ResponseVO<User> responseVO = new ResponseVO<>();
        User user = iUserService.selectUserByToken(token);
        responseVO.setResultObject(user);
        return responseVO;
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
    public ResponseVO<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws PasswordErrorException, TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String newToken = iUserService.updatePasswd(token, oldPasswd, newPasswd);
        responseVO.setResultObject(newToken);
        return responseVO;
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
    public ResponseVO<String> updateInfo(@RequestBody User user, @RequestParam String token) throws TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        iUserService.updateInfo(user, token);
        return responseVO;
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
    public ResponseVO<String> resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) throws ValidationException, NoVerificationCodeException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        iVerificationCodeService.verifyCode(email, code);
        iUserService.resetPasswd(email, passwd);
        return responseVO;
    }

}
