package xz.fzu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import xz.fzu.model.Recruitment;

import java.io.Serializable;

/**
 * Auto-generator
 *
 * @author Murphy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentVO extends Recruitment implements Serializable {

    private static final long serialVersionUID = 2;

    private String companyName;

    public RecruitmentVO(Recruitment recruitment) {

        setRecruitmentId(recruitment.getRecruitmentId());
        setPublishTime(recruitment.getPublishTime());
        setValidate(recruitment.getValidate());
        setCompanyId(recruitment.getCompanyId());
        setJobName(recruitment.getJobName());
        setDescription(recruitment.getDescription());
        setContact(recruitment.getContact());
        setLocation(recruitment.getLocation());
        setDeliveryRequest(recruitment.getDeliveryRequest());
        setSalary(recruitment.getSalary());
        setDegree(recruitment.getDegree());
        setWorkTime(recruitment.getWorkTime());
        setIndustryLabel(recruitment.getIndustryLabel());
        setStationLabel(recruitment.getStationLabel());
        setJobType(recruitment.getJobType());
    }

    public RecruitmentVO() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
