package xz.fzu.heatalgorithm;
/**
 * 简历投递记录
 * @author LITM
 * @时间2019年5月3日 23:14:12
 */
public class ResumeDeliveryRecord {
		public ResumeDeliveryRecord(int resumeDeliveryId, int recruitmentId, String userId, int resumeId,
			int deliveryStatus, String remark) {
		super();
		this.resumeDeliveryId = resumeDeliveryId;
		this.recruitmentId = recruitmentId;
		this.userId = userId;
		this.resumeId = resumeId;
		this.deliveryStatus = deliveryStatus;
		this.remark = remark;
	}
		private int resumeDeliveryId;
		private int recruitmentId;
		private String userId;
		private int resumeId;
		private int deliveryStatus;
		private String remark;
		
		public int getResumeDeliveryId() {
			return resumeDeliveryId;
		}
		public void setResumeDeliveryId(int resumeDeliveryId) {
			this.resumeDeliveryId = resumeDeliveryId;
		}
		public int getRecruitmentId() {
			return recruitmentId;
		}
		public void setRecruitmentId(int recruitmentId) {
			this.recruitmentId = recruitmentId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public int getResumeId() {
			return resumeId;
		}
		public void setResumeId(int resumeId) {
			this.resumeId = resumeId;
		}
		public int getDeliveryStatus() {
			return deliveryStatus;
		}
		public void setDeliveryStatus(int deliveryStatus) {
			this.deliveryStatus = deliveryStatus;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
}
