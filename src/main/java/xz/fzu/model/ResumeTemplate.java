package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Murphy
 * @date 2019/5/23 12:48
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "resume_template")
@Data
public class ResumeTemplate {

    @Id
    /**
     * 用于导出带数据简历的doc
     */
    private String templateFileName;
    private String imgFileName;
    /**
     * 查看未填充数据的简历模板
     */
    private String tempFileName;

}
