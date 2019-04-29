package xz.fzu.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用来存储算法筛选的结果
 * @author LITM
 *
 */
public class FiltrationResult {
	
	private String userId;
	private List<EnterpriseSimilarityResult> enterpriseSimilarityResults = new ArrayList<EnterpriseSimilarityResult>();
	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		
		this.userId = userId;
	}
	public List<EnterpriseSimilarityResult> getEnterpriseSimilarityResults() {
		
		return enterpriseSimilarityResults;
	}
	public void setEnterpriseSimilarityResults(List<EnterpriseSimilarityResult> enterpriseSimilarityResults) {
		
		this.enterpriseSimilarityResults = enterpriseSimilarityResults;
	}
}
