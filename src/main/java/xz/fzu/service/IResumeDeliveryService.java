package xz.fzu.service;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.vo.PageData;

import java.util.List;

public interface IResumeDeliveryService {

    /**
     * @param userId
     * @param resumeId
     * @param recruitmentId
     * @return void
     * @author Murphy
     * @date 2019/4/30 16:24
     * @description 投递一个简历
     */
    public void deliveryResume(String userId, int resumeId, int recruitmentId);

    /**
     * @param
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 19:02
     * @description 用户获得获得简历投递记录
     */
    public List<ResumeDelivery> userGetResumeDeliveryRecord(String userId, PageData pageData) throws InstanceNotExistException;

    /**
     * @param comapnyId
     * @param pageData
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 19:04
     * @description 公司获得简历投递记录
     */
    public List<ResumeDelivery> companyGetResumeDeliveryRecord(String comapnyId, PageData pageData) throws InstanceNotExistException;

    /**
     * @param resumeId
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 19:06
     * @description 根据简历获得投递记录
     */
    public ResumeDelivery getResumeDeliveryRecordByResume(int resumeId) throws InstanceNotExistException;

    /**
     * @param resumeDeliveryId
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 19:07
     * @description 根据投递记录id获得投递记录
     */
    public ResumeDelivery getResumeDeliveryRecordById(int resumeDeliveryId) throws InstanceNotExistException;


    /**
     * @param resumeDeliveryId
     * @return int
     * @author Murphy
     * @date 2019/4/30 19:09
     * @description 删除简历投递记录
     */
    public void deleteResumeDeliveryRecord(int resumeDeliveryId) throws InstanceNotExistException;

    /**
     * @param resumeDelivery
     * @return void
     * @author Murphy
     * @date 2019/4/30 19:09
     * @description 更新简历信息
     */
    public void updateResumeDeliveryRecord(ResumeDelivery resumeDelivery);

}
