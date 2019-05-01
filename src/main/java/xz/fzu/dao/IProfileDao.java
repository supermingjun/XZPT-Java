package xz.fzu.dao;

import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 21:36
 */
public interface IProfileDao {

    /**
     * @param userId
     * @return xz.fzu.model.UserProfile
     * @author Murphy
     * @date 2019/4/30 21:58
     * @description 根据userId获得UserProfile实例
     */
    public UserProfile getUserProfile(String userId);

    /**
     * @param
     * @return java.util.List<xz.fzu.model.RecruitmentProfile>
     * @author Murphy
     * @date 2019/4/30 21:59
     * @description 获得
     */
    public List<RecruitmentProfile> getRecruitmentProfile();


    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @author Murphy
     * @date 2019/5/1 12:54
     * @description 查找所有用户id
     */
    public List<String> selectUserId();
}
