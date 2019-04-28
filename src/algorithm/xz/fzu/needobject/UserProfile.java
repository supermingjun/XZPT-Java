package xz.fzu.needobject;

public class UserProfile {
	private String userId;
	private int jobCategory;
	private String location;
	private int workTime;
	private int jobNature;
	private String salary;
	private int degree;
	private int station;
	
	public UserProfile(String userId, int jobCategory, String location, int workTime, int jobNature,
			String salary, int degree, int station) {
		
		this.userId = userId;
		this.jobCategory = jobCategory;
		this.location = location;
		this.workTime = workTime;
		this.jobNature = jobNature;
		this.salary = salary;
		this.degree = degree;
		this.station = station;
	}
	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		
		this.userId = userId;
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
	public int getStation() {
		
		return station;
	}
	public void setStation(int station) {
		
		this.station = station;
	}
	public int getDegree() {
		
		return degree;
	}
	public void setDegree(int degree) {
		
		this.degree = degree;
	}
}
