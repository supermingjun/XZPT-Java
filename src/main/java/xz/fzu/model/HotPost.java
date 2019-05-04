package xz.fzu.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 热门岗位对象
 * @author LITM
 * @time:2019年5月3日 23:13:23
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotPost implements Comparable<HotPost>{
	private int id;
	private int recruitmentId;
	private int heat;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecruitmentId() {
		return recruitmentId;
	}
	public void setRecruitmentId(int recruitmentId) {
		this.recruitmentId = recruitmentId;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public void heatAdd() {
		heat = heat+1;
	}
	@Override
	public int compareTo(HotPost o) {
		return this.getHeat()>o.getHeat()?0:-1;
	}
}
