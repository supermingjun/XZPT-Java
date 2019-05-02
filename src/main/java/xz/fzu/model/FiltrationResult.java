package xz.fzu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用来存储算法筛选的结果
 * @author LITM
 *
 */
public class FiltrationResult {
	
	private String userId;
	// 企业相似性结果
    private List<RecommendResult> enterpriseSimilarityResults = new ArrayList<RecommendResult>();
	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		
		this.userId = userId;
	}

    public List<RecommendResult> getEnterpriseSimilarityResults() {
		
		return enterpriseSimilarityResults;
	}

    public void setEnterpriseSimilarityResults(List<RecommendResult> enterpriseSimilarityResults) {
		
		this.enterpriseSimilarityResults = enterpriseSimilarityResults;
	}
}
