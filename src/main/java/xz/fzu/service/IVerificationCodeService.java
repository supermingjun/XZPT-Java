package xz.fzu.service;

import org.apache.commons.mail.EmailException;
import xz.fzu.exception.NoVerfcationCodeException;
import xz.fzu.exception.ValidationExceprion;

public interface IVerificationCodeService {
    int sendValidateCode(String email) throws EmailException;

    boolean verifyCode(String email, int code) throws ValidationExceprion, NoVerfcationCodeException;
}
