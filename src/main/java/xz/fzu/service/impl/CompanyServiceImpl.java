package xz.fzu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xz.fzu.dao.ICompanyDao;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.SHA;
import xz.fzu.util.TokenUtil;

import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/25 19:31
 */
@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyDao iCompanyDao;
    @Autowired
    IVerificationCodeService iVerificationCodeService;

    @Override
    public void register(Company company, int code) throws ValidationExceprion, NoVerfcationCodeException {

        iVerificationCodeService.validateCode(company.getEmail(), code);
        // 设置uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        company.setCompanyId(uuid);
        //  加密密码
        company.setPasswd(SHA.encrypt(company.getPasswd()));
        iCompanyDao.insertInstance(company);
    }

    @Override
    public Company getInfo(String token) throws UserNotFoundException {
        String userId = iCompanyDao.selectIdByToken(token);
        Company company = iCompanyDao.selectCompanyById(userId);
        if (company.getCompanyId() == null) {
            throw new UserNotFoundException();
        }
        company.setPasswd(null);
        return company;
    }

    @Override
    public Company getInfo(String companyId, int a) throws UserNotFoundException {
        Company company = iCompanyDao.selectCompanyById(companyId);
        if (company.getCompanyId() == null) {
            throw new UserNotFoundException();
        }
        company.setPasswd(null);
        return company;
    }

    @Override
    public void updateInfoByToken(Company company, String token) throws TokenExpiredException {
        TokenUtil.verify(token);
        if (iCompanyDao.verifyToken(token) == 0) {
            throw new TokenExpiredException();
        }
        iCompanyDao.updateInfo(company);
    }

    @Override
    public String login(String email, String passwd) throws PasswordErrorException {
        passwd = SHA.encrypt(passwd);
        if (iCompanyDao.loginWithPasswd(email, passwd) == 0) {
            throw new PasswordErrorException();
        }
        String companId = iCompanyDao.selectIdByEmail(email);
        String token = TokenUtil.createToken(companId, passwd);
        Company company = new Company();
        company.setCompanyId(companId);
        company.setToken(token);
        updateToken(company);
        return token;
    }

    @Override
    public String loginWithToken(String token) throws TokenExpiredException {
        if (iCompanyDao.verifyToken(token) == 0) {
            throw new TokenExpiredException();
        }
        return token;
    }

    @Override
    public void resetPasswd(String email, String passwd) throws TokenExpiredException {
        passwd = SHA.encrypt(passwd);
        Company company = iCompanyDao.selectCompanyByEmail(email);
        company.setPasswd(passwd);
        updateInfoByToken(company, company.getToken());
    }

    @Override
    public void updateToken(Company company) {
        iCompanyDao.updateInfo(company);
    }

    @Override
    public Company getInstaceByToken(String token) throws TokenExpiredException, UserNotFoundException {
        String companyId = iCompanyDao.selectIdByToken(token);
        if (companyId == null) {
            throw new TokenExpiredException();
        }
        return getInfo(companyId);
    }

    @Override
    public String updatePasswd(String token, String oldPasswd, String newPasswd) throws TokenExpiredException, PasswordErrorException {
        if (iCompanyDao.verifyToken(token) == 0) {
            throw new TokenExpiredException();
        }
        oldPasswd = SHA.encrypt(oldPasswd);
        newPasswd = SHA.encrypt(newPasswd);
        int affectRow = iCompanyDao.updatePasswd(token, oldPasswd, newPasswd);
        if (affectRow == 0) {
            throw new PasswordErrorException();
        }
        String userId = iCompanyDao.selectIdByToken(token);
        return TokenUtil.createToken(userId, newPasswd);
    }
}
