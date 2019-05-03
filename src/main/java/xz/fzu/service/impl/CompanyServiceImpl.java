package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.ICompanyDao;
import xz.fzu.exception.*;
import xz.fzu.model.Company;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IVerificationCodeService;
import xz.fzu.util.Sha;
import xz.fzu.util.TokenUtil;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/25 19:31
 */
@Service
public class CompanyServiceImpl implements ICompanyService {
    @Resource
    ICompanyDao iCompanyDao;
    @Resource
    IVerificationCodeService iVerificationCodeService;

    @Override
    public void register(Company company, int code) throws ValidationException, NoVerificationCodeException {

        iVerificationCodeService.verifyCode(company.getEmail(), code);
        // 设置uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        company.setCompanyId(uuid);
        //  加密密码
        company.setPasswd(Sha.encrypt(company.getPasswd()));
        iCompanyDao.insertInstance(company);
    }

    @Override
    public Company getInfoByCompanyId(String companyId) throws UserNotFoundException {

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
        passwd = Sha.encrypt(passwd);
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
    public String verifyToken(String token) throws TokenExpiredException {

        if (iCompanyDao.verifyToken(token) == 0) {
            throw new TokenExpiredException();
        }

        return token;
    }

    @Override
    public void resetPasswd(String email, String passwd) throws TokenExpiredException {
        passwd = Sha.encrypt(passwd);
        Company company = iCompanyDao.selectCompanyByEmail(email);
        company.setPasswd(passwd);
        updateInfoByToken(company, company.getToken());
    }

    @Override
    public void updateToken(Company company) {
        iCompanyDao.updateInfo(company);
    }

    @Override
    public Company getInfoByToken(String token) throws TokenExpiredException, UserNotFoundException {

        String companyId = iCompanyDao.selectIdByToken(token);
        if (companyId == null) {
            throw new TokenExpiredException();
        }

        return getInfoByCompanyId(companyId);
    }

    @Override
    public String updatePasswd(String token, String oldPasswd, String newPasswd) throws TokenExpiredException, PasswordErrorException {

        if (iCompanyDao.verifyToken(token) == 0) {
            throw new TokenExpiredException();
        }
        oldPasswd = Sha.encrypt(oldPasswd);
        newPasswd = Sha.encrypt(newPasswd);
        int affectRow = iCompanyDao.updatePasswd(token, oldPasswd, newPasswd);
        if (affectRow == 0) {
            throw new PasswordErrorException();
        }
        String companyId = iCompanyDao.selectIdByToken(token);

        return TokenUtil.createToken(companyId, newPasswd);
    }
}
