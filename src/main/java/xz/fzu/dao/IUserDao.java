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

    //根据邮箱查找用户
    User selectByEmail(String email);

    //根据用户Id查找用户
    User selectByUserId(String email);

    // 验证用户账号密码
    int vertifyUser(User user);
}
