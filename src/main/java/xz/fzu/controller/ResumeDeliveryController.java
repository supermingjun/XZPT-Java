package xz.fzu.controller;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Resume;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.*;
import xz.fzu.util.PushUtil;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 简历投递相关的控制器
 *
 * @author Murphy
 * @date 2019/5/2 14:05
 */
@RestController
public class ResumeDeliveryController {

    @Resource
    IResumeDeliveryService iResumeDeliveryService;
    @Resource
    IUserService iUserService;
    @Resource
    IResumeService iResumeService;
    @Resource
    ICompanyService iCompanyService;
    @Resource
    IRecruitmentService iRecruitmentService;

    /**
     * 投递简历
     *
     * @param token         token
     * @param resumeId      简历id
     * @param recruitmentId 招聘信息id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 21:41
     */
    @RequestMapping(value = "/user/deliveryresume", method = RequestMethod.POST)
    public ResponseVO deliveryResume(@RequestParam String token, @RequestParam Long resumeId, @RequestParam Long recruitmentId) throws TokenExpiredException, EvilIntentions {

        ResponseVO responseVO = new ResponseVO();
        String userId = iUserService.verifyToken(token);
        iResumeService.copyResume(resumeId);
        Resume resume = new Resume();
        resume.setResumeId((long) resumeId);
        resume.setResumeStatus(1L);
        iResumeService.updateResume(userId, resume);
        iResumeDeliveryService.deliveryResume(userId, resumeId, recruitmentId);

        return responseVO;
    }

    /**
     * @param token          token
     * @param resumeDelivery 简历投递记录
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户更新投递信息
     */
    @RequestMapping(value = "/user/updatedeliveryrecord", method = RequestMethod.POST)
    public ResponseVO userUpdateDeliveryRecord(@RequestParam String token, @RequestBody ResumeDelivery resumeDelivery) throws TokenExpiredException {

        ResponseVO responseVO = new ResponseVO();
        iUserService.verifyToken(token);
        resumeDelivery.setDeliveryStatus(0L);
        iResumeDeliveryService.updateResumeDeliveryRecord(resumeDelivery);

        return responseVO;
    }

    /**
     * 根据投递id查看投递记录
     * @param token            token
     * @param resumeDeliveryId 简历投递记录的id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户根据投递id获得投递信息
     */
    @RequestMapping(value = "/user/getdeliveryrecordbyid", method = RequestMethod.POST)
    public ResponseVO userGetDeliveryRecordById(@RequestParam String token, @RequestParam int resumeDeliveryId) throws TokenExpiredException, InstanceNotExistException {

        iUserService.verifyToken(token);
        return getDeliveryRecordById(resumeDeliveryId);
    }

    /**
     * 分页查看投递记录
     *
     * @param token    token
     * @param pageData 分页信息
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户根据投递id获得投递信息
     */
    @RequestMapping(value = "/user/getlistdeliveryrecordbyuserid", method = RequestMethod.POST)
    public ResponseVO userGetListDeliveryRecordById(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        String userId = iUserService.selectUserByToken(token).getUserId();
        List<ResumeDelivery> list = iResumeDeliveryService.getRecordByUserId(userId, pageData);
        pageData.setContentList(setTransients(list));
        responseVO.setResultObject(pageData);

        return responseVO;
    }


    // Company

    /**
     * @param token    token
     * @param resumeId 简历id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户根据投递id获得投递信息
     */
    @RequestMapping(value = "/user/getdeliveryrecordbyresumeid", method = RequestMethod.POST)
    public ResponseVO<ResumeDelivery> userGetDeliveryRecordByResumeId(@RequestParam String token, @RequestParam int resumeId) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO<ResumeDelivery> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        ResumeDelivery resumeDelivery = iResumeDeliveryService.getResumeDeliveryRecordByResume(resumeId);
        responseVO.setResultObject(resumeDelivery);

