package xz.fzu.util;


import org.junit.Test;

/**
 * @author Murphy
 * @date 2019/4/20 21:13
 */
public class TestSHA {

    @Test
    public void main() {
        try {
            String inputStr = "简单加密";
            System.out.println("=======加密前的数据:" + inputStr);
            System.out.println("=======加密后的数据:" + SHA.encrypt(inputStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}