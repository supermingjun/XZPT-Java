package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/4/27 10:54
 */
public class InstanceNotExistException extends AbstractException {
    public InstanceNotExistException() {
        super("找不到实例！");
        setErrorCode(Constants.INSTANCE_NOT_EXIST);
    }
}
