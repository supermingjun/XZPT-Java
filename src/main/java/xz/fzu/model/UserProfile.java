package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 该类为用户画像
 *
 * @author LITM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserProfile {
    private String userId;
    private Long industryLabel;
    private String expectedCity;
    private Long workTime;
    private Long jobType;
    private String expectSalary;
    private Long highestEducation;
    private String stationLabel;
//
//    public UserProfile(String userId, Long industryLabel, String expectedCity, Long workTime, Long jobType, String expectSalary, Long highestEducation, String stationLabel) {
//        this.userId = userId;
//        this.industryLabel = industryLabel;
//        this.expectedCity = expectedCity;
//        this.workTime = workTime;
//        this.jobType = jobType;
//        this.expectSalary = expectSalary;
//        this.highestEducation = highestEducation;
//        this.stationLabel = stationLabel;
//    }
}
