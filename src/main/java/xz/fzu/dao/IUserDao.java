package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import xz.fzu.model.User;

/**
 * @author Murphy
 * @date 2019/4/19 22:59
 */
@Repository
public interface IUserDao {
    void insertUser(User user);

    User selectByEmail(String email);

    User selectByUserId(String email);
}
