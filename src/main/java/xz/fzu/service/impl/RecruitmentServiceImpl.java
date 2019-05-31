package xz.fzu.service.impl;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import xz.fzu.algorithm.RecommendHotJobs;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.OverLimitException;
import xz.fzu.mapper.RecruitmentMapper;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.util.PageUtil;
import xz.fzu.util.PushUtil;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/27 10:51
 */
@Service
public class RecruitmentServiceImpl implements IRecruitmentService {

    @Resource
    RecruitmentMapper recruitmentMapper;

    @Override
    public Long insertRecruitment(List<String> userIdList, Recruitment recruitment) throws OverLimitException, IOException, ParseException {

        verifyLimit(recruitment.getCompanyId());
        recruitment.setValidate(0);
        recruitmentMapper.insert(recruitment);
        if (recruitment.getIndustryLabel() != null && userIdList != null) {
            PushUtil.getInstance().push(userIdList, recruitment.getJobName(), recruitment.getDescription(), recruitment.getRecruitmentId() + "");
        }

        return recruitment.getRecruitmentId();
    }

    @Override
    public Recruitment getRecruitmentById(long recruitmentId) throws InstanceNotExistException {
        xz.fzu.model.Recruitment recruitment = recruitmentMapper.selectInstaceById(recruitmentId);
        if (recruitment == null) {
            throw new InstanceNotExistException();
        }
        return recruitment;
    }

    @Override
    public List<Recruitment> getListRecruitmentByCompanyId(String companyId, PageData pageData) throws InstanceNotExistException {
        List<xz.fzu.model.Recruitment> recruitmentList = recruitmentMapper.selectListInstanceByCompanyId(companyId,
                (pageData.getCurrentPage() - 1) * pageData.getPageSize(), pageData.getPageSize());
        if (recruitmentList.size() == 0) {
            throw new InstanceNotExistException();
        }

        return recruitmentList;
    }

    @Override
    public void deleteRecruitment(long recruitmentId, String companyId) throws EvilIntentions {
        xz.fzu.model.Recruitment tempRecruitment = recruitmentMapper.selectInstaceById(recruitmentId);
        if (tempRecruitment == null || !tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        recruitmentMapper.deleteInstance(recruitmentId);
    }

    @Override
    public void updateRecruitment(xz.fzu.model.Recruitment recruitment, String companyId) throws EvilIntentions {
        xz.fzu.model.Recruitment tempRecruitment = recruitmentMapper.selectInstaceById(recruitment.getRecruitmentId());
        if (!tempRecruitment.getCompanyId().equals(companyId)) {
            throw new EvilIntentions();
        }
        recruitmentMapper.updateInstance(recruitment);
    }

    @Override
    public List<Recruitment> getListRecruitmentByKeyWord(String keyWord, PageData requestPage) throws InstanceNotExistException {
        List<xz.fzu.model.Recruitment> recruitmentList = recruitmentMapper.selectInstanceByKeyWord('%' + keyWord + '%', (requestPage.getCurrentPage() - 1) * requestPage.getPageSize(), requestPage.getPageSize());
        if (recruitmentList == null || recruitmentList.size() == 0) {
            throw new InstanceNotExistException();
        }

        return recruitmentList;
    }

    @Override
    public List<Recruitment> getRecruitmentByIds(Long label, List<Long> longs, PageData<Recruitment> requestPage) throws InstanceNotExistException {

        List<Recruitment> list = new ArrayList<>();
        for (Long aLong : longs) {
            list.add(recruitmentMapper.selectInstaceById(aLong));
        }
        RecommendHotJobs.recommendHotJobs(label, list);
        PageUtil.paging(list, requestPage);

        return list;
    }
    @Override
    public List<Recruitment> getRecruitmentByResumeDeliveries(List<ResumeDelivery> resumeDeliveries) {

        List<Recruitment> list = new ArrayList<>();
        for(ResumeDelivery resumeDelivery : resumeDeliveries){
            list.add(recruitmentMapper.selectInstaceById(resumeDelivery.getRecruitmentId()));
        }
        return list;
    }
    private static final int LIMIT_SIZE = 10;

    @Override
    public void verifyLimit(String companyId) throws OverLimitException {
        if (recruitmentMapper.selectNumber(companyId) > LIMIT_SIZE) {
            throw new OverLimitException();
        }
    }
}
