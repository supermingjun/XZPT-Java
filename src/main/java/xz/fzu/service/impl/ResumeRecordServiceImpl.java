package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeRecordDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeRecord;
import xz.fzu.service.IResumeRecordService;
import xz.fzu.util.PageUtil;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
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
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getUserId().equals(userId)) {
                list.remove(i);
                i--;
            }
        }

        return PageUtil.paging(list, pageData);
    }

    @Override
    public void insertResumeRecord(ResumeRecord resumeRecord) {
        iResumeRecordDao.insert(resumeRecord);
    }
}
