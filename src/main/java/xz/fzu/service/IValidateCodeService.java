package xz.fzu.service;

import org.apache.commons.mail.EmailException;

public interface IValidateCodeService {
    int sendValidateCode(String email) throws EmailException;

    boolean validateCode(String email, int code);
}
