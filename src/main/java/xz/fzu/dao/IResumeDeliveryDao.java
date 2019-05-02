package xz.fzu.dao;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;

import java.util.List;

/**
 * @author Murphy
 * @title: IResumeDao
 * @projectName XZPT-Java
 * @description: 简历投递相关的dao
 * @date 2019/4/30 15:25
 */
public interface IResumeDeliveryDao {

    /**
     * 插入一条记录
     * @param resumeDelivery 简历投递记录
     * @return void
     * @author Murphy
     * @date 2019/4/30 15:26
     */
    public void insertInstance(ResumeDelivery resumeDelivery);

    /**
     * 根据投递记录id获得投递记录
     * @param resumeDeliveryId 简历投递的id
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 15:31
     * @throws InstanceNotExistException 实例未找到异常
     */
    public ResumeDelivery getInstance(int resumeDeliveryId) throws InstanceNotExistException;

    /**
     * 用户获得投递记录
     * @param userId 用户id
     * @param pageIndex 页码
     * @param pageSize 页大小
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    public List<ResumeDelivery> userGetListInstance(String userId, int pageIndex, int pageSize);

    /**
     * 根据简历获得投递记录
     * @param resumeId 简历id
     * @return jResumeDelivery
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    public ResumeDelivery resumeGetListInstance(int resumeId);

    /**
     * 企业获得投递记录
     * @param recruitmentId 招聘信息的id
     * @param pageIndex 页码
     * @param pageSize 页大小
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    public List<ResumeDelivery> companyGetListInstance(String recruitmentId, int pageIndex, int pageSize);

    /**
     * 根据投递记录id删除记录
     * @param resumeDeliveryId 简历投递记录的id
     * @return int
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    public int deleteInstance(int resumeDeliveryId);

    /**
     * 更新投递记录
     * @param resumeDelivery 简历投递的实例
     * @return void
     * @author Murphy
     * @date 2019/4/30 15:33
     */
    public void updateInstance(ResumeDelivery resumeDelivery);

}
