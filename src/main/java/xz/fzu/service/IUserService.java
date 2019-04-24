package xz.fzu.service;

import xz.fzu.model.User;


public interface IUserService {
    String register(User user);

    User selectByEmail(String email);

    User selectByUserId(String userId);

    void vertifyUser(User user);

    String verifyToken(String token);

    int updateToken(String token, String userId);

    User selectUserByToken(String token);
}
