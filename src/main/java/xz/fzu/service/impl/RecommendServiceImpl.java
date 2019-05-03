package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IRecommendResultDao;
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
    IRecommendResultDao iRecommendResultDao;

    @Override
    public void insertInstance(List<RecommendResult> recommendResults) {
        for (RecommendResult recommendResult : recommendResults) {
            iRecommendResultDao.insertInstance(recommendResult);
        }
    }

    @Override
    public List<RecommendResult> getListResult(String userId) {
        return iRecommendResultDao.getListResult(userId);
    }

    @Override
    public void deleteAll() {
        iRecommendResultDao.deleteAll();
    }
}
