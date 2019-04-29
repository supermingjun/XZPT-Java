package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeDao;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.Resume;
import xz.fzu.service.IResumeService;
import xz.fzu.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 0:18
 */
@Service
public class ResumeServiceImpl implements IResumeService {

    @Resource
    IResumeDao iResumeDao;

    @Override
    public void insertResume(String token, Resume resume) throws TokenExpiredException, EvilIntentions {

        String userId = getUserId(token);
        resume.setUserId(userId);
        iResumeDao.insertInstance(resume);
    }

    @Override
    public void updateResume(String token, Resume resume) throws EvilIntentions, TokenExpiredException {

        String userId = getUserId(token);
        resume.setUserId(userId);
        iResumeDao.updateInstance(resume);
    }

    @Override
    public List<Resume> getListResume(String userId) {

        iResumeDao.selectListByUserId(userId);
        return null;
    }

    @Override
    public int deleteResume(int resumeId) {
        return 0;
    }

    @Override
    public Resume getResume(int resumeId) {
        return null;
    }

    @Resource
    IUserService iUserService;

    private String getUserId(String token) throws TokenExpiredException {
        String userId = iUserService.verifyToken(token);
        return userId;
    }
}
