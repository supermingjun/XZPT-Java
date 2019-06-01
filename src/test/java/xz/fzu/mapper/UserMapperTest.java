package xz.fzu.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xz.fzu.model.User;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/223:37
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class UserMapperTest {

    @Resource
    private UserMapper userDao;

    private static final String USER_ID = "8fc64f665d504230934ed8e639f52930";
    private static final String TOKEN = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJQQVNTV09SRCI6ImZnaDJ2Y2tpZm0xOGxzaGZiNGdqOXE0ajRpMDY2djBkIiwiVVNFUl9JRCI6ImFjNjc0OGUxMmQxZDRmM2ZhZTEyNmYzYWZmYTFjMDVkIiwiZXhwIjoxNTYwNTY3MDI5fQ.iaZfKKGxfjIpWQEtr215ToodU9-Soi396aVVpcY4s_8";
    private static final String PASSWD = "383nmvdponqbibsei890qvim5p0r2j6";

    @Test
    public void selectByUserId() {

        User find = userDao.selectByUserId(USER_ID);
        assert find != null;
    }

    @Test
    public void vertifyUser() {
        User user = new User();
        user.setUserId(USER_ID);
        user.setPasswd(PASSWD);
        userDao.vertifyUser(user);
    }

    @Test
    public void selectUserIdByToken() {
        String find = userDao.selectUserIdByToken(TOKEN);
        assert find != null;
    }

    @Test
    public void updateToken() {
        userDao.updateToken(TOKEN, USER_ID);
    }

    @Test
    public void updatePasswd() {
        userDao.updatePasswd(USER_ID, PASSWD);
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setUserId(USER_ID);
        user.setPasswd(PASSWD);
        userDao.updateInfo(user);
    }
}