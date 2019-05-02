package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.algorithm.RecomAlgorithm;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.RecommendResult;
import xz.fzu.model.Recruitment;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.service.IProfileService;
import xz.fzu.service.IRecommendService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @title: RecommendController
 * @projectName XZPT-Java
 * @description: 推荐算法相关的控制器
 * @date 2019/4/30 19:29
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class RecommendController {

    @Resource
    RecomAlgorithm recomAlgorithm;
    @Resource
    IProfileService iProfileService;
    @Resource
    IUserService iUserService;

    @Resource
    IRecruitmentService iRecruitmentService;
    @Autowired
    public RecommendController(RecomAlgorithm recomAlgorithm, IProfileService iProfileService, IRecommendService iRecommendService) {
        this.iProfileService = iProfileService;
        this.iRecommendService = iRecommendService;
        this.recomAlgorithm = recomAlgorithm;
        new Thread(() -> {
            while (true) {
                iRecommendService.deleteAll();
                for (String string : iProfileService.selectUserId()) {

                    List<RecruitmentProfile> recruitmentProfiles = iProfileService.getRecruitmentProfile();
                    List<RecommendResult> recommendResults = recomAlgorithm.recomAlgorithm(iProfileService.getUserProfile(string), recruitmentProfiles, 10);
                    iRecommendService.insertInstance(recommendResults);
                }
                try {
                    Thread.sleep(1000 * 60 * 60 * 6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Resource
    IRecommendService iRecommendService;
    /**
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/29 21:53
     * @description 推荐接口
     */
    @RequestMapping(value = "/getrecommend", method = RequestMethod.POST)
    public ResponseData<List<Recruitment>> getRecommend(@RequestParam String token) throws TokenExpiredException, InstanceNotExistException {

        ResponseData<List<Recruitment>> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        List<RecommendResult> recruitmentProfiles = iRecommendService.getListResult(userId);
        List<Recruitment> list = new ArrayList<>();
        for (RecommendResult recommendResult : recruitmentProfiles) {
            int recruitmentId = recommendResult.getRecruitmentId();
            Recruitment recruitment = iRecruitmentService.getRecruitmentById(recruitmentId);
            list.add(recruitment);
        }
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }
        responseData.setResultObject(list);

        return responseData;
    }
}
