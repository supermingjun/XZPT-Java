package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IUserDao;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.util.TokenUtil;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/19 23:14
 */
@Service
public class UserServiceImpl implements IUserService {

//    @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入
    @Resource
IUserDao iUserDao;

    /**
     * @param user
     * @return void
     * @author Murphy
     * @date 2019/4/23 0:10
     * @description 注册, 返回token
     */
    @Override
    public String register(User user) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(uuid);
        String token = TokenUtil.createToken(user.getUserId(), user.getPasswd());
        user.setToken(token);
        iUserDao.insertUser(user);
        return token;
    }

    /**
     * @param email
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/20 15:14
     * @description 根据用户email查找用户
     */
    @Override
    public User selectByEmail(String email) {
        return iUserDao.selectByEmail(email);
    }

    @Override
    public User selectByUserId(String userId) {
        return iUserDao.selectByUserId(userId);
    }

    /**
     * @param user
     * @return int
     * @author Murphy
     * @date 2019/4/20 21:05
     * @description 验证用户名和密码
     */
    @Override
    public void vertifyUser(User user) {
        if (iUserDao.vertifyUser(user) != 1) {
            throw new RuntimeException("账号或密码错误");
        }
        ;
    }

    @Override
    public String verifyToken(String token) {
        TokenUtil.verify(token);
        return iUserDao.selectUserIdByToken(token);
    }

    @Override
    public int updateToken(String token, String userId) {
        return iUserDao.updateToken(token, userId);
    }

    @Override
    public User selectUserByToken(String token) {
        String userId = iUserDao.selectUserIdByToken(token);
        return iUserDao.selectByUserId(userId);
    }
}
