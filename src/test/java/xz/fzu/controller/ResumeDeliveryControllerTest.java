package xz.fzu.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Murphy
 * @date 2019/5/223:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class ResumeDeliveryControllerTest {

    @Test
    public void deliveryResume() {
    }

    @Test
    public void userGetDeliveryRecord() {
    }

    @Test
    public void userUpdateDeliveryRecord() {
    }

    @Test
    public void userGetDeliveryRecordById() {
    }

    @Test
    public void userGetDeliveryRecordByResumeId() {
    }

    @Test
    public void companyGetDeliveryRecord() {
    }

    @Test
    public void comapnyUpdateDeliveryRecord() {
    }

    @Test
    public void companyGetDeliveryRecordById() {
    }

    @Test
    public void companyGetResumeByResumeId() {
    }
}