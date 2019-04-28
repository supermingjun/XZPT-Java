package xz.fzu.needobject;

/**
 * 该类用来保存企业id以及相似性结果。
 * @author LITM
 *
 */
public class EnterpriseSimilarityResult implements Comparable<EnterpriseSimilarityResult>{
	
	private String recruitmentId;
	private double SimilarityResult;
	public String getRecruitmentId() {
		return recruitmentId;
	}
	public void setRecruitmentId(String recruitmentId) {
		
		this.recruitmentId = recruitmentId;
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
	
}
