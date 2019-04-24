package xz.fzu.util;


import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Murphy
 * @date 2019/4/20 21:13
 */
public class SHA {
    private static final String KEY_SHA = "SHA";//TODO

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
        return sha.toString(32).substring(0, 32);
    }

    private SHA() {
    }
}