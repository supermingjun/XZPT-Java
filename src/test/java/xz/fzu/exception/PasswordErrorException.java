package xz.fzu.exception;

/**
 * @author Murphy
 * @date 2019/4/24 14:48
 */
public class PasswordErrorException extends Exception {
    private PasswordErrorException(String name) {
        super(name);
    }

    public PasswordErrorException() {
        super("密码错误");
    }
}
