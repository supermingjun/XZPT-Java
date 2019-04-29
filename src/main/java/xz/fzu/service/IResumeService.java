package xz.fzu.service;

import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.Resume;

import java.util.List;

public interface IResumeService {

    /**
     * @param resume
     * @return void
     * @author Murphy
     * @date 2019/4/30 0:14
     * @description 插入一个实例
     */
    public void insertResume(String token, Resume resume) throws TokenExpiredException, EvilIntentions;

    /**
     * @param resume
     * @return xz.fzu.model.Resume
     * @author Murphy
     * @date 2019/4/30 0:14
     * @description 更新简历
     */
    public void updateResume(String token, Resume resume) throws EvilIntentions, TokenExpiredException;

    /**
     * @param userId
     * @return java.util.List<xz.fzu.model.Resume>
     * @author Murphy
     * @date 2019/4/30 0:15
     * @description 查找当前用户所有的简历
     */
    public List<Resume> getListResume(String userId);


    /**
     * @param resumeId
     * @return int
     * @author Murphy
     * @date 2019/4/30 0:16
     * @description 删除简历
     */
    public int deleteResume(int resumeId);

    /**
     * @param resumeId
     * @return xz.fzu.model.Resume
     * @author Murphy
     * @date 2019/4/30 0:17
     * @description 获得指定简历实例
     */
    public Resume getResume(int resumeId);

    /**
     * @param token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/30 0:35
     * @description 根据token获得用户id
     */
    public String getUserId(String token) throws TokenExpiredException;
}
