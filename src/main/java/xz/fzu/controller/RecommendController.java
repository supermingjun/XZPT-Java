package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.algorithm.RecommendAlgorithm;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.RecommendResult;
import xz.fzu.model.Recruitment;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.service.*;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐算法相关的控制器
 *
 * @author Murphy
 * @date 2019/4/30 19:29
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class RecommendController {

    @Resource
    RecommendAlgorithm recomAlgorithm;
    @Resource
    IProfileService iProfileService;
    @Resource
    IUserService iUserService;

    @Resource
    IRecruitmentService iRecruitmentService;
    @Resource
    IRecommendService iRecommendService;
    @Resource
    ICompanyService iCompanyService;
    @Autowired
    public RecommendController(RecommendAlgorithm recomAlgorithm, IProfileService iProfileService, IRecommendService iRecommendService) {
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

    /**
     * @param token token
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/29 21:53
     * @description 推荐接口
     */
    @RequestMapping(value = "/getrecommend", method = RequestMethod.POST)
    public ResponseVO<List<Recruitment>> getRecommend(@RequestParam String token) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO<List<Recruitment>> responseVO = new ResponseVO<>();
        String userId = iUserService.verifyToken(token);
        List<RecommendResult> recruitmentProfiles = iRecommendService.getListResult(userId);
        List<Recruitment> list = new ArrayList<>();
        for (RecommendResult recommendResult : recruitmentProfiles) {
            int recruitmentId = recommendResult.getRecruitmentId();
            Recruitment recruitment = iRecruitmentService.getRecruitmentById(recruitmentId);
            setCompanyName(recruitment);
            list.add(recruitment);
        }
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }
        responseVO.setResultObject(list);

        return responseVO;
    }

    /**
     * 设置招聘信息公司名字
     *
     * @param recruitmentVO 招聘信息
     * @return xz.fzu.vo.Recruitment
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void setCompanyName(Recruitment recruitmentVO) {

        String companyName = "公司不存在";
        try {

            companyName = iCompanyService.getInfoByCompanyId(recruitmentVO.getCompanyId()).getCompanyName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recruitmentVO.setCompanyName(companyName);
    }
}
