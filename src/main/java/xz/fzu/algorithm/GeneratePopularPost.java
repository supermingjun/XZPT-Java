package xz.fzu.algorithm;

import xz.fzu.model.HotPost;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;

import java.sql.Timestamp;
import java.util.*;

/**
 * 生成热门岗位
 *
 * @author LITM
 * @时间2019年5月3日 23:13:42
 */
public class GeneratePopularPost {

    public static final int[] HOT_LEVEL = {0,1,3,5};
    public static final int[] DAYS = {10,30,90};
    private Map<Long,Recruitment> map = new HashMap<>(400);
    /**
     * 获得招聘信息相对于当前时间的天数
     * @param recruitmentId
     * @return
     */
    public long gainDays(long recruitmentId){

        Timestamp beginDate = null;
        Timestamp endDate = null;
        endDate = new Timestamp(System.currentTimeMillis());
        beginDate = map.get(recruitmentId).getPublishTime();
        long day = (endDate.getTime() - beginDate.getTime())/(24*60*60*1000);
        return day;
    }

    /**
     * 获得招聘信息对应的热度
     * @param day
     * @return
     */
    public int judge(long day){

        if(day<= DAYS[0]){
            return HOT_LEVEL[3];
        }
        else if(day<=DAYS[1]&&day>DAYS[0]){
            return HOT_LEVEL[2];
        }
        else if(day<=DAYS[2]&&day>DAYS[1]){
            return HOT_LEVEL[1];
        }
        else{
            return HOT_LEVEL[0];
        }
    }

    /**
     * 将招聘信息与招聘信息id进行映射
     * @param recruitments
     */
    public void recruitmentMapping(List<Recruitment> recruitments){

        for(Recruitment recruitment : recruitments){
            map.put(recruitment.getRecruitmentId(),recruitment);
        }
    }
    /**
     * 生成热门岗位列表
     * @param resumeDeliveryRecords
     * @return
     */
    public List<HotPost> generatePopularPostRank(List<ResumeDelivery> resumeDeliveryRecords,
                                                 List<Recruitment> recruitments) {

        recruitmentMapping(recruitments);
        Map<Long, HotPost> hotPosts = new HashMap<>(400);
        for (ResumeDelivery resumeDeliberyRecord : resumeDeliveryRecords) {
            long recruitmentId = resumeDeliberyRecord.getRecruitmentId();
            if (hotPosts.get(recruitmentId) != null) {
                long day = gainDays(recruitmentId);
                int hotLevel = judge(day);
                HotPost hotPost = hotPosts.get(recruitmentId);
                hotPost.heatAdd(hotLevel);
            } else {
                int heat = 1;
                HotPost hotPost = new HotPost();
                hotPost.setRecruitmentId(recruitmentId);
                hotPost.setHeat(heat);
                hotPosts.put(recruitmentId, hotPost);
            }
        }

        List<HotPost> hotPosts2 = new ArrayList<>(hotPosts.values());
        Collections.sort(hotPosts2);
        return hotPosts2;
    }
}
