package xz.fzu.dao;

import xz.fzu.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Murphy
 * @date 2019/4/19 22:59
 */
@Repository
public interface IUserDao {
    void register(User user);
}
