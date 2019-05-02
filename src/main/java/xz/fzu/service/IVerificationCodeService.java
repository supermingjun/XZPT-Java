package xz.fzu.service;

import org.apache.commons.mail.EmailException;
import xz.fzu.exception.NoVerfcationCodeException;
import xz.fzu.exception.ValidationException;

/**
 * @author Murphy
 * @title: IVerificationCodeService
 * @projectName XZPT-Java
 * @description: 发送验证码相关的Service接口
 */
public interface IVerificationCodeService {

    /**
     * 发送验证码
     *
     * @param email
     * @return int
     * @throws EmailException 发送邮件异常
     * @author Murphy
     * @date 2019/5/2 19:49
     */
    int sendValidateCode(String email) throws EmailException;

    /**
     * 验证验证码
     *
     * @param email
     * @param code
     * @return boolean
     * @throws ValidationException       验证错误
     * @throws NoVerfcationCodeException 没有获取验证码异常
     * @author Murphy
     * @date 2019/5/2 19:49
     */
    boolean verifyCode(String email, int code) throws ValidationException, NoVerfcationCodeException;
}
