package xz.fzu.service.impl;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import xz.fzu.exception.NoVerfcationCodeException;
import xz.fzu.exception.ValidationException;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.EmailUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Murphy
 * @date 2019/4/20 11:12
 */
@Service
public class VerificationCodeServiceImpl implements IVerificationCodeService {

    /**
     * 存储验证码
     */
    private static Map<String, Integer> map = new HashMap<>();
    /***
     * @author Murphy
     * @date 2019/4/20 11:22
     * @param email
     * @return int
     * @description 发送验证码
     */
    @Override
    public int sendValidateCode(String email) throws EmailException {
        EmailUtil emailUtil = EmailUtil.getInstance();
        int value = emailUtil.sendEmail(email);
        map.put(email, value);
        return value;
    }

    /***
     * @author Murphy
     * @date 2019/4/20 11:24
     * @param email
     * @param code
     * @return boolean
     * @description 验证验证码的方法
     */
    @Override
    public boolean verifyCode(String email, int code) throws ValidationException, NoVerfcationCodeException {
        Integer value = map.get(email);
        if (value == null) {
            throw new NoVerfcationCodeException();
        } else if (value != code) {
            throw new ValidationException();
        }
        map.remove(email);
        return true;
    }

}
