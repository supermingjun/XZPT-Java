package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private String userId;
    private String telephone;
    private String passwd;
    private String userName;
    private String headUrl;
    private String email;
    private Long sex;
    private String school;
    private String specialty;
    private Long highestEducation;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Date endTime;
    private Long workTime;
    private Long jobType;
    private String presentCity;
    private String expectedCity;
    private Long industryLabel;
    private String stationLabel;
    private String expectSalary;
    private String token;
    private Long age;

}