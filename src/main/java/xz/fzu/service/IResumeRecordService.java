package xz.fzu.service;

import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeRecord;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/28 0:14
 */
public interface IResumeRecordService {


    /**
     * 根据用户id获取记录
     *
     * @param userId   用户id
     * @param pageData 分页数据
     * @return xz.fzu.model.ResumeRecord
     * @throws InstanceNotExistException 找不到实例
     * @author Murphy
     * @date 2019/5/28 0:15
     */
    List<ResumeRecord> getListResumeRecordUserId(String userId, PageData<ResumeRecord> pageData) throws InstanceNotExistException;


    /**
     * 插入一条简历生成记录
     *
     * @param resumeRecord 简历记录
     * @return void
     * @author Murphy
     * @date 2019/5/28 0:21
     */
    void insertResumeRecord(ResumeRecord resumeRecord);
}
