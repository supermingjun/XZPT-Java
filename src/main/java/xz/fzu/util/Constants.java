package xz.fzu.util;

/**
 * @author Murphy
 * @date 2019/4/20 11:40
 */
public class Constants {
    public static final String resultCode = "resultCode";
    /* 返回值相关*/
    /* OK */
    public static final Integer OK = 200;

    public static final int INTERNAL_ERROR = 1999;
    // Token过期
    public static final int TOKEN_EXPIRED = 2002;

    // 账号不存在或者密码错误。
    public static final int PASSWD_FAULT = 2008;
    // 验证码错误
    public static final Integer validateCodeError = 2011;
    // 账号已被注册
    public static final Integer accountUsed = 2009;

    // 邮件发送出错
    public static final Integer sendEmailError = 2012;

    // 数据库连接错误
    public static final int databaseConnectError = 2013;


    // 未获取验证码错误
    public static final int noVerfcationCode = 2014;
    // 未找到用户异常
    public static final int userNotFoundError = 2015;
    // 请求URL错误
    public static final int URL_NOT_FOUND = 2016;



    public static final String resultMsg = "resultMsg";
    public static final String resultObject = "resultObject";
}
