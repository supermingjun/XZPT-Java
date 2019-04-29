package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IRecruitmentDao;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Recruitment;
import xz.fzu.service.IRecruitmentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/27 10:51
 */
@Service
public class RecruitmentServiceImpl implements IRecruitmentService {

    @Resource
    IRecruitmentDao iRecruitmentDao;

    @Override
    public void insertRecruitment(Recruitment recruitment) {
        iRecruitmentDao.insertInstance(recruitment);
    }

    @Override
    public Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException {
        Recruitment recruitment = iRecruitmentDao.selectInstaceById(recruitmentId);
        if (recruitment == null) {
            throw new InstanceNotExistException();
        }
        return recruitment;
    }

    @Override
    public List<Recruitment> getListRecruitmentByCompanyId(String companyId) throws InstanceNotExistException {
        List<Recruitment> recruitmentList = iRecruitmentDao.selectListInstaceByCompanyId(companyId);
        if (recruitmentList == null) {
            throw new InstanceNotExistException();
        }
        return recruitmentList;
    }

    @Override
    public void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions {
        Recruitment tempRecruitment = iRecruitmentDao.selectInstaceById(recruitmentId);
        if (tempRecruitment == null || !tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        iRecruitmentDao.deleteInstace(recruitmentId);
    }

    @Override
    public void updateRecruitment(Recruitment recruitment, String companyId) throws EvilIntentions {
        Recruitment tempRecruitment = iRecruitmentDao.selectInstaceById(recruitment.getRecruitmentId());
        if (!tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        iRecruitmentDao.updateInstace(recruitment);
    }

    @Override
    public List<Recruitment> getListRecruitmentByKeyWord(String keyWord) throws InstanceNotExistException {
        List<Recruitment> recruitmentList = iRecruitmentDao.selectInstanceByKeyWord(keyWord);
        if (recruitmentList == null) {
            throw new InstanceNotExistException();
        }
        return recruitmentList;
    }
}
