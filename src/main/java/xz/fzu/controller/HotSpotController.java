package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.heatalgorithm.GeneratePopularPost;
import xz.fzu.model.HotPost;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.vo.RecruitmentVO;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/51:42
 */
@RestController
public class HotSpotController {

    @Resource
    IResumeDeliveryService iResumeDeliveryService;
    @Resource
    IRecruitmentService iRecruitmentService;
    private List<HotPost> hotPosts;

    @Autowired
    public HotSpotController() {
        new Thread(() -> {
            while (true) {
                GeneratePopularPost generatePopularPost = new GeneratePopularPost();
                List<ResumeDelivery> resumeDeliveries = iResumeDeliveryService.getAllRecord();
                hotPosts = generatePopularPost.generatePopularPostRank(resumeDeliveries);
                try {
                    Thread.sleep(1000 * 60 * 60 * 6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取热度算法的接口
     *
     * @param token token
     * @return xz.fzu.vo.ResponseData<java.util.List < xz.fzu.vo.RecruitmentVO>>
     * @author Murphy
     * @date 2019/5/5 3:12
     */
    @RequestMapping(value = "/user/gethotspot", method = RequestMethod.POST)
    public ResponseData<List<RecruitmentVO>> deliveryResume(@RequestParam String token) throws TokenExpiredException, EvilIntentions {

        ResponseData<List<RecruitmentVO>> responseData = new ResponseData<>();
        if (hotPosts != null) {
            List<RecruitmentVO> list = new ArrayList<>();
            for (HotPost hotPost : hotPosts) {
                try {
                    list.add(iRecruitmentService.getRecruitmentById(hotPost.getRecruitmentId()));
                } catch (Exception e) {

                }
            }
            responseData.setResultObject(list);
        }
        return responseData;
    }
}
