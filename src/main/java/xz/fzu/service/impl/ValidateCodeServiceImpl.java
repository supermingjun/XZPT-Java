package xz.fzu.service.impl;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import xz.fzu.service.IValidateCodeService;
import xz.fzu.util.EmailUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/20 11:12
 */
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {

    // 存储验证码
    static Map<String, Integer> map = new HashMap<>();

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
    public boolean validateCode(String email, int code) {
        return map.get(email).equals(code);
    }

}
