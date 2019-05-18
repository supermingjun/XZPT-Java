package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "resume")
@Data
public class Resume implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private long resumeId;
    private String userId;
    private String telephone;
    private String userName;
    private String headUrl;
    private String email;
    private long sex;
    private String presentCity;
    private String expectedCity;
    private String school;
    private String speciality;
    private java.sql.Date startTime;
    private java.sql.Date endTime;
    private long highestEducation;
    private String certificate;
    private String projectExperience;
    private String practicalExperience;
    private long resumeStatus;
    private long age;
    private String expectWork;

}
