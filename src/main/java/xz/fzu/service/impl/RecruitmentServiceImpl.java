package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IRecruitmentDao;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Recruitment;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.RecruitmentVO;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public RecruitmentVO getRecruitmentById(long recruitmentId) throws InstanceNotExistException {
        Recruitment recruitment = iRecruitmentDao.selectInstaceById(recruitmentId);
        if (recruitment == null) {
            throw new InstanceNotExistException();
        }
        RecruitmentVO recruitmentVO = new RecruitmentVO(recruitment);
        return recruitmentVO;
    }

    @Override
    public List<RecruitmentVO> getListRecruitmentByCompanyId(String companyId, PageData pageData) throws InstanceNotExistException {
        List<Recruitment> recruitmentList = iRecruitmentDao.selectListInstanceByCompanyId(companyId,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (recruitmentList.size() == 0) {
            throw new InstanceNotExistException();
        }

        return listCast(recruitmentList);
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
    public List<RecruitmentVO> getListRecruitmentByKeyWord(String keyWord, PageData requestPage) throws InstanceNotExistException {
        List<Recruitment> recruitmentList = iRecruitmentDao.selectInstanceByKeyWord(keyWord, (requestPage.getCurrentPage() - 1) * requestPage.getPageSize(), requestPage.getPageSize());
        if (recruitmentList == null) {
            throw new InstanceNotExistException();
        }

        return listCast(recruitmentList);
    }

    private List<RecruitmentVO> listCast(List<Recruitment> recruitments) {
        List<RecruitmentVO> res = new ArrayList<>();
        for (Recruitment recruitment : recruitments) {
            res.add(new RecruitmentVO(recruitment));
        }

        return res;
    }
}
