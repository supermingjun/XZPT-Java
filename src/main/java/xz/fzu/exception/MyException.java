package xz.fzu.exception;

/**
 * @author Murphy
 * @date 2019/4/24 15:13
 */
public abstract class MyException extends Exception {
    //    protected static final int errorCode;
    public int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(String value) {
        super(value);
    }
}
