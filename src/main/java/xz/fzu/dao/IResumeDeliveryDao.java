package xz.fzu.dao;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 15:25
 */
public interface IResumeDeliveryDao {

    /**
     * @param resumeDelivery
     * @return void
     * @author Murphy
     * @date 2019/4/30 15:26
     * @description 插入一条记录
     */
    public void insertInstance(ResumeDelivery resumeDelivery);

    /**
     * @param resumeDeliveryId
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/4/30 15:31
     * @description 根据投递记录id获得投递记录
     */
    public ResumeDelivery getInstance(int resumeDeliveryId) throws InstanceNotExistException;

    /**
     * @param userId
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     * @description 用户获得投递记录
     */
    public List<ResumeDelivery> userGetListInstance(String userId, int pageIndex, int pageSize);

    /**
     * @param resumeId
     * @return jResumeDelivery
     * @author Murphy
     * @date 2019/4/30 15:32
     * @description 根据简历获得投递记录
     */
    public ResumeDelivery resumeGetListInstance(int resumeId);

    /**
     * @param recruitmentId
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     * @description 企业获得投递记录
     */
    public List<ResumeDelivery> companyGetListInstance(String recruitmentId, int pageIndex, int pageSize);

    /**
     * @param resumeDeliveryId
     * @return int
     * @author Murphy
     * @date 2019/4/30 15:32
     * @description 根据投递记录id删除记录
     */
    public int deleteInstance(int resumeDeliveryId);

    /**
     * @param resumeDelivery
     * @return void
     * @author Murphy
     * @date 2019/4/30 15:33
     * @description 更新投递记录
     */
    public void updateInstance(ResumeDelivery resumeDelivery);

}
