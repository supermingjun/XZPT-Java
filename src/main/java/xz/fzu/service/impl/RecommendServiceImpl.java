package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.mapper.RecommendResultMapper;
import xz.fzu.model.RecommendResult;
import xz.fzu.service.IRecommendService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/1 12:44
 */
@Service
public class RecommendServiceImpl implements IRecommendService {

    @Resource
    RecommendResultMapper recommendResultMapper;

    @Override
    public void insertInstance(List<RecommendResult> recommendResults) {
        for (RecommendResult recommendResult : recommendResults) {
            recommendResultMapper.insertInstance(recommendResult);
        }
    }

    @Override
    public List<RecommendResult> getListResult(String userId) {
        return recommendResultMapper.getListResult(userId);
    }

    @Override
    public void deleteAll() {
        recommendResultMapper.deleteAll();
    }
}
