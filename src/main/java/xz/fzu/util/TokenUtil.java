package xz.fzu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/20 20:30
 */
public class TokenUtil {

    // 公钥
    private static final String TOKEN_SECRET = "123";//TODO  修改密钥

    private static final String USER_ID = "USER_ID";
    private static final String PASSWORD = "PASSWORD";

    // 过期时间为7天
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * @param userId
     * @param passwd
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/20 20:34
     * @description 生成签名
     */
    public static String createToken(String userId, String passwd) {
        // 设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥和加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        // 设置头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("type", "JWT");
        header.put("alg", "HS256");
        // 返回token字符串
        return JWT.create()
                .withHeader(header)
                .withClaim(USER_ID, userId)
                .withClaim(PASSWORD, passwd)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * @param token
     * @return boolean
     * @author Murphy
     * @date 2019/4/20 20:37
     * @description 检验token是否正确
     */
    public static boolean verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }
}
