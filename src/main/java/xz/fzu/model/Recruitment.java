package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "recruitment")
@Data
public class Recruitment implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentId;
    private Timestamp publishTime;
    private Integer validate;
    private String companyId;
    private String jobName;
    private String description;
    private String contact;
    private String location;
    private String deliveryRequest;
    private String salary;
    private String degree;
    private Integer workTime;
    private Long industryLabel;
    private String stationLabel;
    private Integer jobType;
    private Integer headCount;

    @Transient
    private String companyName;
    @Transient
    private String station;
    @Transient
    private String industry;
    @Transient
    private int count;

}
