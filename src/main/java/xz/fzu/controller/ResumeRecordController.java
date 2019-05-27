package xz.fzu.controller;

import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.ResumeRecord;
import xz.fzu.service.IResumeRecordService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/28 0:12
 */
@RestController
public class ResumeRecordController {


    @Resource
    IUserService iUserService;

    @Resource
    IResumeRecordService iResumeRecordService;

    @RequestMapping(value = "/user/getlistresumerecord", method = RequestMethod.POST)
    public ResponseVO userGetListDeliveryRecordById(@RequestParam String token, @RequestBody PageData<ResumeRecord> pageData) throws TokenExpiredException, InstanceNotExistException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        String userId = iUserService.selectUserByToken(token).getUserId();
        List<ResumeRecord> list = iResumeRecordService.getListResumeRecordUserId(userId, pageData);
        pageData.setContentList(list);
        responseVO.setResultObject(pageData);

        return responseVO;
    }
}
