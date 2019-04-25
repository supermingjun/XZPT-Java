package xz.fzu.service;

import xz.fzu.exception.PasswordErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;


public interface IUserService {
    String register(User user);

    User selectByEmail(String email);

    User selectByUserId(String userId);

    String vertifyUser(User user);

    String verifyToken(String token) throws TokenExpiredException;

    void updateToken(String token, String userId);

    User selectUserByToken(String token);

    String updatePasswd(String token, String oldPasswd, String newPasswd) throws PasswordErrorException, TokenExpiredException;

    void updateInfo(User user, String token) throws TokenExpiredException;

    void resetPasswd(String email, String passwd);
}
