package xz.fzu.util;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

public class EmailUtilTest {

    /**
     * @author Murphy
     * @date 2019/4/19 0:15
     * @param
     * @return void
     * @description 测试验证码单元
     */
    @Test
    public void testSendEmail() throws EmailException {
        EmailUtil emailUtil = EmailUtil.getInstance();
//        System.out.println("当前验证码是："+emailUtil.sendEmail("625326143@qq.com"));
    }
}
