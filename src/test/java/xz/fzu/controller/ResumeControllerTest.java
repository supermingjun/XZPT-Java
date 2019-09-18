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
public class ResumeControllerTest {

    @Test
    public void getListResume() {
    }

    @Test
    public void getResume() {
    }

    @Test
    public void updateResume() {
    }

    @Test
    public void insertResume() {
    }

    @Test
    public void deleteResume() {
    }
}