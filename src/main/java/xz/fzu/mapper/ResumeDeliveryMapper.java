package xz.fzu.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeDelivery;

import java.util.List;

/**
 * 简历投递相关的dao
 *
 * @author Murphy
 * @date 2019/4/30 15:25
 */
@Repository
public interface ResumeDeliveryMapper extends Mapper<ResumeDelivery> {

    /**
     * 插入一条记录
     *
     * @param resumeDelivery 简历投递记录
     * @return void
     * @author Murphy
     * @date 2019/4/30 15:26
     */
//    void insertInstance(ResumeDelivery resumeDelivery);

    /**
     * 根据投递记录id获得投递记录
     *
     * @param resumeDeliveryId 简历投递的id
     * @return xz.fzu.model.ResumeDelivery
     * @throws InstanceNotExistException 实例未找到异常
     * @author Murphy
     * @date 2019/4/30 15:31
     */
    ResumeDelivery getInstance(Long resumeDeliveryId) throws InstanceNotExistException;

    /**
     * 用户获得投递记录
     *
     * @param userId    用户id
     * @param status    简历状态
     * @param pageIndex 页码
     * @param pageSize  页大小
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    List<ResumeDelivery> userGetListInstance(String userId, int status, int pageIndex, int pageSize);

    /**
     * 根据简历获得投递记录
     *
     * @param resumeId 简历id
     * @return jResumeDelivery
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    ResumeDelivery resumeGetListInstance(int resumeId);

    /**
     * 企业获得投递记录
     *
     * @param companyId 公司id
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    List<ResumeDelivery> companyGetListInstance(String companyId);

    /**
     * 根据投递记录id删除记录
     *
     * @param resumeDeliveryId 简历投递记录的id
     * @return int
     * @author Murphy
     * @date 2019/4/30 15:32
     */
    int deleteInstance(int resumeDeliveryId);

    /**
     * 更新投递记录
     *
     * @param resumeDelivery 简历投递的实例
     * @return int
     * @author Murphy
     * @date 2019/4/30 15:33
     */
    int updateInstance(ResumeDelivery resumeDelivery);

    /**
     * 获得所有的投递记录
     *
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/5/5 2:01
     */
    List<ResumeDelivery> mySelectAll();
}
