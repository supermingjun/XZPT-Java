package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.mapper.IndustryLabelMapper;
import xz.fzu.mapper.StationLabelMapper;
import xz.fzu.service.ILabelService;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/419:17
 */
@Service
public class LabelServiceImpl implements ILabelService {

    @Resource
    StationLabelMapper stationLabelMapper;
    @Resource
    IndustryLabelMapper industryLabelMapper;

    @Override
    public String getStationLabel(Long id) {
        return stationLabelMapper.getInstance(id);
    }

    @Override
    public String getIndustryLabel(Long id) {
        return industryLabelMapper.getInstance(id);
    }
}
