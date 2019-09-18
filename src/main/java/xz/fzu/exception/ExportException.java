package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/5/25 15:33
 */
public class ExportException extends AbstractException {
    public ExportException() {
        super("导出失败！");
        setErrorCode(Constants.EXPORT_ERROR);
    }
}
