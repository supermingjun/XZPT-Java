package xz.fzu.algorithm;
/**
 * 该类为用户画像
 * @author LITM
 *
 */
public class UserProfile {
	private String userId;
	private int industryLabel;
	private String expectedCity;
	private int workTime;
	private int jobType;
	private String expectSalary;
	private int highestEducation;
	private String stationLabel;
	
	public UserProfile(String userId, int industryLabel, String expectedCity, int workTime, int jobType,
			String expectSalary, int highestEducation, String stationLabel) {
		
		this.userId = userId;
		this.industryLabel = industryLabel;
		this.expectedCity = expectedCity;
		this.workTime = workTime;
		this.jobType = jobType;
		this.expectSalary = expectSalary;
		this.highestEducation = highestEducation;
		this.stationLabel = stationLabel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(int industryLabel) {
		this.industryLabel = industryLabel;
	}

	public String getExpectedCity() {
		return expectedCity;
	}

	public void setExpectedCity(String expectedCity) {
		this.expectedCity = expectedCity;
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

	public String getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(String expectSalary) {
		this.expectSalary = expectSalary;
	}

	public String getStationLabel() {
		return stationLabel;
	}

	public void setStationLabel(String stationLabel) {
		this.stationLabel = stationLabel;
	}

	public int getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(int highestEducation) {
		this.highestEducation = highestEducation;
	}
	
}
