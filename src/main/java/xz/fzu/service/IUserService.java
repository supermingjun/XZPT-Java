package xz.fzu.service;

import xz.fzu.exception.AccountUsedException;
import xz.fzu.exception.PasswordErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;

/**
 *  用户相关的Service接口
 * @author Murphy
 */
public interface IUserService {
    /**
     * 用户注册
     *
     * @param user 用户实例
     * @return java.lang.String
     * @throws AccountUsedException 账号已被使用异常
     * @author Murphy
     * @date 2019/5/2 20:09
     */
    String register(User user) throws AccountUsedException;

    /**
     * 搜索用户通过邮箱
     * @author Murphy
     * @date 2019/5/2 20:14
     * @param email 邮件地址
     * @return xz.fzu.model.User
     */
    User selectByEmail(String email);

    /**
     * 根据用户id查找用户
     * @author Murphy
     * @date 2019/5/2 20:58
     * @param userId 用户id
     * @return xz.fzu.model.User
     */
    User selectByUserId(String userId);

    /**
     * 验证用户的账户密码
     * @author Murphy
     * @date 2019/5/2 20:58
     * @param user 用户实例
     * @return java.lang.String
     * @throws PasswordErrorException 密码错误异常
     */
    String verifyUser(User user) throws PasswordErrorException;

    /**
     * 验证tken是否正常
     * @author Murphy
     * @date 2019/5/2 20:59
     * @param token toen
     * @return java.lang.String
     * @throws TokenExpiredException token过期异常
     */
    String verifyToken(String token) throws TokenExpiredException;

    /**
     * 更新token
     * @author Murphy
     * @date 2019/5/2 20:59
     * @param token token
     * @param userId 用户id
     * @return void
     */
    void updateToken(String token, String userId);

    /**
     * 根据token查找用户实例
     * @author Murphy
     * @date 2019/5/2 21:00
     * @param token token
     * @return xz.fzu.model.User
     * @throws TokenExpiredException token过期异常
     */
    User selectUserByToken(String token) throws TokenExpiredException;

    /**
     * 更新密码
     * @author Murphy
     * @date 2019/5/2 21:00
     * @param token token
     * @param oldPasswd 老密码
     * @param newPasswd 新密码
     * @return java.lang.String
     * @throws PasswordErrorException 密码错误异常
     * @throws TokenExpiredException token过期异常
     */
    String updatePasswd(String token, String oldPasswd, String newPasswd) throws PasswordErrorException, TokenExpiredException;

    /**
     * 更新用户信息
     * @author Murphy
     * @date 2019/5/2 21:01
     * @param user 用户实例
     * @param token token
     * @return void
     * @throws TokenExpiredException token过期异常
     */
    void updateInfo(User user, String token) throws TokenExpiredException;

    /**
     * 重置密码
     * @author Murphy
     * @date 2019/5/2 21:02
     * @param email 邮件地址
     * @param passwd 密码
     * @return void
     */
    void resetPasswd(String email, String passwd);
}
