package xz.fzu.mapper;

import org.springframework.stereotype.Repository;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;

/**
 * 配置信息相关的dao
 *
 * @author Murphy
 * @date 2019/4/30 21:36
 */
@Repository
public interface ProfileMapper {

    /**
     * 根据userId获得UserProfile实例
     *
     * @param userId 用户的id
     * @return xz.fzu.model.UserProfile
     * @author Murphy
     * @date 2019/4/30 21:58
     */
    UserProfile getUserProfile(String userId);

    /**
     * 获得招聘信息的profile
     *
     * @return java.util.List<xz.fzu.model.RecruitmentProfile>
     * @author Murphy
     * @date 2019/4/30 21:59
     * @description
     */
    List<RecruitmentProfile> getRecruitmentProfile();


    /**
     * 查找所有用户id
     *
     * @return java.util.List<java.lang.String>
     * @author Murphy
     * @date 2019/5/1 12:54
     */
    List<String> selectUserId();
}
