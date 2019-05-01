package xz.fzu.dao;

import xz.fzu.model.RecommendResult;

import java.util.List;

public interface IRecommendResultDao {

    /**
     * @param recommendResult
     * @return void
     * @author Murphy
     * @date 2019/5/1 0:37
     * @description 插入一条记录
     */
    public void insertInstance(RecommendResult recommendResult);

    /**
     * @param userId
     * @return java.util.List<xz.fzu.model.RecommendResult>
     * @author Murphy
     * @date 2019/5/1 0:38
     * @description 获得许多条记录
     */
    public List<RecommendResult> getListResult(String userId);

    /**
     * @param
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:41
     * @description 删除所有的记录
     */
    public void deleteAll();
}
