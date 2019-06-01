package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.mapper.InterviewSkillMapper;
import xz.fzu.model.InterviewSkill;
import xz.fzu.service.IInterviewSkillService;
import xz.fzu.util.PageUtil;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/19 19:04
 */
@Service
public class InterviewSkillServiceImpl implements IInterviewSkillService {

    @Resource
    InterviewSkillMapper interviewSkillMapper;

    @Override
    public InterviewSkill getInstance(Long id) {
        return interviewSkillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<InterviewSkill> getListInstance(PageData<InterviewSkill> pageData) throws InstanceNotExistException {
        List<InterviewSkill> list = interviewSkillMapper.selectAll();

        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }

        return PageUtil.paging(list, pageData);
    }
}
