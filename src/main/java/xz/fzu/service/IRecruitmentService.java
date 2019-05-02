package xz.fzu.service;

import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Recruitment;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * @author Murphy
 * @title: IRecruitmentService
 * @projectName XZPT-Java
 * @description: 招聘信息相关的Service接口
 */
public interface IRecruitmentService {

    /**
     * 插入一条招聘信息
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:48
     */
    public void insertRecruitment(Recruitment recruitment);

    /**
     * 根据id获得招聘信息
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 10:49
     * @throws InstanceNotExistException 实例不存在
     */
    public Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException;

    /**
     * 查看公司旗下所有招聘信息
     * @param companyId 公司id
     * @param pageData 页面数据
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 10:49
     * @throws InstanceNotExistException 实例不存在
     */
    List<Recruitment> getListRecruitmentByCompanyId(String companyId, PageData pageData) throws InstanceNotExistException;


    /**
     * 根据keyword获得招聘List
     * @param keyWord 关键词
     * @param requestPage 请求页
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/28 23:52
     * @throws InstanceNotExistException 实例不存在
     */
    public List<Recruitment> getListRecruitmentByKeyWord(String keyWord, PageData requestPage) throws InstanceNotExistException;


    /**
     * 删除招聘信息
     * @param recruitmentId 招聘信息的id
     * @param companyId 公司的id
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:50
     * @throws EvilIntentions 恶意操作异常
     */
    public void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions;


    /**
     * 更新招聘信息
     * @param recruitment 招聘信息
     * @param companyId 企业的id
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:51
     * @throws EvilIntentions 恶意操作异常
     */
    public void updateRecruitment(Recruitment recruitment, String companyId) throws EvilIntentions;


}
