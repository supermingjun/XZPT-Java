package xz.fzu.service;

import xz.fzu.model.RecommendResult;

import java.util.List;

public interface IRecommendService {

    /**
     * @param recommendResults
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:42
     * @description 插入一个实例
     */
    public void insertInstance(List<RecommendResult> recommendResults);

    /**
     * @param userId
     * @return java.util.List<xz.fzu.model.RecommendResult>
     * @author Murphy
     * @date 2019/5/1 12:42
     * @description 获得实例的集合
     */
    public List<RecommendResult> getListResult(String userId);

    /**
     * @param
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:42
     * @description 删除所有的实例
     */
    public void deleteAll();
}
