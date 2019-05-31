package xz.fzu.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.model.User;

/**
 * 用户相关的dao
 *
 * @author Murphy
 * @date 2019/4/19 22:59
 */
@Repository
public interface UserMapper extends Mapper<User> {

    /**
     * 插入用户
     *
     * @param user 用户实例
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:14
     */
//    void insertUser(User user);

    /**
     * 从邮箱查找用户的实例
     *
     * @param email 邮件地址
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 14:16
     * @description 根据邮箱查找用户
     */
    User selectByEmail(String email);

    /**
     * 根据id查找用户 调用此方法前应先调用token得到userid的方法
     *
     * @param userId 用户id
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 14:14
     */
    User selectByUserId(String userId);

    /**
     * 验证用户账号密码
     *
     * @param user 用户实例
     * @return int
     * @author Murphy
     * @date 2019/4/24 14:14
     */
    int vertifyUser(User user);

    /**
     * 通过token查找用户
     *
     * @param token token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 14:14
     */
    String selectUserIdByToken(String token);

    /**
     * 更新token
     *
     * @param token  token
     * @param userId 用户id
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:08
     */
    void updateToken(String token, String userId);

    /**
     * 更新密码
     *
     * @param userId 用户id
     * @param passwd 密码
     * @return void
     * @author Murphy
     * @date 2019/4/24 14:13
     */
    void updatePasswd(String userId, String passwd);


    /**
     * 更新用户信息
     *
     * @param user 用户实例
     * @return void
     * @author Murphy
     * @date 2019/4/24 16:41
     */
    void updateInfo(User user);
}
