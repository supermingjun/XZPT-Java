package xz.fzu.service;


import xz.fzu.exception.*;
import xz.fzu.model.Company;

/**
 * 企业相关的Service接口
 *
 * @author Murphy
 */
public interface ICompanyService {
    /**
     * 企业登录接口
     *
     * @param company 企业实例
     * @param code    验证码
     * @return void
     * @throws ValidationException         验证错误异常
     * @throws NoVerificationCodeException 没有获取验证码异常
     * @author Murphy
     * @date 2019/4/25 19:23
     */
    void register(Company company, int code) throws ValidationException, NoVerificationCodeException;//TODO 是否需要code

    /**
     * 根据企业id查看企业信息
     *
     * @param companyId 企业id
     * @return xz.fzu.model.Company
     * @throws UserNotFoundException 找不到该用户
     * @author Murphy
     * @date 2019/4/25 19:25
     */
    Company getInfoByCompanyId(String companyId) throws UserNotFoundException;

    /**
     * 通过token 更新用户信息
     *
     * @param company 企业实例
     * @param token   token
     * @return void
     * @throws TokenExpiredException token过期异常
     * @author Murphy
     * @date 2019/4/25 19:27
     */
    void updateInfoByToken(Company company, String token) throws TokenExpiredException;

    /**
     * 通过邮箱和密码登录，返回token
     *
     * @param email  邮箱地址
     * @param passwd 密码
     * @return java.lang.String
     * @throws PasswordErrorException 密码错误异常
     * @author Murphy
     * @date 2019/4/25 19:31
     */
    String login(String email, String passwd) throws PasswordErrorException;

    /**
     * 使用token登录
     *
     * @param token token
     * @return java.lang.String
     * @throws TokenExpiredException token过期异常
     * @author Murphy
     * @date 2019/4/25 19:55
     */
    String verifyToken(String token) throws TokenExpiredException;

    /**
     * 重置密码
     *
     * @param email
     * @param passwd
     * @return void
     * @throws TokenExpiredException token过期异常
     * @author Murphy
     * @date 2019/4/25 20:01
     * @description 更新用户密码
     */
    void resetPasswd(String email, String passwd) throws TokenExpiredException;

    /**
     * 更新token
     *
     * @param company 企业实例
     * @return void
     * @author Murphy
     * @date 2019/4/25 20:08
     * @description 更新用户的token
     */
    void updateToken(Company company);

    /**
     * 从token中获得实例
     *
     * @param token token
     * @return xz.fzu.model.Company
     * @throws TokenExpiredException token过期异常
     * @throws UserNotFoundException 用户未找到异常
     * @author Murphy
     * @date 2019/4/25 20:44
     * @description 通过token获得企业实例
     */
    Company getInfoByToken(String token) throws TokenExpiredException, UserNotFoundException;

    /**
     * 修改密码并返回新的token
     *
     * @param token     token
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @return java.lang.String
     * @throws TokenExpiredException  token过期异常
     * @throws PasswordErrorException 密码错误异常
     * @author Murphy
     * @date 2019/4/25 21:00
     */
    String updatePasswd(String token, String oldPasswd, String newPasswd) throws TokenExpiredException, PasswordErrorException;
}
