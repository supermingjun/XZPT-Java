package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/24 15:04
 */
public class ValidationException extends AbstractException {

    public ValidationException() {
        super("验证码错误！");
        setErrorCode(Constants.VALIDATE_CODE_ERROR);
    }
}
