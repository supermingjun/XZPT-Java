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

}
