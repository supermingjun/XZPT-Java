package xz.fzu.service;

import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 22:13
 */
public interface IProfileService {

    /**
     * 获得User的视图
     *
     * @param userId 用户id
     * @return xz.fzu.model.UserProfile
     * @author Murphy
     * @date 2019/4/30 22:14
     */
    UserProfile getUserProfile(String userId);

    /**
     * 获得招聘会实例
     *
     * @return java.util.List<xz.fzu.model.RecruitmentProfile>
     * @author Murphy
     * @date 2019/4/30 22:14
     */
    List<RecruitmentProfile> getRecruitmentProfile();

    /**
     * 查找所有用户的id
     *
     * @return java.util.List<java.lang.String>
     * @author Murphy
     * @date 2019/5/1 12:59
     */
    List<String> selectUserId();
}
