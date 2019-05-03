package xz.fzu.service;

import org.apache.commons.mail.EmailException;
import xz.fzu.exception.NoVerificationCodeException;
import xz.fzu.exception.ValidationException;

/**
 * 发送验证码相关的Service接口
 * @author Murphy
 */
public interface IVerificationCodeService {

    /**
     * 发送验证码
     *
     * @param email 邮件地址
     * @return int
     * @throws EmailException 发送邮件异常
     * @author Murphy
     * @date 2019/5/2 19:49
     */
    void sendValidateCode(String email) throws EmailException;

    /**
     * 验证验证码
     *
     * @param email 邮件地址
     * @param code 验证码
     * @return boolean
     * @throws ValidationException       验证错误
     * @throws NoVerificationCodeException 没有获取验证码异常
     * @author Murphy
     * @date 2019/5/2 19:49
     */
    void verifyCode(String email, int code) throws ValidationException, NoVerificationCodeException;
}
