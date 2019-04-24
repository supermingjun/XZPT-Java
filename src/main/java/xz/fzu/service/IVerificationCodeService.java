package xz.fzu.service;

import org.apache.commons.mail.EmailException;

public interface IVerificationCodeService {
    int sendValidateCode(String email) throws EmailException;

    boolean validateCode(String email, int code);
}
