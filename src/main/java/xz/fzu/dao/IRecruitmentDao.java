package xz.fzu.dao;

import xz.fzu.model.Recruitment;

import java.util.List;

/**
 * @author Murphy
 * @title: IRecruitmentDao
 * @projectName XZPT-Java
 * @description: 招聘信息结果相关的dao
 */
public interface IRecruitmentDao {

    /**
     * 插入一个招聘信息
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:13
     */
    public void insertInstance(Recruitment recruitment);

    /**
     * 通过实例id查找招聘信息实例
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 1:13
     */
    public Recruitment selectInstaceById(long recruitmentId);

    /**
     * 根据公司id查找所有的招聘信息
     * @param companyId 招聘信息的id
     * @param requestPage 请求页
     * @param pageSize 请求页大小
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 1:13
     */
    public List<Recruitment> selectListInstanceByCompanyId(String companyId, int requestPage, int pageSize);

    /**
     * 通过keyWord查找招聘信息
     * @param keyWord 关键词
     * @param requestPage 页码
     * @param pageSize 页大小
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/28 23:54
     */
    public List<Recruitment> selectInstanceByKeyWord(String keyWord, int requestPage, int pageSize);

    /**
     * 删除招聘信息
     * @param recruitmentId 招聘信息id
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     */
    public void deleteInstace(long recruitmentId);

    /**
     *  更新招聘信息
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     */
    public void updateInstace(Recruitment recruitment);
}
