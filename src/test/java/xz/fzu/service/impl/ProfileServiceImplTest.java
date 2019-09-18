package xz.fzu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xz.fzu.model.UserProfile;
import xz.fzu.service.IProfileService;

/**
 * @author Murphy
 * @date 2019/5/223:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class ProfileServiceImplTest {
    @Autowired
    private IProfileService iProfileService;
    @Test
    public void getUserProfile() {
        UserProfile user = iProfileService.getUserProfile("8fc64f665d504230934ed8e639f52930");
        assert user != null;
    }

    @Test
    public void getRecruitmentProfile() {
    }

    @Test
    public void selectUserId() {
    }
}