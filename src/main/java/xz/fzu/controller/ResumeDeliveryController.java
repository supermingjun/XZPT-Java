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
    @Resource
    ICompanyService iCompanyService;

    @RequestMapping(value = "/user/deliveryresume", method = RequestMethod.POST)
    public ResponseData deliveryResume(@RequestParam String token, @RequestParam int resumeId, @RequestParam int recruitmentId) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        String userId = iUserService.verifyToken(token);
        int copyId = iResumeService.copyResume(resumeId);
        iResumeDeliveryService.deliveryResume(userId, copyId, recruitmentId);

        return responseData;
    }

    /**
     * @param token
     * @param pageData
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/5/2 14:47
     * @description 获得自己的所有的投递记录
     */
    @RequestMapping(value = "/user/getdeliveryrecord", method = RequestMethod.POST)
    public ResponseData userGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException {

        ResponseData responseData = new ResponseData();
        String userId = iUserService.verifyToken(token);
        List<ResumeDelivery> list = iResumeDeliveryService.userGetResumeDeliveryRecord(userId, pageData);
        pageData.setContentList(list);

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
    @RequestMapping(value = "/company/getdeliveryrecord", method = RequestMethod.POST)
    public ResponseData companyGetDeliveryRecord(@RequestParam String token, @RequestBody PageData<ResumeDelivery> pageData) throws TokenExpiredException, InstanceNotExistException, UserNotFoundException {

        ResponseData responseData = new ResponseData();
        iCompanyService.verifyToken(token);
        String companyId = iCompanyService.getInfoByToken(token).getCompanyId();
        List<ResumeDelivery> list = iResumeDeliveryService.userGetResumeDeliveryRecord(companyId, pageData);
        pageData.setContentList(list);

        return responseData;
    }
}
