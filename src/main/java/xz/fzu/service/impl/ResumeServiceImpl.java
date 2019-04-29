package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeDao;
import xz.fzu.model.Resume;
import xz.fzu.service.IResumeService;

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
    public void insertResume(Resume resume) {

    }

    @Override
    public void updateResume(Resume resume) {

    }

    @Override
    public List<Resume> getListResume(String userId) {
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
}
