package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/25 20:13
 */
@RestController
@RequestMapping(value = "/company/")
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
    public Map login(@RequestParam String email, @RequestParam String passwd) {

        Map<Object, Object> map = initResultMap();
        try {
            String token = iCompanyService.login(email, passwd);
            map.put(Constants.resultObject, token);
        } catch (PasswordErrorException e) {
            putInfo2Map(map, Constants.PASSWD_FAULT, e.getMessage());
        } catch (Exception e) {
            putInfo2Map(map, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return map;
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
            iCompanyService.loginWithToken(token);
        } catch (TokenExpiredException e) {
            putInfo2Map(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            putInfo2Map(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
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
    public Map register(@RequestBody Company company, @RequestParam int code) {

        Map<Object, Object> map = initResultMap();
        try {
            iCompanyService.register(company, code);
        } catch (ValidationExceprion | NoVerfcationCodeException e) {
            putInfo2Map(map, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            putInfo2Map(map, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return map;
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
    public Map<Object, Object> getUser(@RequestParam String token) {
        Map<Object, Object> returnMap = initResultMap();
        try {
            Company company = iCompanyService.getInfo(token);
            company.setPasswd(null);
            company.setToken(null);
            returnMap.put(Constants.resultObject, company);
        } catch (UserNotFoundException e) {
            putInfo2Map(returnMap, Constants.TOKEN_EXPIRED, e.getMessage());
        } catch (Exception e) {
            putInfo2Map(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
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
    public Map<Object, Object> updatePasswd(@RequestParam String token, @RequestParam String oldPasswd, @RequestParam String newPasswd) {

        Map<Object, Object> returnMap = initResultMap();
        try {
            String newToken = iCompanyService.updatePasswd(token, oldPasswd, newPasswd);
            returnMap.put(Constants.resultObject, newToken);
        } catch (TokenExpiredException e) {
            putInfo2Map(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            putInfo2Map(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
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
    public Map<Object, Object> updateInfo(@RequestBody Company company, @RequestParam String token) {

        Map<Object, Object> returnMap = initResultMap();
        try {
            iCompanyService.updateInfoByToken(company, token);
        } catch (TokenExpiredException e) {
            putInfo2Map(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            putInfo2Map(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
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
    public Map<Object, Object> resetPasswd(@RequestParam String email, @RequestParam int code, @RequestParam String passwd) {
        Map<Object, Object> returnMap = initResultMap();
        try {
            iVerificationCodeService.validateCode(email, code);
            iCompanyService.resetPasswd(email, passwd);
        } catch (ValidationExceprion | NoVerfcationCodeException e) {
            putInfo2Map(returnMap, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            putInfo2Map(returnMap, Constants.UNKNOWN_ERROR, e.getMessage());
        }
        return returnMap;
    }

    /**
     * @param
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author Murphy
     * @date 2019/4/25 17:35
     * @description 初始化返回的Map
     */
    private Map<Object, Object> initResultMap() {

        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        return returnMap;
    }


    /**
     * @param map
     * @param code
     * @param msg
     * @return void
     * @author Murphy
     * @date 2019/4/25 17:35
     * @description map putvalue的包装方法
     */
    static void putInfo2Map(Map<Object, Object> map, Integer code, String msg) {
        if (code != null) {
            map.put(Constants.resultCode, code);
        }
        if (msg != null) {
            map.put(Constants.resultMsg, msg);
        }
    }
}
