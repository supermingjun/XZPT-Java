package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    private long recruitmentId;
    private Timestamp publishTime;
    private long validate;
    private String companyId;
    private String jobName;
    private String description;
    private String contact;
    private String location;
    private String deliveryRequest;
    private String salary;
    private String degree;
    private long workTime;
    private long industryLabel;
    private String stationLabel;
    private long jobType;
    private Integer headCount;

    @Transient
    /*
     * 以下字段不对应表中的具体字段，所以全部使用@Transient
     * */
    private String companyName;
    @Transient
    private String station;
    @Transient
    private String industry;

}
