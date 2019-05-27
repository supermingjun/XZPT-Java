package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeRecordDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeRecord;
import xz.fzu.service.IResumeRecordService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/28 0:17
 */
@Service
public class ResumeRecordServiceImpl implements IResumeRecordService {

    @Resource
    IResumeRecordDao iResumeRecordDao;

    @Override
    public List<ResumeRecord> getListResumeRecordUserId(String userId, PageData<ResumeRecord> pageData) throws InstanceNotExistException {
        List<ResumeRecord> list = iResumeRecordDao.selectAll();
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }
        int start = (pageData.getCurrentPage() - 1) * pageData.getPageSize();
        int end = pageData.getPageSize() + start - 1;
        List<ResumeRecord> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i >= start && i <= end) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void insertResumeRecord(ResumeRecord resumeRecord) {
        iResumeRecordDao.insert(resumeRecord);
    }
}
