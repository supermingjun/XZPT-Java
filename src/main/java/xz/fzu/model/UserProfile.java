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
    private int industryLabel;
    private String expectedCity;
    private int workTime;
    private int jobType;
    private String expectSalary;
    private int highestEducation;
    private String stationLabel;

}
