package xz.fzu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Murphy
 * @date 2019/5/223:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class CompanyServiceImplTest {

    @Test
    public void register() {
    }

    @Test
    public void getInfoByCompanyId() {
    }

    @Test
    public void updateInfoByToken() {
    }

    @Test
    public void login() {
    }

    @Test
    public void verifyToken() {
    }

    @Test
    public void resetPasswd() {
    }

    @Test
    public void updateToken() {
    }

    @Test
    public void getInfoByToken() {
    }

    @Test
    public void updatePasswd() {
    }
}