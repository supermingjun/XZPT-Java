package xz.fzu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Murphy
 * @date 2019/5/223:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class RecruitmentServiceImplTest {

    @Test
    public void insertRecruitment() {
    }

    @Test
    public void getRecruitmentById() {
    }

    @Test
    public void getListRecruitmentByCompanyId() {
    }

    @Test
    public void deleteRecruitment() {
    }

    @Test
    public void updateRecruitment() {
    }

    @Test
    public void getListRecruitmentByKeyWord() {
    }
}