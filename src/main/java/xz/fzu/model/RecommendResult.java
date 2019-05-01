package xz.fzu.model;


import java.io.Serializable;

public class RecommendResult implements Serializable {

    private static final long serialVersionUID = 1;

    private long resultId;
    private String userId;
    private long recruitmentId;
    private double similarityResult;


    public long getResultId() {
        return resultId;
    }

    public void setResultId(long resultId) {
        this.resultId = resultId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public long getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }


    public double getSimilarityResult() {
        return similarityResult;
    }

    public void setSimilarityResult(double similarityResult) {
        this.similarityResult = similarityResult;
    }

}
