package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/24 15:04
 */
public class ValidationExceprion extends MyException {

    public ValidationExceprion() {
        super("验证码错误！");
        setErrorCode(Constants.validateCodeError);
    }
}
