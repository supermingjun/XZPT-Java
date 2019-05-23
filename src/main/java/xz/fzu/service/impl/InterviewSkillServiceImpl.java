package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IInterviewSkillDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.InterviewSkill;
import xz.fzu.service.IInterviewSkillService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/19 19:04
 */
@Service
public class InterviewSkillServiceImpl implements IInterviewSkillService {

    @Resource
    IInterviewSkillDao iInterviewSkillDao;

    @Override
    public InterviewSkill getInstance(Long id) {
        return iInterviewSkillDao.selectByPrimaryKey(id);
    }

    @Override
    public List<InterviewSkill> getListInstance(PageData pageData) throws InstanceNotExistException {
        List<InterviewSkill> list = iInterviewSkillDao.selectAll();

        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }

        int pageSize = pageData.getPageSize();
        int startIndex = pageData.getCurrentPage() * pageSize;

        List<InterviewSkill> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i >= startIndex && i < startIndex + pageSize) {
                res.add(list.get(i));
            }
        }

        return res;
    }
}
