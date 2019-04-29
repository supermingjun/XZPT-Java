package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.Recruitment;
import xz.fzu.model.Resume;
import xz.fzu.model.User;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IResumeService;
import xz.fzu.service.IUserService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.List;

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
    private IVerificationCodeService iVerificationCodeService;
    @Resource
    private IRecruitmentService iRecruitmentService;

    @Resource
    IResumeService iResumeService;
    @Autowired
    public UserController(IUserService iUserService, IVerificationCodeService iValidateCodeService) {
        this.iUserService = iUserService;
        this.iVerificationCodeService = iValidateCodeService;
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
    public ResponseData register(@RequestBody User user, @RequestParam int code) throws ValidationExceprion, NoVerfcationCodeException, AccountUsedException {

        ResponseData<String> responseData = new ResponseData<String>();
        iVerificationCodeService.verifyCode(user.getEmail(), code);
        String token = iUserService.register(user);
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
    public ResponseData<String> loginWithToken(@RequestParam String token) throws TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<String>();
        iUserService.verifyToken(token);
        responseData.setResultObject(token);
        return responseData;
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
    public ResponseData<String> login(@RequestBody User user) throws PasswordErrorException {

        ResponseData<String> responseData = new ResponseData<String>();
        String token = iUserService.verifyUser(user);
        responseData.setResultObject(token);
        return responseData;
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
    public ResponseData<User> getUser(@RequestParam String token) throws TokenExpiredException {

        ResponseData<User> responseData = new ResponseData<User>();
        User user = iUserService.selectUserByToken(token);
        responseData.setResultObject(user);
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
    public ResponseData<String> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) throws PasswordErrorException, TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<String>();
        String newToken = iUserService.updatePasswd(token, oldPasswd, newPasswd);
        responseData.setResultObject(newToken);
        return responseData;
    }

    /**
     * @param user
     * @param token
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:18
     * @description 更新信息
     */
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<String> updateInfo(@RequestBody User user, @RequestParam String token) throws TokenExpiredException {

        ResponseData<String> responseData = new ResponseData<String>();
        iUserService.updateInfo(user, token);
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
    public ResponseData<String> resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) throws ValidationExceprion, NoVerfcationCodeException {

        ResponseData<String> responseData = new ResponseData<String>();
        iVerificationCodeService.verifyCode(email, code);
        iUserService.resetPasswd(email, passwd);
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
    public ResponseData getListRecruitmentByCompanyId(@RequestParam String token, @RequestParam long recruitmentId) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<Recruitment> responseData = new ResponseData<Recruitment>();
        iUserService.verifyToken(token);
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
     * @description 按id获得指定公司所有招聘信息
     */
    @RequestMapping(value = "/getlistrecruitmentbycompanyid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<List<Recruitment>> getListRecruitmentByCompanyId(@RequestParam String token, @RequestParam String companyId) throws InstanceNotExistException, TokenExpiredException, UserNotFoundException {

        ResponseData<List<Recruitment>> responseData = new ResponseData<List<Recruitment>>();
        iUserService.verifyToken(token);
        List<Recruitment> recruitmentList = iRecruitmentService.getListRecruitmentByCompanyId(companyId);
        responseData.setResultObject(recruitmentList);

        return responseData;
    }

    /**
     * @param token
     * @param keyWord
     * @return xz.fzu.vo.ResponseData<java.util.List < xz.fzu.model.Recruitment>>
     * @author Murphy
     * @date 2019/4/29 21:34
     * @description 搜索招聘信息
     */
    @RequestMapping(value = "/searchrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<PageData> searchRecruitment(@RequestParam String token, @RequestParam String keyWord, @RequestBody PageData<Recruitment> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<PageData> responseData = new ResponseData<>();
        iUserService.verifyToken(token);
        List<Recruitment> recruitmentList = iRecruitmentService.getListRecruitmentByKeyWord(keyWord, pageData);
        pageData.setContentList(recruitmentList);
        responseData.setResultObject(pageData);

        return responseData;
    }


    /**
     * @param token
     * @param pageData
     * @return xz.fzu.vo.ResponseData<xz.fzu.vo.PageData>
     * @author Murphy
     * @date 2019/4/30 1:10
     * @description 查看本人所有的简历
     */
    @RequestMapping(value = "/getlistresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<PageData> getListResume(@RequestParam String token, @RequestBody PageData<Resume> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<PageData> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        List<Resume> recruitmentList = iResumeService.getListResume(userId, pageData);
        pageData.setContentList(recruitmentList);
        responseData.setResultObject(pageData);

        return responseData;
    }

    /**
     * @param token
     * @param resume
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 1:13
     * @description 更新简历
     */
    @RequestMapping(value = "/updateresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Resume> updateResume(@RequestParam String token, @RequestBody Resume resume) throws InstanceNotExistException, TokenExpiredException, EvilIntentions {

        ResponseData<Resume> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        iResumeService.updateResume(userId, resume);

        return responseData;
    }

    @RequestMapping(value = "/insertresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insertResume(@RequestParam String token, @RequestBody Resume resume) throws InstanceNotExistException, TokenExpiredException, EvilIntentions {

        ResponseData responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        iResumeService.insertResume(userId, resume);

        return responseData;
    }

    /**
     * @param token
     * @param resumeId
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 1:14
     * @description 删除简历
     */
    @RequestMapping(value = "/deleteresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Resume> deleteResume(@RequestParam String token, @RequestParam int resumeId) throws InstanceNotExistException, TokenExpiredException, EvilIntentions {

        ResponseData<Resume> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token); // TODO 安全认证
        iResumeService.deleteResume(resumeId);

        return responseData;
    }


    /**
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/29 21:53
     * @description 推荐接口
     */
    @RequestMapping(value = "/getrecommend", method = RequestMethod.POST)
    public ResponseData getRecommend(String token) {

        return null;
    }
}
