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
     * @param userId
     * @return xz.fzu.model.UserProfile
     * @author Murphy
     * @date 2019/4/30 22:14
     * @description 获得User的视图
     */
    public UserProfile getUserProfile(String userId);

    /**
     * @param
     * @return java.util.List<xz.fzu.model.RecruitmentProfile>
     * @author Murphy
     * @date 2019/4/30 22:14
     * @description 获得招聘会实例
     */
    public List<RecruitmentProfile> getRecruitmentProfile();
}
