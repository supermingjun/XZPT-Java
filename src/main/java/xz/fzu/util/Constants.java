package xz.fzu.util;

/**
 * @author Murphy
 * @date 2019/4/20 11:40
 */
public class Constants {
    public static final String RESULT_CODE = "RESULT_CODE";
    /* 返回值相关*/
    /* OK */
    public static final Integer OK = 200;

    public static final int INTERNAL_ERROR = 1999;
    // Token过期
    public static final int TOKEN_EXPIRED = 2002;

    // 账号不存在或者密码错误。
    public static final int PASSWD_FAULT = 2008;
    // 验证码错误
    public static final Integer VALIDATE_CODE_ERROR = 2011;
    // 账号已被注册
    public static final Integer ACCOUNT_USED = 2009;

    // 邮件发送出错
    public static final Integer SEND_EMAIL_ERROR = 2012;

    // 数据库连接错误
    public static final int DATABASE_CONNECT_ERROR = 2013;


    // 未获取验证码错误
    public static final int NO_VERFCATION_CODE = 2014;
    // 未找到用户异常
    public static final int USER_NOT_FOUND_ERROR = 2015;
    // 请求URL错误
    public static final int URL_NOT_FOUND = 2016;
    // Token 错误
    public static final Integer TOKEN_ERROR = 2017;
    // 找不到实例
    public static final int INTANCE_NOT_EXIST = 2018;
    // 恶意操作
    public static final int EVIL_INTENTIONS = 2019;


    public static final String RESULT_MSG = "RESULT_MSG";
    public static final String RESULT_OBJECT = "RESULT_OBJECT";
}
