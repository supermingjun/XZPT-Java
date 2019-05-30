package xz.fzu.algorithm;

import xz.fzu.model.HotPost;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IRecruitmentService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * 生成热门岗位
 *
 * @author LITM
 * @时间2019年5月3日 23:13:42
 */
public class GeneratePopularPost {
    @Resource
    ICompanyService iCompanyService;
    @Resource
    IRecruitmentService iRecruitmentService;
    public static final int[] HOT_LEVEL = {0,1,3,5};
    public static final int[] DAYS = {10,30,90};

    /**
     * 获得招聘信息相对于当前时间的天数
     * @param recruitmentId
     * @return
     */
    public long gainDays(long recruitmentId){
        Timestamp beginDate = null;
        Timestamp endDate = null;
        endDate = new Timestamp(System.currentTimeMillis());
        try{
            beginDate = iRecruitmentService.getRecruitmentById(recruitmentId).getPublishTime();
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
     * 生成热门岗位列表
     * @param resumeDeliveryRecords
     * @return
     */
    public List<HotPost> generatePopularPostRank(List<ResumeDelivery> resumeDeliveryRecords) {

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

    /**
     * list设置招聘信息的公司名字
     *
     * @param list 招聘信息数组
     * @return java.util.List<xz.fzu.vo.Recruitment>
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void listSetCompanyName(List<Recruitment> list) {

        for (Recruitment recruitment : list) {
            setCompanyName(recruitment);
        }
    }

    /**
     * 设置招聘信息公司名字
     *
     * @param recruitmentVO 招聘信息
     * @return xz.fzu.vo.Recruitment
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void setCompanyName(Recruitment recruitmentVO) {

        String companyName = "公司不存在";
        try {
            companyName = iCompanyService.getInfoByCompanyId(recruitmentVO.getCompanyId()).getCompanyName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recruitmentVO.setCompanyName(companyName);
    }
}
