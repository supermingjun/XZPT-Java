package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.model.InterviewSkill;

/**
 * @author Murphy
 * @date 2019/5/19 18:59
 */
@Repository
public interface IInterviewSkillDao extends Mapper<InterviewSkill> {
}
