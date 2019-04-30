package xz.fzu.algorithm;

/**
 * 该类用来保存企业id以及相似性结果
 * @author LITM
 *
 */
public class EnterpriseSimilarityResult implements Comparable<EnterpriseSimilarityResult>{

	private String userId;
	private Integer recruitmentId;
	private double SimilarityResult;

	public Integer getRecruitmentId() {
		return recruitmentId;
	}

	public void setRecruitmentId(Integer str) {

		this.recruitmentId = str;
	}
	public double getSimilarityResult() {
		
		return SimilarityResult;
	}
	public void setSimilarityResult(double similarityResult) {
		
		SimilarityResult = similarityResult;
	}
	@Override
	public int compareTo(EnterpriseSimilarityResult o) {
		
		return o.getSimilarityResult()>this.getSimilarityResult()?0:-1;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
