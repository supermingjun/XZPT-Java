package xz.fzu.exception;

/**
 * @author Murphy
 * @date 2019/4/24 15:13
 */
public abstract class AbstractException extends Exception {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public AbstractException(String value) {
        super(value);
    }
}
