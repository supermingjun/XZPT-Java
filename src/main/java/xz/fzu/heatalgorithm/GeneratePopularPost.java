package xz.fzu.heatalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 生成热门岗位
 * @author LITM
 * @时间2019年5月3日 23:13:42
 */
public class GeneratePopularPost {
	 	public List<HotPost> generatePopularPostRank(List<ResumeDeliveryRecord> resumeDeliveryRecords) {
	 		
	 		Map<Integer,HotPost> hotPosts = new HashMap<Integer,HotPost>(400);
	 		for(ResumeDeliveryRecord resumeDeliberyRecord : resumeDeliveryRecords) {
	 			int recruitmentId = resumeDeliberyRecord.getRecruitmentId();
	 			if(hotPosts.get(recruitmentId)!=null) {
	 				HotPost hotPost = hotPosts.get(recruitmentId);
	 				hotPost.heatAdd();
	 			}
	 			else {
	 				int heat = 1;
	 				HotPost hotPost = new HotPost();
	 				hotPost.setRecruitmentId(recruitmentId);
	 				hotPost.setHeat(heat);
	 				hotPosts.put(recruitmentId, hotPost);
	 			}
	 		}
	 		List<HotPost> hotPosts2 = new ArrayList<HotPost>(hotPosts.values());
	 		Collections.sort(hotPosts2);
	 		return hotPosts2;
	 	}
}
