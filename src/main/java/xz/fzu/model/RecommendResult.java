package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 该类用来保存企业id以及相似性结果
 *
 * @author LITM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RecommendResult implements Comparable<RecommendResult> {

    private String resultId;
    private String userId;
    private Integer recruitmentId;
    private double similarityResult;

    @Override
    public int compareTo(RecommendResult o) {

        return o.getSimilarityResult() > this.getSimilarityResult() ? 0 : -1;
    }

}
