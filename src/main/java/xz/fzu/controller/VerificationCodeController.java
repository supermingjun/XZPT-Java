package xz.fzu.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.model.User;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;

/**
 * 验证码相关的控制器
 *
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
     * @param user 用户实例
     * @return void
     * @author Murphy
     * @date 2019/4/20 11:08
     * @description 验证码工具
     */
    @RequestMapping(value = "/*")
    @ResponseBody
    public ResponseData getVerificationCode(@RequestBody User user) throws EmailException {

        ResponseData responseData = new ResponseData();
        String email = user.getEmail();
        iValidateCodeService.sendValidateCode(email);

        return responseData;
    }
}
