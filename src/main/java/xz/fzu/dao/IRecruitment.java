package xz.fzu.dao;

import xz.fzu.model.Recruitment;

import java.util.List;

public interface IRecruitment {

    /**
     * @param recruitment
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 插入一个实例
     */
    public void insertInstance(Recruitment recruitment);

    /**
     * @param recruitmentId
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 通过实例id查找数据库实例
     */
    public Recruitment selectInstaceById(String recruitmentId);

    /**
     * @param companyId
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 根据公司id查找所有的招聘信息
     */
    public List<Recruitment> selectListInstaceByCompanyId(String companyId);

    /**
     * @param recruitmentId
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     * @description 删除招聘信息
     */
    public void deleteInstace(String recruitmentId);

    /**
     * @param recruitment
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     * @description 更新信息
     */
    public void updateInstace(Recruitment recruitment);
}
