package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Murphy
 * @date 2019/5/315:26
 */
@Table(name = "resume_record")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeRecord {

    @Id
    /**
     * 自增主键，无实际意义
     */
    private Long recordId;
    private String resumeUrl;
    private String templateJpgUrl;
    private String userId;

}
