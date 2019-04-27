package xz.fzu.service;

import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Recruitment;

import java.util.List;

public interface IRecruitmentService {

    /**
     * @param recruitment
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:48
     * @description 插入一条招聘信息
     */
    public void insertRecruitment(Recruitment recruitment);

    /**
     * @param recruitmentId
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 10:49
     * @description 查看招聘信息
     */
    public Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException;

    /**
     * @param companyId
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 10:49
     * @description 查看公司旗下所有招聘信息
     */
    public List<Recruitment> getListRecruitmentByCompanyId(String companyId) throws InstanceNotExistException;

    /**
     * @param recruitmentId
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:50
     * @description 删除招聘信息
     */
    public void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions;


    /**
     * @param recruitment
     * @param companyId
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:51
     * @description 更新招聘信息
     */
    public void updateRecruitment(Recruitment recruitment, String companyId) throws EvilIntentions;
}
