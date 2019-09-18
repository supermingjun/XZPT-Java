package xz.fzu.service;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.InterviewSkill;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/19 19:01
 */

public interface IInterviewSkillService {

    /**
     * 通过面试技巧id来获得面试技巧文章
     *
     * @param id 面试技巧的id
     * @return xz.fzu.model.InterviewSkill
     * @author Murphy
     * @date 2019/5/19 19:02
     */
    InterviewSkill getInstance(Long id);

    /**
     * 获得面试技巧的List
     *
     * @param pageData 分页信息
     * @return java.util.List<xz.fzu.model.InterviewSkill>
     * @throws InstanceNotExistException 找不到实例或者表述为没有更多的页面可以使用了
     * @author Murphy
     * @date 2019/5/19 19:04
     */
    List<InterviewSkill> getListInstance(PageData<InterviewSkill> pageData) throws InstanceNotExistException;
}
