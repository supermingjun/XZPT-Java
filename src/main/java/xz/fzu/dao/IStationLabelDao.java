package xz.fzu.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Murphy
 * @date 2019/5/419:06
 */
@Repository
public interface IStationLabelDao {

    /**
     * 根据岗位id获得岗位标签
     *
     * @param stationId 岗位id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/4 19:08
     */
    String getInstance(int stationId);
}
