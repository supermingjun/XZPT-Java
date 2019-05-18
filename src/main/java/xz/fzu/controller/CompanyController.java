package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;

/**
 * 企业相关的控制器
 *
 * @author Murphy
 * @date 2019/4/25 20:13
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Resource
    ICompanyService iCompanyService;

    @Resource
    IVerificationCodeService iVerificationCodeService;

    @Resource
    IRecruitmentService iRecruitmentService;

    @Autowired
    public CompanyController(ICompanyService iCompanyService, IVerificationCodeService iVerificationCodeService, IRecruitmentService iRecruitmentService) {

        this.iCompanyService = iCompanyService;
        this.iVerificationCodeService = iVerificationCodeService;
        this.iRecruitmentService = iRecruitmentService;
    }

    /**
     * @param company 企业实例
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/25 20:31
     * @description 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseData<String> login(@RequestBody Company company) throws PasswordErrorException {

        String email = company.getEmail();
        String passwd = company.getPasswd();
        ResponseData<String> responseData = new ResponseData<>();
        String token = iCompanyService.login(email, passwd);
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
    public ResponseData loginWithToken(@RequestParam String token) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iCompanyService.verifyToken(token);

        return responseData;
    }

    /**
     * @param company 企业实例
     * @param code    验证码
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/25 20:37
     * @description 注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseData register(@RequestBody Company company, @RequestParam int code) throws ValidationException, NoVerificationCodeException {

        ResponseData responseData = new ResponseData();
        iCompanyService.register(company, code);

        return responseData;
    }


    /**
     * @param token toke
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 2:06
     * @description 根据企业token获得企业
     */
    @RequestMapping(value = "/getcompanybytoken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Company> getUser(@RequestParam String token) throws UserNotFoundException, TokenExpiredException {

        ResponseData<Company> responseData = new ResponseData<>();
        Company company = iCompanyService.getInfoByToken(token);
        company.setPasswd(null);
        company.setToken(null);
        responseData.setResultObject(company);

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
    public ResponseData<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws TokenExpiredException, PasswordErrorException {

        ResponseData<String> responseData = new ResponseData<>();
        String newToken = iCompanyService.updatePasswd(token, oldPasswd, newPasswd);
        responseData.setResultObject(newToken);

        return responseData;
    }


    /**
     * @param company 企业实例
     * @param token   token
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:18
     * @description 更新信息
     */
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateInfo(@RequestBody Company company, @RequestParam String token) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iCompanyService.updateInfoByToken(company, token);

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
    public ResponseData resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) throws ValidationException, NoVerificationCodeException, TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iVerificationCodeService.verifyCode(email, code);
        iCompanyService.resetPasswd(email, passwd);

        return responseData;
    }

}
