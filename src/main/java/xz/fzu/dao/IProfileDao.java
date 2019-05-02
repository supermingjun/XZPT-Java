package xz.fzu.dao;

import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;

/**
 * @author Murphy
 * @title: IProfileDao
 * @projectName XZPT-Java
 * @description: 配置信息相关的dao
 * @date 2019/4/30 21:36
 * */
public interface IProfileDao {

    /**
     * 根据userId获得UserProfile实例
     * @param userId 用户的id
     * @return xz.fzu.model.UserProfile
     * @author Murphy
     * @date 2019/4/30 21:58
     */
    public UserProfile getUserProfile(String userId);

    /**
     * 获得招聘信息的profile
     * @return java.util.List<xz.fzu.model.RecruitmentProfile>
     * @author Murphy
     * @date 2019/4/30 21:59
     * @description
     */
    public List<RecruitmentProfile> getRecruitmentProfile();


    /**
     * 查找所有用户id
     * @return java.util.List<java.lang.String>
     * @author Murphy
     * @date 2019/5/1 12:54
     */
    public List<String> selectUserId();
}
