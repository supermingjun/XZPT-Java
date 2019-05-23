package xz.fzu.exception;

import xz.fzu.util.Constants;

/**
 * @author Murphy
 * @date 2019/5/20 23:19
 */
public class CsvErrorException extends AbstractException {
    public CsvErrorException() {
        super("CSV错误！");
        setErrorCode(Constants.CSV_ERROR);
    }

    public CsvErrorException(String string) {
        super(string);
        setErrorCode(Constants.CSV_LENGTH_ERROR);
    }
}
