package xz.fzu.service;

import xz.fzu.model.RecommendResult;

import java.util.List;

/**
 * 推荐信息相关的Service接口
 * @author Murphy
 */
public interface IRecommendService {

    /**
     * 插入一个实例
     * @param recommendResults 推荐结果
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:42
     */
    void insertInstance(List<RecommendResult> recommendResults);

    /**
     * 获得实例的集合
     * @param userId 用户id
     * @return java.util.List<xz.fzu.model.RecommendResult>
     * @author Murphy
     * @date 2019/5/1 12:42
     */
    List<RecommendResult> getListResult(String userId);

    /**
     * 删除所有的实例
     * @return void
     * @author Murphy
     * @date 2019/5/1 12:42
     */
    void deleteAll();
}
