package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseData;

/**
 * @author Murphy
 * @date 2019/4/25 20:13
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    ICompanyService iCompanyService;

    @Autowired
    IVerificationCodeService iVerificationCodeService;

    @Autowired
    public CompanyController(ICompanyService iCompanyService, IVerificationCodeService iVerificationCodeService) {
        this.iCompanyService = iCompanyService;
        this.iVerificationCodeService = iVerificationCodeService;
    }

    /**
     * @param email
     * @param passwd
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/25 20:31
     * @description 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseData<String> login(@RequestParam String email, @RequestParam String passwd) throws PasswordErrorException {

        ResponseData<String> responseData = new ResponseData<String>();
        String token = iCompanyService.login(email, passwd);
        responseData.setResultObject(token);
        return responseData;
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
    public ResponseData loginWithToken(@RequestParam String token) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iCompanyService.loginWithToken(token);
        return responseData;
    }

    /**
     * @param company
     * @param code
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/25 20:37
     * @description 注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseData register(@RequestBody Company company, @RequestParam int code) throws ValidationExceprion, NoVerfcationCodeException {

        ResponseData responseData = new ResponseData();
        iCompanyService.register(company, code);
        return responseData;
    }


    /**
     * @param token
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 2:06
     * @description 根据企业token获得企业
     */
    @RequestMapping(value = "/getcompanybytoken", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Company> getUser(@RequestParam String token) throws UserNotFoundException {

        ResponseData<Company> responseData = new ResponseData<Company>();
        Company company = iCompanyService.getInfo(token);
        company.setPasswd(null);
        company.setToken(null);
        responseData.setResultObject(company);
        return responseData;
    }

    /**
     * @param token
     * @param oldPasswd
     * @param newPasswd
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:34
     * @description 更新密码
     */
    @RequestMapping(value = "/updatepasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<String>();
        String newToken = iCompanyService.updatePasswd(token, oldPasswd, newPasswd);
        responseData.setResultObject(newToken);
        return responseData;
    }


    /**
     * @param company
     * @param token
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
     * @param code
     * @param passwd
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:24
     * @description 重置用户密码
     */
    @RequestMapping(value = "/resetpasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) throws ValidationExceprion, NoVerfcationCodeException, TokenExpiredException {
        ResponseData responseData = new ResponseData();
        iVerificationCodeService.validateCode(email, code);
        iCompanyService.resetPasswd(email, passwd);
        return responseData;
    }
}
