package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IRecruitmentDao;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Recruitment;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.vo.PageData;

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
//        iRecruitmentDao.insertInstance(recruitment);
        iRecruitmentDao.insert(recruitment);
    }

    @Override
    public Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException {
        xz.fzu.model.Recruitment recruitment = iRecruitmentDao.selectInstaceById(recruitmentId);
        if (recruitment == null) {
            throw new InstanceNotExistException();
        }
        return recruitment;
    }

    @Override
    public List<Recruitment> getListRecruitmentByCompanyId(String companyId, PageData pageData) throws InstanceNotExistException {
        List<xz.fzu.model.Recruitment> recruitmentList = iRecruitmentDao.selectListInstanceByCompanyId(companyId,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (recruitmentList.size() == 0) {
            throw new InstanceNotExistException();
        }

        return recruitmentList;
    }

    @Override
    public void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions {
        xz.fzu.model.Recruitment tempRecruitment = iRecruitmentDao.selectInstaceById(recruitmentId);
        if (tempRecruitment == null || !tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        iRecruitmentDao.deleteInstance(recruitmentId);
    }

    @Override
    public void updateRecruitment(xz.fzu.model.Recruitment recruitment, String companyId) throws EvilIntentions {
        xz.fzu.model.Recruitment tempRecruitment = iRecruitmentDao.selectInstaceById(recruitment.getRecruitmentId());
        if (!tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        iRecruitmentDao.updateInstance(recruitment);
    }

    @Override
    public List<Recruitment> getListRecruitmentByKeyWord(String keyWord, PageData requestPage) throws InstanceNotExistException {
        List<xz.fzu.model.Recruitment> recruitmentList = iRecruitmentDao.selectInstanceByKeyWord('%' + keyWord + '%', (requestPage.getCurrentPage() - 1) * requestPage.getPageSize(), requestPage.getPageSize());
        if (recruitmentList == null) {
            throw new InstanceNotExistException();
        }

        return recruitmentList;
    }
}
