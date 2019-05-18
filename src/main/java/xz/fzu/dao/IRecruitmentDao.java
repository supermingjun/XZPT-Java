package xz.fzu.dao;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.model.Recruitment;

import java.util.List;

/**
 * 招聘信息结果相关的dao
 *
 * @author Murphy
 */
@Repository
public interface IRecruitmentDao extends Mapper<Recruitment> {

    /**
     * 插入一个招聘信息
     *
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:13
     */
//    void insertInstance(Recruitment recruitment);

    /**
     * 通过实例id查找招聘信息实例
     *
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 1:13
     */
    Recruitment selectInstaceById(long recruitmentId);

    /**
     * 根据公司id查找所有的招聘信息
     *
     * @param companyId   招聘信息的id
     * @param requestPage 请求页
     * @param pageSize    请求页大小
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 1:13
     */
    List<Recruitment> selectListInstanceByCompanyId(String companyId, int requestPage, int pageSize);

    /**
     * 通过keyWord查找招聘信息
     *
     * @param keyWord     关键词
     * @param requestPage 页码
     * @param pageSize    页大小
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/28 23:54
     */
    List<Recruitment> selectInstanceByKeyWord(String keyWord, int requestPage, int pageSize);

    /**
     * 删除招聘信息
     *
     * @param recruitmentId 招聘信息id
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     */
    void deleteInstace(long recruitmentId);

    /**
     * 更新招聘信息
     *
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     */
    void updateInstance(Recruitment recruitment);
}
