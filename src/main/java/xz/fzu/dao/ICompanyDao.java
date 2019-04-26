package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import xz.fzu.model.Company;

@Repository
public interface ICompanyDao {

    /**
     * @param email
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 18:14
     * @description 通过email查找company的id
     */
    String selectIdByEmail(String email);

    /**
     * @param token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 19:26
     * @description 通过token查看企业的id
     */
    String selectIdByToken(String token);

    /**
     * @param companyId
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 18:15
     * @description 查找company实例
     */
    Company selectCompanyById(String companyId);

    /**
     * @param email
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 18:15
     * @description 通过email查找company实例
     */
    Company selectCompanyByEmail(String email);

    /**
     * @param company
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:17
     * @description 修改公司信息，应当传入公司id，以及在修改完状态后应当更新审核状态
     */
    void updateInfo(Company company);

    /**
     * @param company
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:18
     * @description 插入公司实例
     */
    void insertInstance(Company company);

    /**
     * @param companyId
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:19
     * @description 删除公司实例
     */
    void deleteCompany(String companyId);

    /**
     * @param token
     * @return int
     * @author Murphy
     * @date 2019/4/25 19:47
     * @description 验证token的正确性
     */
    int verifyToken(String token);


    /**
     * @param email
     * @param passwd
     * @return int
     * @author Murphy
     * @date 2019/4/25 19:50
     * @description 通过账户密码登录
     */
    int loginWithPasswd(String email, String passwd);

    /**
     * @param token
     * @param oldPasswd
     * @param newPasswd
     * @return int
     * @author Murphy
     * @date 2019/4/25 20:51
     * @description 更新用户密码
     */
    int updatePasswd(String token, String oldPasswd, String newPasswd);

}
