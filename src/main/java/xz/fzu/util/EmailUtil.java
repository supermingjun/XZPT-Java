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
    int sendEmail(String emailaddress) throws EmailException {
        int code = getRandomCode();
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.yeah.net");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com，qq失败率高，不建议使用
        email.setCharset("UTF-8");
        email.addTo(emailaddress);// 收件地址

        email.setFrom("supermingjun@yeah.net", "福大校招平台官方验证中心");//此处填邮箱地址和用户名,用户名可以任意填写
        email.setSSLOnConnect(true);
        email.setAuthentication("supermingjun@yeah.net", "djylrz666");//此处填写邮箱地址和客户端授权码

        email.setSubject("福大校招平台注册");//此处填写邮件名，邮件名可任意填写
        //TODO 修改这里的邮件内容
        email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);//此处填写邮件内容
        email.send();
        return code;
    }

    /* EmailUtil的静态引用 */
    private static EmailUtil emailUtil;

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
