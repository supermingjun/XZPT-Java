package xz.fzu.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    /**
     * 向指定邮箱发送验证码
     *
     * @param emailaddress 目标邮箱
     * @return 返回随机码
     * @author mingjun
     */
    public int sendEmail(String emailaddress) throws EmailException {
        int code = getRandomCode();
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.yeah.net");
        email.setHostName("smtp.163.com");
        email.setCharset("UTF-8");
        // 收件地址
        email.addTo(emailaddress);
        // 给自己抄送一份
//        email.addCc("supermingjun@yeah.net");

//        email.setFrom("supermingjun@yeah.net", "福大校招平台官方验证中心");
        email.setFrom("litmkf@163.com", "福大校招平台官方验证中心");
        email.addCc("litmkf@163.com");
        email.setSSLOnConnect(true);
        email.setAuthentication("litmkf@163.com", "zxcvbnm123456");

        email.setSubject("福大校招平台注册");//此处填写邮件名，邮件名可任意填写
        //TODO 修改这里的邮件内容
        email.setMsg("尊敬的用户，您好！您本次注册的验证码是:" + code + "!");//此处填写邮件内容
        email.send();
        return code;
    }

    /* EmailUtil的静态引用 */
    private volatile static EmailUtil emailUtil;

    /**
     * @param
     * @return
     * @author Murphy
     * @date 2019/4/19 0:01
     * @description 匿名构造函数
     */
    private EmailUtil() {

    }


    /**
     * @param
     * @return xz.fzu.util.EmailUtil
     * @author Murphy
     * @date 2019/4/18 23:59
     * @description 返回单例
     */
    public static EmailUtil getInstance() {
        if (emailUtil == null) {
            synchronized (EmailUtil.class) {
                if (emailUtil == null) {
                    emailUtil = new EmailUtil();
                }
            }
        }
        return emailUtil;
    }

    /**
     * @author Murphy
     * @date 2019/4/19 0:14
     * @param
     * @return int
     * @description 产生随机的6位数验证码
     */
    private int getRandomCode(){
        return (int)(Math.random()*100000*0.9+100000);
    }

}