        return responseVO;
    }

    /**
     * @param token    token
     * @param pageData 页信息
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 14:47
     * @description 获得自己的所有的投递记录
     */
    @RequestMapping(value = "/user/getlistdeliveryrecord", method = RequestMethod.POST)
    public ResponseVO userGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO responseVO = new ResponseVO();
        String userId = iUserService.verifyToken(token);
        List<ResumeDelivery> list = iResumeDeliveryService.userGetResumeDeliveryRecord(userId, pageData);
        pageData.setContentList(setTransients(list));

        return responseVO;
    }

    /**
     * -+*
     * **
     *
     * @param token          token
     * @param resumeDelivery 简历投递记录
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:19
     * @description 公司更新简历投递记录
     */
    @RequestMapping(value = "/company/updatedeliveryrecord", method = RequestMethod.POST)
    public ResponseVO comapnyUpdateDeliveryRecord(@RequestParam String token, @RequestBody ResumeDelivery resumeDelivery) throws TokenExpiredException, InstanceNotExistException, EvilIntentions, IOException, ParseException {

        ResponseVO responseVO = new ResponseVO();

        // 更新简历投递中的记录
        long resumeId = iResumeDeliveryService.getResumeDeliveryRecordById(resumeDelivery.getResumeDeliveryId()).getResumeId();
        Resume resume = iResumeService.getResume(null, resumeId);
        resume.setResumeStatus(resumeDelivery.getDeliveryStatus());
        iResumeService.updateResume(resume.getUserId(), resume);
        List<String> list = new ArrayList<>();
        list.add(resume.getUserId());
        PushUtil.getInstance().push(list, "您的投递状态更新", "", resumeDelivery.getResumeDeliveryId() + "");

        iCompanyService.verifyToken(token);
        iResumeDeliveryService.updateResumeDeliveryRecord(resumeDelivery);

        return responseVO;
    }


    /**
     * @param token            token
     * @param resumeDeliveryId 简历投递记录id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 企业根据投递id获得投递信息
     */
    @RequestMapping(value = "/company/getdeliveryrecordbyid", method = RequestMethod.POST)
    public ResponseVO companyGetDeliveryRecordById(@RequestParam String token, @RequestParam int resumeDeliveryId) throws TokenExpiredException, InstanceNotExistException {

        iCompanyService.verifyToken(token);
        return getDeliveryRecordById(resumeDeliveryId);
    }

    /**
     * @param token    token
     * @param pageData 页信息
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/2 14:47
     * @description 公司获得自己招聘信息所有的投递记录
     */
    @RequestMapping(value = "/company/getlistdeliveryrecord", method = RequestMethod.POST)
    public ResponseVO companyGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException, UserNotFoundException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        String companyId = iCompanyService.getInfoByToken(token).getCompanyId();
        List<ResumeDelivery> list = iResumeDeliveryService.companyGetResumeDeliveryRecord(companyId, pageData);
        pageData.setContentList(setTransients(list));
        responseVO.setResultObject(pageData);

        return responseVO;
    }

    /**
     * @param resumeDeliveryId 简历投递记录id
     * @return xz.fzu.vo.ResponseVO<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/5/2 18:37
     * @description 根据投递id获得投递信息
     */
    private ResponseVO<ResumeDelivery> getDeliveryRecordById(int resumeDeliveryId) throws InstanceNotExistException {

        ResponseVO<ResumeDelivery> responseVO = new ResponseVO<>();
        ResumeDelivery resumeDelivery = iResumeDeliveryService.getResumeDeliveryRecordById((long) resumeDeliveryId);
        responseVO.setResultObject(setTransient(resumeDelivery));

        return responseVO;
    }

    /**
     * 通过简历id 得到简历
     *
     * @param token    token
     * @param resumeId 简历id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/3 15:36
     */
    @RequestMapping(value = "/company/getresumebyid", method = RequestMethod.POST)
    public ResponseVO companyGetResumeByResumeId(@RequestParam String token, @RequestParam int resumeId) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO<Resume> responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        Resume resume = iResumeService.getResume(null, (long) resumeId);
        responseVO.setResultObject(resume);

        return responseVO;
    }

    /**
     * 将投递记录添加投递者的用户名
     *
     * @param resumeDeliveries 投递记录的list
     * @return java.util.List<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/5/3 15:36
     */
    private List<ResumeDelivery> setTransients(List<ResumeDelivery> resumeDeliveries) throws InstanceNotExistException {

        List<ResumeDelivery> list = new ArrayList<>();
        for (ResumeDelivery resumeDelivery : resumeDeliveries) {
            list.add(setTransient(resumeDelivery));
        }

        return list;
    }

    /**
     * 单条投递记录添加投递用户名
     *
     * @param resumeDelivery 投递记录
     * @return xz.fzu.model.ResumeDelivery
     * @author Murphy
     * @date 2019/5/3 15:38
     */
    private ResumeDelivery setTransient(ResumeDelivery resumeDelivery) throws InstanceNotExistException {

        String userId = resumeDelivery.getUserId();
        resumeDelivery.setUserName(iUserService.selectByUserId(userId).getUserName());
        resumeDelivery.setRecruitmentName(iRecruitmentService.getRecruitmentById(resumeDelivery.getRecruitmentId()).getJobName());
        resumeDelivery.setSchool(iResumeService.getResume(null, resumeDelivery.getResumeId()).getSchool());
        resumeDelivery.setSpeciality(iResumeService.getResume(null, resumeDelivery.getResumeId()).getSpeciality());
        String companyId = iRecruitmentService.getRecruitmentById(resumeDelivery.getRecruitmentId()).getCompanyId();
        try {
            resumeDelivery.setCompanyName(iCompanyService.getInfoByCompanyId(companyId).getCompanyName());
            resumeDelivery.setCompanyId(companyId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return resumeDelivery;
    }
}
