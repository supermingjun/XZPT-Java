package xz.fzu.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xz.fzu.model.User;

/**
 * @author Murphy
 * @date 2019/5/223:37
 */
public class IUserDaoTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testUserDao() {
        User user = new User();
        user.setPasswd("123456");
        user.setUserName("待就业");
        user.setEmail("djylrz@qq.com");
        user.setSex(1);
        //System.out.println(userDao.findOnValidate(user).getMark());
        User find = userDao.selectByEmail("djylrz@qq.com");
        System.out.println(find.getUserId());
        System.out.println(find.getUserName());
    }
}