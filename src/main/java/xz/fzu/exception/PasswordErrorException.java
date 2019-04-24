package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/24 14:48
 */
public class PasswordErrorException extends MyException {
    public PasswordErrorException() {
        super("密码错误！");
        setErrorCode(Constants.PASSWD_FAULT);
    }
}
