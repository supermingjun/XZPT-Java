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
import xz.fzu.service.ICompanyService;
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

    @Resource
    ICompanyService iCompanyService;
    private Thread thread = null;
    @Autowired
    public HotSpotController() {
        runThread();
    }

    @Resource
    IUserService iUserService;

    /***
     * 热点服务后台线程
     * @author Murphy
     * @date 2019/5/30 16:46
     * @param
     * @return void
     */
    public void runThread() {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(() -> {
            while (true) {
                GeneratePopularPost generatePopularPost = new GeneratePopularPost();
                List<ResumeDelivery> resumeDeliveries = iResumeDeliveryService.getAllRecord();
                List<Recruitment> recruitments = iRecruitmentService.getRecruitmentByResumeDeliveries(resumeDeliveries);
                hotPosts = generatePopularPost.generatePopularPostRank(resumeDeliveries,recruitments);
                try {
                    Thread.sleep(1000 * 60 * 60 * 6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

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

            for (Recruitment recruitment : list) {
                setCompanyName(recruitment);
            }
            responseVO.setResultObject(list);
        }

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
