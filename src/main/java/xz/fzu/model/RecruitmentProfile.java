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
    private int recruitmentId;
    private int industryLabel;
    private String location;
    private int workTime;
    private int jobType;
    private String salary;
    private String degree;
    private String stationLabel;

}
