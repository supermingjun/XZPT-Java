package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 该类用来保存招聘信息画像
 *
 * @author LITM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RecruitmentProfile {
    private Long recruitmentId;
    private Long industryLabel;
    private String location;
    private Long workTime;
    private Long jobType;
    private String salary;
    private String degree;
    private String stationLabel;
    private String jobName;
//    public RecruitmentProfile(){
//
//    }
//    public RecruitmentProfile(Long recruitmentId, Long industryLabel, String location, Long workTime, Long jobType, String salary, String degree, String stationLabel, String jobName) {
//        this.recruitmentId = recruitmentId;
//        this.industryLabel = industryLabel;
//        this.location = location;
//        this.workTime = workTime;
//        this.jobType = jobType;
//        this.salary = salary;
//        this.degree = degree;
//        this.stationLabel = stationLabel;
//        this.jobName = jobName;
//    }
}
