package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.ExportException;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.Resume;
import xz.fzu.service.IExportResumeService;
import xz.fzu.service.IResumeService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/23 20:35
 */
@RestController
public class ExportResumeController {

    @Resource
    IExportResumeService iExportResumeService;
    @Resource
    IUserService iUserService;
    @Resource
    IResumeService iResumeService;

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
        String userId = iUserService.selectUserByToken(token).getUserId();
        Resume resume = iResumeService.getFirstResume(userId);
        String url = iExportResumeService.exportResume(resume, templatePath, userId);
        response.setResultObject(url);

        return response;
    }
}
