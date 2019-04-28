package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IUserDao;
import xz.fzu.exception.PasswordErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.util.SHA;
import xz.fzu.util.TokenUtil;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/19 23:14
 */
@Service
public class UserServiceImpl implements IUserService {

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
        user.setPasswd(SHA.encrypt(user.getPasswd()));
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
     * @description 验证用户名和密码, 并且返回token
     */
    @Override
    public String verifyUser(User user) throws PasswordErrorException {

        // 密码加密处理
        user.setPasswd(SHA.encrypt(user.getPasswd()));
        if (iUserDao.vertifyUser(user) != 1) {
            throw new PasswordErrorException();
        }
        // 因为传入的user只有email和passwd
        user = selectByEmail(user.getEmail());
        String token = TokenUtil.createToken(user.getUserId(), user.getPasswd());
        // 更新Token
        updateToken(token, user.getUserId());

        return token;
    }

    /**
     * @param token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 14:04
     * @description 验证token是否正确和过期
     */
    @Override
    public String verifyToken(String token) throws TokenExpiredException {

        TokenUtil.verify(token);
        String userId = iUserDao.selectUserIdByToken(token);
        if (userId == null) {
            throw new TokenExpiredException();
        }

        return userId;
    }

    /**
     * @param token
     * @param userId
     * @return int
     * @author Murphy
     * @date 2019/4/24 14:04
     * @description 更新token
     */
    @Override
    public void updateToken(String token, String userId) {
        iUserDao.updateToken(token, userId);
    }

    /**
     * @param token
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 16:51
     * @description 通过token查找用户
     */
    @Override
    public User selectUserByToken(String token) throws TokenExpiredException {

        String userId = verifyToken(token);
        User user = iUserDao.selectByUserId(userId);
        user.setPasswd(null);
        user.setToken(null);

        return user;
    }

    /**
     * @param user
     * @param token
     * @return void
     * @author Murphy
     * @date 2019/4/24 16:50
     * @description 更新用户信息（禁止更新密码）
     */
    @Override
    public void updateInfo(User user, String token) throws TokenExpiredException {

        String userId = verifyToken(token);
        user.setUserId(userId);
        user.setPasswd(null);

        iUserDao.updateInfo(user);
    }

    /**
     * @param token
     * @param oldPasswd
     * @param newPasswd
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 15:35
     * @description 更改密码
     */
    @Override
    public String updatePasswd(String token, String oldPasswd, String newPasswd) throws PasswordErrorException, TokenExpiredException {

        // 对密码进行加密处理
        oldPasswd = SHA.encrypt(oldPasswd);
        newPasswd = SHA.encrypt(newPasswd);

        String userId = verifyToken(token);

        User user = iUserDao.selectByUserId(userId);
        if (!oldPasswd.equals(user.getPasswd())) {
            throw new PasswordErrorException();
        }
        iUserDao.updatePasswd(userId, newPasswd);
        String newToken = TokenUtil.createToken(userId, newPasswd);
        iUserDao.updateToken(newToken, userId);

        return newToken;
    }

    /**
     * @param email
     * @param passwd
     * @return void
     * @author Murphy
     * @date 2019/4/25 17:28
     * @description 重置用户密码
     */
    @Override
    public void resetPasswd(String email, String passwd) {

        String userId = iUserDao.selectByEmail(email).getUserId();
        User user = new User();
        user.setUserId(userId);
        user.setPasswd(SHA.encrypt(passwd));

        iUserDao.updateInfo(user);
    }
}
