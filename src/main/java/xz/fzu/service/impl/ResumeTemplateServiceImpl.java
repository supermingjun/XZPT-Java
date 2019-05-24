package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeTemplateDao;
import xz.fzu.model.ResumeTemplate;
import xz.fzu.service.IResumeTemplateService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 12:56
 */
@Service
public class ResumeTemplateServiceImpl implements IResumeTemplateService {

    @Resource
    IResumeTemplateDao iResumeTemplateDao;

    @Override
    public void insertInstance(ResumeTemplate instance) {
        iResumeTemplateDao.insert(instance);
    }

    @Override
    public ResumeTemplate getInstance(String fileName) {
        return iResumeTemplateDao.selectByPrimaryKey(fileName);
    }

    @Override
    public List<ResumeTemplate> getPageData(PageData requestPage) {
        List<ResumeTemplate> list = iResumeTemplateDao.selectAll();
        int start = (requestPage.getCurrentPage() - 1) * requestPage.getPageSize();
        int end = requestPage.getPageSize() + start - 1;
        List<ResumeTemplate> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i >= start && i <= end) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
