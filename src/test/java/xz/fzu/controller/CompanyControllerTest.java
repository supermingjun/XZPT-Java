package xz.fzu.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Murphy
 * @date 2019/5/223:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class CompanyControllerTest {

    @Test
    public void login() {
    }

    @Test
    public void loginWithToken() {
    }

    @Test
    public void register() {
    }

    @Test
    public void getUser() {
    }

    @Test
    public void updatePasswd() {
    }

    @Test
    public void updateInfo() {
    }

    @Test
    public void resetPasswd() {
    }
}