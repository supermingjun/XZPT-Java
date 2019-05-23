package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/25 17:20
 * 用户未找到异常
 */
public class UserNotFoundException extends AbstractException {
    public UserNotFoundException() {
        super("未找到该用户!");
        setErrorCode(Constants.USER_NOT_FOUND_ERROR);
    }
}
