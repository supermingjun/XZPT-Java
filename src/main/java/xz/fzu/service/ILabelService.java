package xz.fzu.service;

/**
 * 标签服务
 *
 * @author Murphy
 * @date 2019/5/419:13
 */
public interface ILabelService {

    /**
     * 获得岗位标签
     *
     * @param id 岗位id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/4 19:15
     */
    String getStationLabel(Long id);

    /**
     * 获得行业标签
     *
     * @param id 行业标签
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/4 19:15
     */
    String getIndustryLabel(Long id);
}
