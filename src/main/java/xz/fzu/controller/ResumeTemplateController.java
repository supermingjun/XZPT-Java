package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.model.ResumeTemplate;
import xz.fzu.service.IResumeTemplateService;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 13:10
 */
@RestController
public class ResumeTemplateController {

    @Resource
    IResumeTemplateService iResumeTemplateService;

    /**
     * 获得所有的简历模板
     *
     * @return xz.fzu.vo.ResponseVO<java.util.List < xz.fzu.model.ResumeTemplate>>
     * @author Murphy
     * @date 2019/5/23 13:15
     */
    @RequestMapping(value = "/getlistresumetemplate", method = RequestMethod.POST)
    public ResponseVO<List<ResumeTemplate>> getAll() {

        ResponseVO<List<ResumeTemplate>> responseVO = new ResponseVO<>();
        List<ResumeTemplate> resumeTemplateList = iResumeTemplateService.getAll();
        responseVO.setResultObject(resumeTemplateList);

        return responseVO;
    }
}
