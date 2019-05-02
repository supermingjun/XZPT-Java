package xz.fzu.dao;

import xz.fzu.model.Resume;

import java.util.List;

public interface IResumeDao {

    /**
     * @param resume
     * @return xz.fzu.model.Resume
     * @author Murphy
     * @date 2019/4/27 22:33
     * @description 插入一条实例
     */
    public void insertInstance(Resume resume);

    /**
     * @param resumeId
     * @return int
     * @author Murphy
     * @date 2019/4/27 22:34
     * @description 根据简历Id删除实例
     */
    public int deleteInstance(int resumeId);

    /**
     * @param resume
     * @return void
     * @author Murphy
     * @date 2019/4/27 22:50
     * @description 更新实例
     */
    public void updateInstance(Resume resume);

    /**
     * @param resumeId
     * @return void
     * @author Murphy
     * @date 2019/4/27 22:52
     * @description 查实例，应当先检查用户有无权限
     */
    public Resume selectInstanceByResumeId(int resumeId);

    /**
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return java.util.List<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/27 22:53
     * @description 查看用户的所有实例
     */
    public List<Resume> selectListByUserId(String userId, int pageIndex, int pageSize);

    /**
     * @param resumeId
     * @return int
     * @author Murphy
     * @date 2019/5/2 14:30
     * @description 复制一个实例并返回主键值
     */
    public int copyInstance(int resumeId);
}
