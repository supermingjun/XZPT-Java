package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.ExportException;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Resume;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.model.ResumeRecord;
import xz.fzu.service.*;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 20:35
 */
@RestController
public class ExportController {

    @Resource
    IExportService iExportResumeService;
    @Resource
    IUserService iUserService;
    @Resource
    IResumeService iResumeService;

    @Resource
    ICompanyService iCompanyService;
    @Resource
    IResumeDeliveryService iResumeDeliveryService;
    @Resource
    IResumeRecordService iResumeRecordService;

    /**
     * 导出简历Controller
     *
     * @param token        token
     * @param templatePath 模板路径
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/25 16:57
     */
    @RequestMapping(value = "/user/exportresume", method = RequestMethod.POST)
    public ResponseVO<String> exportResume(@RequestParam String token, @RequestParam String templatePath) throws TokenExpiredException, InstanceNotExistException, ExportException {

        ResponseVO<String> response = new ResponseVO<>();

        ResumeRecord resumeRecord = new ResumeRecord();
        String userId = iUserService.selectUserByToken(token).getUserId();
        Resume resume = iResumeService.getFirstResume(userId);
        System.out.println("导出" + resume.getUserName() + "的简历。");
        String url = iExportResumeService.exportResume(resume, templatePath, userId);
        response.setResultObject(url);


        resumeRecord.setResumeUrl(url);
        resumeRecord.setUserId(userId);
        int index = templatePath.lastIndexOf('.') + 1;
        resumeRecord.setTemplateJpgUrl(templatePath.substring(0, index) + "jpg");
        iResumeRecordService.insertResumeRecord(resumeRecord);

        return response;
    }

    @RequestMapping(value = "/company/export/excel/resume", method = RequestMethod.POST)
    public ResponseVO<String> exportExcelOfResume(@RequestParam String token, @RequestParam Long recruitmentId) throws TokenExpiredException, InstanceNotExistException, UserNotFoundException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        List<ResumeDelivery> list = iResumeDeliveryService.getAllRecord();
        List<Resume> res = new ArrayList<>();
        for (ResumeDelivery resumeDelivery : list) {
            if (resumeDelivery.getRecruitmentId().equals(recruitmentId)) {
                Resume resume = iResumeService.getResume(null, resumeDelivery.getResumeId());
                res.add(resume);
            }
        }
        String url = iExportResumeService.exportExcel(res, iCompanyService.getInfoByToken(token).getCompanyId());
        responseVO.setResultObject(url);

        return responseVO;
    }
}
