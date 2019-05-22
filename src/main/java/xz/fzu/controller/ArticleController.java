package xz.fzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.InterviewSkill;
import xz.fzu.service.IInterviewSkillService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/5/19 18:24
 */
@Controller
@RequestMapping(value = "/interviewskill", method = RequestMethod.GET)
public class ArticleController {

    @Resource
    IInterviewSkillService iInterviewSkillService;

    /**
     * 获得一篇文章
     *
     * @param attribute 属性
     * @param id        文章的id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 19:12
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String showArticle(Map<String, String> attribute, @RequestParam Long id) {

        InterviewSkill interviewSkill = iInterviewSkillService.getInstance(id);
        attribute.put("title", interviewSkill.getTitle());
        attribute.put("content", interviewSkill.getContent());
        attribute.put("time", interviewSkill.getTime().toString());
        attribute.put("author", interviewSkill.getAuthor());

        return "article";
    }

    /**
     * 分页查看面试技巧
     *
     * @param pageData 分页数据
     * @return xz.fzu.vo.ResponseVO<xz.fzu.vo.PageData>
     * @author Murphy
     * @date 2019/5/19 19:34
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<PageData> showLIstArticle(@RequestBody PageData<InterviewSkill> pageData) throws InstanceNotExistException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        List<InterviewSkill> list = iInterviewSkillService.getListInstance(pageData);
        pageData.setContentList(list);
        responseVO.setResultObject(pageData);

        return responseVO;
    }
}
