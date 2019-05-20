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

}
