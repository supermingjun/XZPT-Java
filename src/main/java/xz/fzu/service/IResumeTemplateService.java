package xz.fzu.service;

import xz.fzu.model.ResumeTemplate;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 12:51
 */
public interface IResumeTemplateService {

    /**
     * 插入实例
     *
     * @param instance 实例
     * @return void
     * @author Murphy
     * @date 2019/5/23 12:53
     */
    void insertInstance(ResumeTemplate instance);

    /**
     * 获得实例
     *
     * @param fileName 文件名
     * @return xz.fzu.model.ResumeTemplate
     * @author Murphy
     * @date 2019/5/23 12:53
     */
    ResumeTemplate getInstance(String fileName);

    /**
     * 获得所有的简历模板
     *
     * @param pageData 分页数据
     * @return java.util.List<xz.fzu.model.ResumeTemplate>
     * @author Murphy
     * @date 2019/5/23 12:59
     */
    List<ResumeTemplate> getPageData(PageData pageData);
}
