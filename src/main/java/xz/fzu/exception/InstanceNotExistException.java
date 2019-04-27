package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/27 10:54
 */
public class InstanceNotExistException extends MyException {
    public InstanceNotExistException() {
        super("找不到实例！");
        setErrorCode(Constants.intanceNotExist);
    }
}
