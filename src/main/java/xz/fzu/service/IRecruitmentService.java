package xz.fzu.service;

import org.json.simple.parser.ParseException;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.OverLimitException;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.vo.PageData;

import java.io.IOException;
import java.util.List;

/**
 * 简历相关的Service接口
 *
 * @author Murphy
 */
public interface IRecruitmentService {

    /**
     * 插入一条招聘信息
     *
     * @param userIdList 用户列表
     * @param recruitment 招聘信息的实例
     * @return void
     * @author Murphy
     * @date 2019/4/27 10:48
     * @throws OverLimitException 超出发布信息上限异常
     * @throws IOException mipush异常
     * @throws ParseException parse异常
     */
    Long insertRecruitment(List<String> userIdList, Recruitment recruitment) throws OverLimitException, IOException, ParseException;

    /**
     * 根据id获得招聘信息
     *
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.model.Recruitment
     * @throws InstanceNotExistException 实例不存在
     * @author Murphy
     * @date 2019/4/27 10:49
     */
    Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException;

    /**
     * 查看公司旗下所有招聘信息
     *
     * @param companyId 公司id
     * @param pageData  页面数据
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @throws InstanceNotExistException 实例不存在
     * @author Murphy
     * @date 2019/4/27 10:49
     */
    List<Recruitment> getListRecruitmentByCompanyId(String companyId, PageData pageData) throws InstanceNotExistException;


    /**
     * 根据keyword获得招聘List
     *
     * @param keyWord     关键词
     * @param requestPage 请求页
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @throws InstanceNotExistException 实例不存在
     * @author Murphy
     * @date 2019/4/28 23:52
     */
    List<Recruitment> getListRecruitmentByKeyWord(String keyWord, PageData requestPage) throws InstanceNotExistException;


    /**
     * 删除招聘信息
     *
     * @param recruitmentId 招聘信息的id
     * @param companyId     公司的id
     * @return void
     * @throws EvilIntentions 恶意操作异常
     * @author Murphy
     * @date 2019/4/27 10:50
     */
    void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions;


    /**
     * 更新招聘信息
     *
     * @param recruitment 招聘信息
     * @param companyId   企业的id
     * @return void
     * @throws EvilIntentions 恶意操作异常
     * @author Murphy
     * @date 2019/4/27 10:51
     */
    void updateRecruitment(xz.fzu.model.Recruitment recruitment, String companyId) throws EvilIntentions;


    /**
     * 根据listid获得招聘信息
     *
     * @param label 岗位标签
     * @param longs ids
     * @param requestPage   分页信息
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/5/23 13:52
     * @throws InstanceNotExistException 找不到相应的id
     */
    List<Recruitment> getRecruitmentByHotpost(Long label, List<Long> longs, PageData<Recruitment> requestPage) throws InstanceNotExistException;

    /**
     * 根据投递记录里的recruitmentId获得招聘信息
     * @param resumeDeliveries
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author litm
     * @date 2019/5/30 23:15:57
     */
    List<Recruitment> getRecruitmentByResumeDeliveries(List<ResumeDelivery> resumeDeliveries);
    /**
     * 验证招聘信息数目是否超出限制
     *
     * @param companyId 公司id
     * @return void
     * @author Murphy
     * @date 2019/5/27 14:07
     * @throws OverLimitException 超出限制异常
     */
    void verifyLimit(String companyId) throws OverLimitException;
}
