package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/5/27 14:05
 */
public class OverLimitException extends AbstractException {
    public OverLimitException() {
        super("超过发布岗位数量限制！");
        setErrorCode(Constants.OVER_LIMIT);
    }
}