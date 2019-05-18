package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.model.Resume;

import java.util.List;

/**
 * 简历相关的dao
 *
 * @author Murphy
 */
@Repository
public interface IResumeDao  extends Mapper<Resume> {

    /**
     * 插入一条实例
     *
     * @param resume 简历的
     * @return xz.fzu.model.Resume
     * @author Murphy
     * @date 2019/4/27 22:33
     */
//    void insertInstance(Resume resume);

    /**
     * 根据简历Id删除实例
     *
     * @param resumeId 简历的id
     * @return int
     * @author Murphy
     * @date 2019/4/27 22:34
     */
    int deleteInstance(int resumeId);

    /**
     * 更新实例
     *
     * @param resume 简历实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 22:50
     */
    void updateInstance(Resume resume);

    /**
     * 查实例，应当先检查用户有无权限
     *
     * @param resumeId 简历id
     * @return void
     * @author Murphy
     * @date 2019/4/27 22:52
     */
    Resume selectInstanceByResumeId(int resumeId);

    /**
     * 查看用户的所有实例
     *
     * @param userId    用户的id
     * @param pageIndex 页面索引
     * @param pageSize  页大小
     * @return java.util.List<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/27 22:53
     */
    List<Resume> selectListByUserId(String userId, int pageIndex, int pageSize);

    /**
     * 复制一个实例并返回主键值
     *
     * @param resumeId 简历id
     * @return int
     * @author Murphy
     * @date 2019/5/2 14:30
     */
    void copyInstance(int resumeId);
}
