package xz.fzu.exception;

/**
 * @author Murphy
 * @date 2019/4/24 14:47
 */
public class TokenExpiredException extends Exception {
    private TokenExpiredException(String name) {
        super(name);
    }

    public TokenExpiredException() {
        super("Token 过期");
    }
}
