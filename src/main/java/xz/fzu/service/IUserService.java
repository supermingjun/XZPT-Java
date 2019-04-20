package xz.fzu.service;

import org.apache.commons.mail.EmailException;
import xz.fzu.model.User;


public interface IUserService {
    void register(User user) throws EmailException;
}
