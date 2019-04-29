package xz.fzu.model;

import xz.fzu.algorithm.EnterpriseSimilarityResult;

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
