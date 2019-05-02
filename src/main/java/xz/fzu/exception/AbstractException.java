package xz.fzu.exception;

/**
 * @author Murphy
 * @date 2019/4/24 15:13
 */
public abstract class AbstractException extends Exception {
    //    protected static final int errorCode;
    public int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public AbstractException(String value) {
        super(value);
    }
}
