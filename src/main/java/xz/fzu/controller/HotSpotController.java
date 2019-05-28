package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.algorithm.GeneratePopularPost;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.HotPost;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.model.User;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/5 1:42
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

    @Resource
    IUserService iUserService;
    /**
     * 获取热度算法的接口
     *
     * @param pageData 分页数据
     * @return xz.fzu.vo.ResponseVO<java.util.List < xz.fzu.vo.Recruitment>>
     * @author Murphy
     * @date 2019/5/5 3:12
     */
    @RequestMapping(value = "/user/gethotspot", method = RequestMethod.POST)
    public ResponseVO<List<Recruitment>> deliveryResume(@RequestParam String token, @RequestBody PageData<Recruitment> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseVO<List<Recruitment>> responseVO = new ResponseVO<>();
        if (hotPosts != null) {
            List<Recruitment> list;
            List<Long> recruitmentIds = new ArrayList<>();
            for (HotPost hotPost : hotPosts) {
                recruitmentIds.add(hotPost.getRecruitmentId());
            }
            User user = iUserService.selectUserByToken(token);
            list = iRecruitmentService.getRecruitmentByIds(user.getIndustryLabel(), recruitmentIds, pageData);
            responseVO.setResultObject(list);
        }

        return responseVO;
    }
}
