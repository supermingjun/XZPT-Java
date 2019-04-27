package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.model.Recruitment;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
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
     * @param company
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
        iCompanyService.verifyToken(token);

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
    public ResponseData<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws TokenExpiredException, PasswordErrorException {

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

    /**
     * @param recruitment
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/27 11:06
     * @description 发布招聘信息
     */
    @RequestMapping(value = "/releaserecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData releaseRecruitment(@RequestBody Recruitment recruitment, @RequestParam String token) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iCompanyService.verifyToken(token);
        iRecruitmentService.insertRecruitment(recruitment);

        return responseData;
    }

    /**
     * @param token
     * @param recruitmentId
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定的招聘信息
     */
    @RequestMapping(value = "/getrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getRecruitment(@RequestParam String token, @RequestParam long recruitmentId) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<Recruitment> responseData = new ResponseData<Recruitment>();
        iCompanyService.verifyToken(token);
        Recruitment recruitment = iRecruitmentService.getRecruitmentById(recruitmentId);
        responseData.setResultObject(recruitment);

        return responseData;
    }


    /**
     * @param token
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定的所有招聘信息
     */
    @RequestMapping(value = "/getlistrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<List<Recruitment>> getRecruitment(@RequestParam String token) throws InstanceNotExistException, TokenExpiredException, UserNotFoundException {

        ResponseData<List<Recruitment>> responseData = new ResponseData<List<Recruitment>>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInstaceByToken(token);
        List<Recruitment> recruitmentList = iRecruitmentService.getListRecruitmentByCompanyId(company.getCompanyId());
        responseData.setResultObject(recruitmentList);

        return responseData;
    }


    /**
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 修改指定的招聘信息
     */
    @RequestMapping(value = "/updaterecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateRecruitment(@RequestParam String token, @RequestBody Recruitment recruitment) throws InstanceNotExistException, TokenExpiredException, UserNotFoundException, EvilIntentions {

        ResponseData responseData = new ResponseData<>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInstaceByToken(token);
        iRecruitmentService.updateRecruitment(recruitment, company.getCompanyId());

        return responseData;
    }


    /**
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 删除指定的招聘信息
     */
    @RequestMapping(value = "/deleterecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteRecruitment(@RequestParam String token, @RequestBody long recruitmentId) throws InstanceNotExistException, TokenExpiredException, UserNotFoundException, EvilIntentions {

        ResponseData responseData = new ResponseData<>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInstaceByToken(token);
        iRecruitmentService.deleteRecruitment(recruitmentId, company.getCompanyId());

        return responseData;
    }
}
