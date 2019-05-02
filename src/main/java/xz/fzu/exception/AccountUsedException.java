package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/29 22:04
 */
public class AccountUsedException extends AbstractException {

    public AccountUsedException() {
        super("账户已被注册！");
        setErrorCode(Constants.ACCOUNT_USED);
    }
}
