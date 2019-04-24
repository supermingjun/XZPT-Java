package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import xz.fzu.model.User;

/**
 * @author Murphy
 * @date 2019/4/19 22:59
 */
@Repository
public interface IUserDao {
    // 插入用户
    void insertUser(User user);

    // 根据邮箱查找用户
    User selectByEmail(String email);

    // 根据id查找用户 调用此方法前应先调用token得到userid的方法-
    User selectByUserId(String userId);

    // 验证用户账号密码
    int vertifyUser(User user);

    // 通过token查找用户
    String selectUserIdByToken(String token);

    // 更新token
    int updateToken(String token, String userId);
}
