package xz.fzu.controller;

import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IResumeDeliveryService;
import xz.fzu.service.IResumeService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/2 14:05
 */
@RestController
public class ResumeDeliveryController {

    //User


    @Resource
    IResumeDeliveryService iResumeDeliveryService;
    @Resource
    IUserService iUserService;
    @Resource
    IResumeService iResumeService;

    @RequestMapping(value = "/user/deliveryresume", method = RequestMethod.POST)
    public ResponseData deliveryResume(@RequestParam String token, @RequestParam int resumeId, @RequestParam int recruitmentId) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        String userId = iUserService.verifyToken(token);
        int copyId = iResumeService.copyResume(resumeId);
        iResumeDeliveryService.deliveryResume(userId, copyId, recruitmentId);

        return responseData;
    }

    @Resource
    ICompanyService iCompanyService;

    /**
     * @param token
     * @param pageData
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 14:47
     * @description 获得自己的所有的投递记录
     */
    @RequestMapping(value = "/user/getlistdeliveryrecord", method = RequestMethod.POST)
    public ResponseData userGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException {

        ResponseData responseData = new ResponseData();
        String userId = iUserService.verifyToken(token);
        List<ResumeDelivery> list = iResumeDeliveryService.userGetResumeDeliveryRecord(userId, pageData);
        pageData.setContentList(list);

        return responseData;
    }

    /**
     * @param token
     * @param resumeDelivery
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户更新投递信息
     */
    @RequestMapping(value = "/user/updatedeliveryrecord", method = RequestMethod.POST)
    public ResponseData userUpdateDeliveryRecord(@RequestParam String token, @RequestBody ResumeDelivery resumeDelivery) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iUserService.verifyToken(token);
        resumeDelivery.setDeliveryStatus(0);
        iResumeDeliveryService.updateResumeDeliveryRecord(resumeDelivery);

        return responseData;
    }

    /**
     * @param token
     * @param resumeDeliveryId
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户根据投递id获得投递信息
     */
    @RequestMapping(value = "/user/getdeliveryrecordbyid", method = RequestMethod.POST)
    public ResponseData userGetDeliveryRecordById(@RequestParam String token, @RequestParam int resumeDeliveryId) throws TokenExpiredException, InstanceNotExistException {

        iUserService.verifyToken(token);
        return getDeliveryRecordById(resumeDeliveryId);
    }


    // Company

    /**
     * @param token
     * @param resumeId
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 用户根据投递id获得投递信息
     */
    @RequestMapping(value = "/user/getdeliveryrecordbyresumeid", method = RequestMethod.POST)
    public ResponseData<ResumeDelivery> userGetDeliveryRecordByResumeId(@RequestParam String token, @RequestParam int resumeId) throws TokenExpiredException, InstanceNotExistException {

        ResponseData<ResumeDelivery> responseData = new ResponseData<>();
        iUserService.verifyToken(token);
        ResumeDelivery resumeDelivery = iResumeDeliveryService.getResumeDeliveryRecordByResume(resumeId);
        responseData.setResultObject(resumeDelivery);

        return responseData;
    }

    /**
     * @param token
     * @param pageData
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 14:47
     * @description 公司获得自己招聘信息所有的投递记录
     */
    @RequestMapping(value = "/company/getlistdeliveryrecord", method = RequestMethod.POST)
    public ResponseData companyGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException, UserNotFoundException {

        ResponseData responseData = new ResponseData();
        iCompanyService.verifyToken(token);
        String companyId = iCompanyService.getInfoByToken(token).getCompanyId();
        List<ResumeDelivery> list = iResumeDeliveryService.companyGetResumeDeliveryRecord(companyId, pageData);
        pageData.setContentList(list);

        return responseData;
    }

    /**
     * @param token
     * @param resumeDelivery
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 18:19
     * @description 公司更新简历投递记录
     */
    @RequestMapping(value = "/company/updatedeliveryrecord", method = RequestMethod.POST)
    public ResponseData comapnyUpdateDeliveryRecord(@RequestParam String token, @RequestBody ResumeDelivery resumeDelivery) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        iCompanyService.verifyToken(token);
        iResumeDeliveryService.updateResumeDeliveryRecord(resumeDelivery);

        return responseData;
    }


    /**
     * @param token
     * @param resumeDeliveryId
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 18:28
     * @description 企业根据投递id获得投递信息
     */
    @RequestMapping(value = "/company/getdeliveryrecordbyid", method = RequestMethod.POST)
    public ResponseData companyGetDeliveryRecordById(@RequestParam String token, @RequestParam int resumeDeliveryId) throws TokenExpiredException, InstanceNotExistException {

        iCompanyService.verifyToken(token);
        return getDeliveryRecordById(resumeDeliveryId);
    }


    /**
     * @param resumeDeliveryId
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.ResumeDelivery>
     * @author Murphy
     * @date 2019/5/2 18:37
     * @description 根据投递id获得投递信息
     */
    private ResponseData<ResumeDelivery> getDeliveryRecordById(int resumeDeliveryId) throws InstanceNotExistException {

        ResponseData<ResumeDelivery> responseData = new ResponseData<>();
        ResumeDelivery resumeDelivery = iResumeDeliveryService.getResumeDeliveryRecordById(resumeDeliveryId);
        responseData.setResultObject(resumeDelivery);

        return responseData;
    }
}
