package xz.fzu.algorithm;

import xz.fzu.model.HotPost;
import xz.fzu.model.Recruitment;
import xz.fzu.model.ResumeDelivery;
import xz.fzu.service.ICompanyService;

import javax.annotation.Resource;
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

    public List<HotPost> generatePopularPostRank(List<ResumeDelivery> resumeDeliveryRecords) {

        Map<Integer, HotPost> hotPosts = new HashMap<Integer, HotPost>(400);
        for (ResumeDelivery resumeDeliberyRecord : resumeDeliveryRecords) {
            int recruitmentId = (int) resumeDeliberyRecord.getRecruitmentId();
            if (hotPosts.get(recruitmentId) != null) {
                HotPost hotPost = hotPosts.get(recruitmentId);
                hotPost.heatAdd();
            } else {
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

    /**
     * list设置招聘信息的公司名字
     *
     * @param list 招聘信息数组
     * @return java.util.List<xz.fzu.vo.Recruitment>
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void listSetCompanyName(List<Recruitment> list) {

        for (int i = 0; i < list.size(); i++) {
            setCompanyName(list.get(i));
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
