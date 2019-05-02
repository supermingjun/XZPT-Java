package xz.fzu.service;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * @author Murphy
 * @title: IResumeDeliveryService
 * @projectName XZPT-Java
 * @description: 简历投递相关的Service接口
 */
public interface IResumeDeliveryService {

    /**
     * 投递一个简历
     * @param userId 用户id
     * @param resumeId 简历id
     * @param recruitmentId 招聘信息id
     * @return void
     * @author Murphy
     * @date 2019/4/30 16:24
     */
    public void deliveryResume(String userId, int resumeId, int recruitmentId);

    /**
     * 用户获得获得简历投递记录
     * @param userId 用户id
     * @param pageData 页面数据
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 19:02
     * @throws InstanceNotExistException 未找到实例
     */
    public List<ResumeDelivery> userGetResumeDeliveryRecord(String userId, PageData pageData) throws InstanceNotExistException;

    /**
     * 公司获得简历投递记录
     * @param comapnyId 企业id
     * @param pageData 页面数据
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 19:04
     * @throws InstanceNotExistException 未找到实例
     */
    public List<ResumeDelivery> companyGetResumeDeliveryRecord(String comapnyId, PageData pageData) throws InstanceNotExistException;

    /**
     * 根据简历获得投递记录
     * @param resumeId 简历id
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 19:06
     * @throws InstanceNotExistException 未找到实例
     */
    public ResumeDelivery getResumeDeliveryRecordByResume(int resumeId) throws InstanceNotExistException;

    /**
     * 根据投递记录id获得投递记录
     * @param resumeDeliveryId 简历投递的id
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 19:07
     * @throws InstanceNotExistException 未找到实例
     */
    public ResumeDelivery getResumeDeliveryRecordById(int resumeDeliveryId) throws InstanceNotExistException;


    /**
     * 删除简历投递记录
     * @param resumeDeliveryId 简历投递的id
     * @return int
     * @author Murphy
     * @date 2019/4/30 19:09
     * @throws InstanceNotExistException 未找到实例
     */
    public void deleteResumeDeliveryRecord(int resumeDeliveryId) throws InstanceNotExistException;

    /**
     * 更新简历信息
     * @param resumeDelivery 简历投递的实例
     * @return void
     * @author Murphy
     * @date 2019/4/30 19:09
     */
    public void updateResumeDeliveryRecord(ResumeDelivery resumeDelivery);

}
