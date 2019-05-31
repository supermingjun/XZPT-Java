package xz.fzu.mapper;

import org.junit.Test;
import xz.fzu.model.User;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/223:37
 */
public class UserMapperTest {

    @Resource
    private UserMapper userDao;

    @Test
    public void testUserDao() {
        User user = new User();
        user.setPasswd("123456");
        user.setUserName("待就业");
        user.setEmail("djylrz@qq.com");
        user.setSex(1L);
        //System.out.println(userDao.findOnValidate(user).getMark());
        User find = userDao.selectByEmail("djylrz@qq.com");
        System.out.println(find.getUserId());
        System.out.println(find.getUserName());
    }
}