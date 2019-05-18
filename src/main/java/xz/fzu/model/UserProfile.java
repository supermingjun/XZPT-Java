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
    private Integer industryLabel;
    private String expectedCity;
    private Integer workTime;
    private Integer jobType;
    private String expectSalary;
    private Integer highestEducation;
    private String stationLabel;

}
