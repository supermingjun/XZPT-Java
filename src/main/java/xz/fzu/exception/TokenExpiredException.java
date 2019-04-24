package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/24 14:47
 */
public class TokenExpiredException extends MyException {

    public TokenExpiredException() {
        super("Token 过期！");
        setErrorCode(Constants.TOKEN_EXPIRED);
    }
}
