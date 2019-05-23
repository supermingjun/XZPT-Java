package xz.fzu.util;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

public class EmailUtilTest {

    /**
     * @param
     * @return void
     * @author Murphy
     * @date 2019/4/19 0:15
     * @description 测试验证码单元
     */
    @Test
    public void testSendEmail() throws EmailException {
        EmailUtil emailUtil = EmailUtil.getInstance();
//        System.out.println("当前验证码是：" + emailUtil.sendEmail("1732626355@qq.com"));
    }
}
