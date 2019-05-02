package xz.fzu.dao;

import xz.fzu.model.RecommendResult;

import java.util.List;

/**
 * @author Murphy
 * @title: IRecommendResultDao
 * @projectName XZPT-Java
 * @description: 推荐结果相关的dao
 */
public interface IRecommendResultDao {

    /**
     * 插入一条推荐记录
     * @param recommendResult 推荐结果
     * @return void
     * @author Murphy
     * @date 2019/5/1 0:37
     */
    public void insertInstance(RecommendResult recommendResult);

    /**
     * 获得许多条记录
     * @param userId 用户的id
     * @return java.util.List<xz.fzu.model.RecommendResult>
     * @author Murphy
     * @date 2019/5/1 0:38
     */
    public List<RecommendResult> getListResult(String userId);

    /**
     * 删除所有的记录
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:41
     */
    public void deleteAll();
}
