package xz.fzu.service.impl;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.EmailUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author Murphy
 * @date 2019/4/20 11:12
 */
@Service
public class VerificationCodeServiceImpl implements IVerificationCodeService {

    // 存储验证码
    @Autowired
    private HttpServletRequest httpServletRequest;
//    @Cacheable("users2")
//    public Map getAnotherUser(String key, String value) {
//        Map<String,String > map =new HashMap<>();
//        map.put(key, value);
//        return map;
//    }
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
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(email, value);
//        session.setMaxInactiveInterval(60);
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
        HttpSession session = httpServletRequest.getSession();
        return code == (Integer) session.getAttribute(email);
    }

}
