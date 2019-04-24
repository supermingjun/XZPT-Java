package xz.fzu.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.model.User;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.Constants;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/20 11:06
 */
@RequestMapping(value = "/getverificationcode")
@RestController
public class VerificationCodeController {

    @Resource
    IVerificationCodeService iValidateCodeService;

    @Autowired
    public VerificationCodeController(IVerificationCodeService iValidateCodeService) {
        this.iValidateCodeService = iValidateCodeService;
    }

    /**
     * @param
     * @return void
     * @author Murphy
     * @date 2019/4/20 11:08
     * @description 验证码工具
     */
    @RequestMapping(value = "/*")
    @ResponseBody
    public Map getVerificationCode(@RequestBody User user) {
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        try {
            String email = user.getEmail();
            iValidateCodeService.sendValidateCode(email);
        } catch (EmailException e) {
            returnMap.put(Constants.resultCode, Constants.sendEmailError);
        }
        return returnMap;
    }
}
