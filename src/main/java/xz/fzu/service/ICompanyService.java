package xz.fzu.service;


import xz.fzu.exception.*;
import xz.fzu.model.Company;

public interface ICompanyService {
    /**
     * @param company
     * @return void
     * @author Murphy
     * @date 2019/4/25 19:23
     * @description 企业登录接口
     */
    void register(Company company, int code) throws ValidationExceprion, NoVerfcationCodeException;//TODO 是否需要code

    /**
     * @param email
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 19:24
     * @description 根据企业token查看企业
     */
    Company getInfoByToken(String token) throws UserNotFoundException;

    /**
     * @param CompanyId
     * @param a
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 19:25
     * @description 根据企业id查看企业信息
     */
    Company getInfoByCompanyId(String CompanyId, int a) throws UserNotFoundException;

    /**
     * @param company
     * @return void
     * @author Murphy
     * @date 2019/4/25 19:27
     * @description 通过token 更新用户信息
     */
    void updateInfoByToken(Company company, String token) throws TokenExpiredException;

    /**
     * @param email
     * @param passwd
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 19:31
     * @description 通过邮箱和密码登录，返回token
     */
    String login(String email, String passwd) throws PasswordErrorException;

    /**
     * @param token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 19:55
     * @description 使用token登录
     */
    String verifyToken(String token) throws TokenExpiredException;

    /**
     * @param email
     * @param passwd
     * @return void
     * @author Murphy
     * @date 2019/4/25 20:01
     * @description 更新用户密码
     */
    void resetPasswd(String email, String passwd) throws TokenExpiredException;

    /**
     * @param company
     * @return void
     * @author Murphy
     * @date 2019/4/25 20:08
     * @description 更新用户的token
     */
    void updateToken(Company company);

    /**
     * @param token
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 20:44
     * @description 通过token获得企业实例
     */
    Company getInstaceByToken(String token) throws TokenExpiredException, UserNotFoundException;

    /**
     * @param token
     * @param oldPasswd
     * @param newPasswd
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 21:00
     * @description 修改密码并返回新的token
     */
    String updatePasswd(String token, String oldPasswd, String newPasswd) throws TokenExpiredException, PasswordErrorException;
}
