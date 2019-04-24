package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import xz.fzu.model.User;

/**
 * @author Murphy
 * @date 2019/4/19 22:59
 */
@Repository
public interface IUserDao {

    /**
     * @param user
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:14
     * @description 插入用户
     */
    void insertUser(User user);

    /**
     * @param email
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 14:16
     * @description 根据邮箱查找用户
     */
    User selectByEmail(String email);

    /**
     * @param userId
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 14:14
     * @description 根据id查找用户 调用此方法前应先调用token得到userid的方法-
     */
    User selectByUserId(String userId);

    /**
     * @param user
     * @return int
     * @author Murphy
     * @date 2019/4/24 14:14
     * @description 验证用户账号密码
     */
    int vertifyUser(User user);

    /**
     * @param token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 14:14
     * @description 通过token查找用户
     */
    String selectUserIdByToken(String token);

    /**
     * @param token
     * @param userId
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:08
     * @description 更新token
     */
    void updateToken(String token, String userId);

    /**
     * @param userId
     * @param passwd
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:13
     * @description 更新密码
     */
    void updatePasswd(String userId, String passwd);


    /**
     * @param user
     * @return void
     * @author Murphy
     * @date 2019/4/24 16:41
     * @description 更新用户信息
     */
    void updateInfo(User user);
}
