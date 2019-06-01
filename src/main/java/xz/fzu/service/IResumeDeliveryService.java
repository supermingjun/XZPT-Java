package xz.fzu.service;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * 简历投递相关的Service接口
 *
 * @author Murphy
 */
public interface IResumeDeliveryService {

    /**
     * 投递一个简历
     *
     * @param userId        用户id
     * @param resumeId      简历id
     * @param recruitmentId 招聘信息id
     * @return void
     * @author Murphy
     * @date 2019/4/30 16:24
     */
    void deliveryResume(String userId, Long resumeId, Long recruitmentId);

    /**
     * 用户获得获得简历投递记录
     *
     * @param userId   用户id
     * @param status   投递记录的状态
     * @param pageData 页面数据
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 19:02
     */
    List<ResumeDelivery> userGetResumeDeliveryRecord(String userId, int status, PageData pageData) throws InstanceNotExistException;

    /**
     * 公司获得简历投递记录
     *
     * @param comapnyId 企业id
     * @param statusA
     * @param statusB
     * @param pageData  页面数据
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 19:04
     */
    List<ResumeDelivery> companyGetResumeDeliveryRecord(String comapnyId, int statusA, int statusB, PageData<ResumeDelivery> pageData) throws InstanceNotExistException;

    /**
     * 根据简历获得投递记录
     *
     * @param resumeId 简历id
     * @return xz.fzu.model.ResumeDelivery
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 19:06
     */
    ResumeDelivery getResumeDeliveryRecordByResume(int resumeId) throws InstanceNotExistException;

    /**
     * 根据投递记录id获得投递记录
     *
     * @param resumeDeliveryId 简历投递的id
     * @return xz.fzu.model.ResumeDelivery
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 19:07
     */
    ResumeDelivery getResumeDeliveryRecordById(Long resumeDeliveryId) throws InstanceNotExistException;


    /**
     * 删除简历投递记录
     *
     * @param resumeDeliveryId 简历投递的id
     * @return int
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 19:09
     */
    void deleteResumeDeliveryRecord(int resumeDeliveryId) throws InstanceNotExistException;

    /**
     * 更新简历信息
     *
     * @param resumeDelivery 简历投递的实例
     * @return void
     * @author Murphy
     * @date 2019/4/30 19:09
     */
    void updateResumeDeliveryRecord(ResumeDelivery resumeDelivery);

    /**
     * 获得所有的投递记录
     *
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/5/5 2:03
     */
    List<ResumeDelivery> getAllRecord();

    /**
     * 根据用户id获得所有记录
     *
     * @param
     * @param userId 用户id
     * @param pageData 分页信息
     * @return void
     * @author Murphy
     * @date 2019/5/27 23:13
     * @throws InstanceNotExistException 没有找到用户记录就报错
     */
    List<ResumeDelivery> getRecordByUserId(String userId, PageData<ResumeDelivery> pageData) throws InstanceNotExistException;
}
