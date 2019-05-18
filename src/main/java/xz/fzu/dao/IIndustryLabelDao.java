package xz.fzu.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Murphy
 * @date 2019/5/419:06
 */
@Repository
public interface IIndustryLabelDao {

    /**
     * 根据id查找行业标签
     *
     * @param id id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/4 19:08
     */
    String getInstance(Long id);
}
