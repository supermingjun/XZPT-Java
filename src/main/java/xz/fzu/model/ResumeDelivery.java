package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Murphy
 * @date 2019/5/315:26
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "resume_delivery")
@Data
public class ResumeDelivery {

    private static final long serialVersionUID = 1;

    @Id
    private Long resumeDeliveryId;
    private Long recruitmentId;
    private String userId;
    private Long resumeId;
    private Long deliveryStatus;
    private String remark;

    @Transient
    private String userName;
    @Transient
    private String recruitmentName;
    @Transient
    private String school;
    @Transient
    private String speciality;

}
