package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeDeliveryDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 19:10
 */
@Service
public class ResumeDeliveryImpl implements IResumeDeliveryService {

    @Resource
    IResumeDeliveryDao iResumeDeliveryDao;

    /**
     * TODO 安全认证，暂时没有
     */
    @Override
    public void deliveryResume(String userId, int resumeId, int recruitmentId) {

        ResumeDelivery resumeDelivery = new ResumeDelivery();
        resumeDelivery.setRecruitmentId(recruitmentId);
        resumeDelivery.setResumeId(resumeId);
        resumeDelivery.setUserId(userId);
        resumeDelivery.setDeliveryStatus(1);
        iResumeDeliveryDao.insertInstance(resumeDelivery);

    }

    @Override
    public List<ResumeDelivery> userGetResumeDeliveryRecord(String userId, PageData pageData) throws InstanceNotExistException {

        List<ResumeDelivery> list = iResumeDeliveryDao.userGetListInstance(userId,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (list == null) {
            throw new InstanceNotExistException();
        }

        return list;
    }

    @Override
    public List<ResumeDelivery> companyGetResumeDeliveryRecord(String comapnyId, PageData pageData) throws InstanceNotExistException {

        List<ResumeDelivery> list = iResumeDeliveryDao.companyGetListInstance(comapnyId,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }

        return list;
    }

    @Override
    public ResumeDelivery getResumeDeliveryRecordByResume(int resumeId) throws InstanceNotExistException {

        ResumeDelivery resumeDelivery = iResumeDeliveryDao.resumeGetListInstance(resumeId);
        if (resumeDelivery == null) {
            throw new InstanceNotExistException();
        }

        return resumeDelivery;
    }

    @Override
    public ResumeDelivery getResumeDeliveryRecordById(int resumeDeliveryId) throws InstanceNotExistException {

        ResumeDelivery resumeDelivery = iResumeDeliveryDao.getInstance(resumeDeliveryId);
        if (resumeDelivery == null) {
            throw new InstanceNotExistException();
        }

        return resumeDelivery;
    }

    @Override
    public void deleteResumeDeliveryRecord(int resumeDeliveryId) throws InstanceNotExistException {

        int affectRow = iResumeDeliveryDao.deleteInstance(resumeDeliveryId);
        if (affectRow == 0) {
            throw new InstanceNotExistException();
        }

    }

    @Override
    public void updateResumeDeliveryRecord(ResumeDelivery resumeDelivery) {

        if (iResumeDeliveryDao.updateInstance(resumeDelivery) == 0) {
            throw new RuntimeException("数据没有更新" + resumeDelivery.getDeliveryStatus() + "，Id是" + resumeDelivery.getResumeDeliveryId() + "。");
        }
        ;

    }
}
