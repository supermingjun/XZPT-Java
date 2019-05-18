package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用来存储算法筛选的结果
 *
 * @author LITM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FiltrationResult {

    private String userId;
    /**
     * 企业相似性结果
     */
    private List<RecommendResult> enterpriseSimilarityResults = new ArrayList<>();

}
