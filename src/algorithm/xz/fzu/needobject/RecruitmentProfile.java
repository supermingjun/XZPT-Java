package xz.fzu.needobject;

/**
 * 该类用来保存招聘信息画像
 * @author LITM
 *
 */
public class RecruitmentProfile {
	private String recruitmentId;
	private int jobCategory;
	private String location;
	private int workTime;
	private int jobNature;
	private String salary;
	private String degreeRequire;
	private int station;
	
	public RecruitmentProfile(String recruitmentId, int jobCategory, String location, int workTime,
			int jobNature, String salary, String degreeRequire, int station) {
		
		this.recruitmentId = recruitmentId;
		this.jobCategory = jobCategory;
		this.location = location;
		this.workTime = workTime;
		this.jobNature = jobNature;
		this.salary = salary;
		this.degreeRequire = degreeRequire;
		this.station = station;
	}
	public String getRecruitmentId() {
		
		return recruitmentId;
	}
	public void setRecruitmentId(String recruitmentId) {
		
		this.recruitmentId = recruitmentId;
	}
	public int getJobCategory() {
		
		return jobCategory;
	}
	public void setJobCategory(int jobCategory) {
		
		this.jobCategory = jobCategory;
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
	public int getJobNature() {
		
		return jobNature;
	}
	public void setJobNature(int jobNature) {
		
		this.jobNature = jobNature;
	}
	public String getSalary() {
		
		return salary;
	}
	public void setSalary(String salary) {
		
		this.salary = salary;
	}
	public String getDegreeRequire() {
		
		return degreeRequire;
	}
	public void setDegreeRequire(String degreeRequire) {
		
		this.degreeRequire = degreeRequire;
	}
	public int getStation() {
		
		return station;
	}
	public void setStation(int station) {
		
		this.station = station;
	}
}
