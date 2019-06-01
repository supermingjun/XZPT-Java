package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.mapper.ResumeDeliveryMapper;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.util.PageUtil;
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
    ResumeDeliveryMapper resumeDeliveryMapper;

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
        resumeDeliveryMapper.insert(resumeDelivery);

    }

    @Override
    public List<ResumeDelivery> userGetResumeDeliveryRecord(String userId, int status, PageData pageData) throws InstanceNotExistException {

        List<ResumeDelivery> list = resumeDeliveryMapper.userGetListInstance(userId, status,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (list == null || list.size() == 0) {
            throw new InstanceNotExistException();
        }

        return list;
    }

    @Override
    public List<ResumeDelivery> companyGetResumeDeliveryRecord(String companyId, int statusA, int statusB, PageData<ResumeDelivery> pageData) throws InstanceNotExistException {

        List<ResumeDelivery> list = resumeDeliveryMapper.companyGetListInstance(companyId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDeliveryStatus() > statusB ||
                    list.get(i).getDeliveryStatus() < statusA) {
                list.remove(i);
                i--;
            }
        }
        PageUtil.paging(list, pageData);

        return list;
    }

    @Override
    public ResumeDelivery getResumeDeliveryRecordByResume(int resumeId) throws InstanceNotExistException {

        ResumeDelivery resumeDelivery = resumeDeliveryMapper.resumeGetListInstance(resumeId);
        if (resumeDelivery == null) {
            throw new InstanceNotExistException();
        }

        return resumeDelivery;
    }

    @Override
    public ResumeDelivery getResumeDeliveryRecordById(Long resumeDeliveryId) throws InstanceNotExistException {

        ResumeDelivery resumeDelivery = resumeDeliveryMapper.getInstance(resumeDeliveryId);
        if (resumeDelivery == null) {
            throw new InstanceNotExistException();
        }

        return resumeDelivery;
    }

    @Override
    public void deleteResumeDeliveryRecord(int resumeDeliveryId) throws InstanceNotExistException {

        int affectRow = resumeDeliveryMapper.deleteInstance(resumeDeliveryId);
        if (affectRow == 0) {
            throw new InstanceNotExistException();
        }

    }

    @Override
    public void updateResumeDeliveryRecord(ResumeDelivery resumeDelivery) {

        if (resumeDeliveryMapper.updateInstance(resumeDelivery) == 0) {
            throw new RuntimeException("数据没有更新" + resumeDelivery.getDeliveryStatus() + "，Id是" + resumeDelivery.getResumeDeliveryId() + "。");
        }

    }

    @Override
    public List<ResumeDelivery> getAllRecord() {

        return resumeDeliveryMapper.mySelectAll();
    }

    @Override
    public List<ResumeDelivery> getRecordByUserId(String userId, PageData<ResumeDelivery> pageData) throws InstanceNotExistException {
        List<ResumeDelivery> list = resumeDeliveryMapper.selectAll();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getUserId().equals(userId)) {
                list.remove(i);
                i--;
            }
        }

        return PageUtil.paging(list, pageData);
    }
}
