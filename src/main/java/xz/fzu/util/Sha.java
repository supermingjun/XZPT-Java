package xz.fzu.util;


import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Murphy
 * @date 2019/4/20 21:13
 */
public class Sha {
    private static final String KEY_SHA = "Sha";
    //TODO 更换key
    /**
     * @param passwd
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/23 0:02
     * @description 加密生成uuid
     */
    public static String encrypt(String passwd) {
        BigInteger sha = null;
        byte[] inputData = passwd.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            return passwd;
        }
        String str = sha.toString(32);
        return str.length() >= 32 ? str.substring(0, 32) : str;
    }

    private Sha() {
    }
}