package xz.fzu.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "resume_delivery")
public class ResumeDelivery implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    private long resumeDeliveryId;
    private long recruitmentId;
    private String userId;
    private long resumeId;
    private long deliveryStatus;
    private String remark;


    public long getResumeDeliveryId() {
        return resumeDeliveryId;
    }

    public void setResumeDeliveryId(long resumeDeliveryId) {
        this.resumeDeliveryId = resumeDeliveryId;
    }


    public long getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
    }


    public long getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(long deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
