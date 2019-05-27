package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeDeliveryDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public void deliveryResume(String userId, Long resumeId, Long recruitmentId) {

        ResumeDelivery resumeDelivery = new ResumeDelivery();
        resumeDelivery.setRecruitmentId(recruitmentId);
        resumeDelivery.setResumeId(resumeId);
        resumeDelivery.setUserId(userId);
        resumeDelivery.setDeliveryStatus(1L);
        iResumeDeliveryDao.insert(resumeDelivery);

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
    public ResumeDelivery getResumeDeliveryRecordById(Long resumeDeliveryId) throws InstanceNotExistException {

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

    }

    @Override
    public List<ResumeDelivery> getAllRecord() {

        return iResumeDeliveryDao.mySelectAll();
    }

    @Override
    public List<ResumeDelivery> getRecordByUserId(String userId, PageData<ResumeDelivery> pageData) throws InstanceNotExistException {
        List<ResumeDelivery> list = iResumeDeliveryDao.selectAll();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getUserId().equals(userId) || list.get(i).getDeliveryStatus() != 0) {
                list.remove(i);
                i--;
            }
        }
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }
        int start = (pageData.getCurrentPage() - 1) * pageData.getPageSize();
        int end = pageData.getPageSize() + start - 1;
        List<ResumeDelivery> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i >= start && i <= end) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
