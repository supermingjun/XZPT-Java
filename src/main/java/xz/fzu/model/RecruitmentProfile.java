package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 该类用来保存招聘信息画像
 * @author LITM
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentProfile {
	private int recruitmentId;
	private int industryLabel;
	private String location;
	private int workTime;
	private int jobType;
	private String salary;
	private String degree;
	private String stationLabel;

	public int getRecruitmentId() {
		return recruitmentId;
	}

	public void setRecruitmentId(int recruitmentId) {
		this.recruitmentId = recruitmentId;
	}

	public int getIndustryLabel() {
		return industryLabel;
	}
	public void setIndustryLabel(int industryLabel) {
		this.industryLabel = industryLabel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	public int getJobType() {
		return jobType;
	}
	public void setJobType(int jobType) {
		this.jobType = jobType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getStationLabel() {
		return stationLabel;
	}
	public void setStationLabel(String stationLabel) {
		this.stationLabel = stationLabel;
	}
	
	
}
