package xz.fzu.controller;

import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.Resume;
import xz.fzu.service.IResumeService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历相关的控制器
 * @author Murphy
 * @date 2019/5/2 13:47
 */
@RestController
public class ResumeController {


    @Resource
    IUserService iUserService;
    @Resource
    IResumeService iResumeService;
    // User

    /**
     * @param token token
     * @param pageData 页信息
     * @return xz.fzu.vo.ResponseData<xz.fzu.vo.PageData>
     * @author Murphy
     * @date 2019/4/30 1:10
     * @description 查看本人所有的简历
     */
    @RequestMapping(value = "/user/getlistresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<PageData> getListResume(@RequestParam String token, @RequestBody PageData<Resume> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<PageData> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        List<Resume> recruitmentList = iResumeService.getListResume(userId, pageData);
        pageData.setContentList(recruitmentList);
        responseData.setResultObject(pageData);

        return responseData;
    }

    /**
     * @param token token
     * @param resumeId 简历id
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 14:27
     * @description 根据简历id获得简历
     */
    @RequestMapping(value = "/user/getresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Resume> getResume(@RequestParam String token, @RequestParam int resumeId) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<Resume> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        Resume resume = iResumeService.getResume(userId, resumeId);
        responseData.setResultObject(resume);

        return responseData;
    }

    /**
     * @param token token
     * @param resume 简历实例
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 1:13
     * @description 更新简历
     */
    @RequestMapping(value = "/user/updateresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Resume> updateResume(@RequestParam String token, @RequestBody Resume resume) throws TokenExpiredException, EvilIntentions {

        ResponseData<Resume> responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        iResumeService.updateResume(userId, resume);

        return responseData;
    }

    /**
     * @param token token
     * @param resume 简历实例
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/30 14:31
     * @description 插入一条简历
     */
    @RequestMapping(value = "/user/createresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData insertResume(@RequestParam String token, @RequestBody Resume resume) throws TokenExpiredException, EvilIntentions {

        ResponseData responseData = new ResponseData<>();
        String userId = iUserService.verifyToken(token);
        iResumeService.insertResume(userId, resume);

        return responseData;
    }

    /**
     * @param token token
     * @param resumeId 简历id
     * @return xz.fzu.vo.ResponseData<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 1:14
     * @description 删除简历
     */
    @RequestMapping(value = "/user/deleteresume", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<Resume> deleteResume(@RequestParam String token, @RequestParam int resumeId) throws InstanceNotExistException, TokenExpiredException {

        ResponseData<Resume> responseData = new ResponseData<>();
        // TODO 安全认证
        String userId = iUserService.verifyToken(token);
        iResumeService.deleteResume(resumeId);

        return responseData;
    }

}
