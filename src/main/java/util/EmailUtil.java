package util;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    /**
     * 向指定邮箱发送验证码
     * @param emailaddress 目标邮箱
     * @param code 验证码
     * @return 成功true，失败false
     * @author mingjun
     */
    public static boolean sendEmail(String emailaddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.yeah.net");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com，qq失败率高，不建议使用
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom("supermingjun@yeah.net", "福大校招平台官方验证中心");//此处填邮箱地址和用户名,用户名可以任意填写
            email.setSSLOnConnect(true);
            email.setAuthentication("supermingjun@yeah.net", "djylrz666");//此处填写邮箱地址和客户端授权码

            email.setSubject("福大校招平台注册");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);//此处填写邮件内容
            email.send();
            //System.out.println("发送成功");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            //System.out.println("发送失败");
            return false;
        }
    }

    public static void main(String[] args) {
        sendEmail("1732626355@qq.com","658964");
    }
}
