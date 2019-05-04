package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IIndustryLabelDao;
import xz.fzu.dao.IStationLabelDao;
import xz.fzu.service.ILabelService;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/419:17
 */
@Service
public class LabelServiceImpl implements ILabelService {

    @Resource
    IStationLabelDao iStationLabelDao;
    @Resource
    IIndustryLabelDao iIndustryLabelDao;

    @Override
    public String getStationLabel(int id) {
        return iStationLabelDao.getInstance(id);
    }

    @Override
    public String getIndustryLabel(int id) {
        return iIndustryLabelDao.getInstance(id);
    }
}
