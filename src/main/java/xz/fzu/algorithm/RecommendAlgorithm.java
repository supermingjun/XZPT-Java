package xz.fzu.algorithm;

import xz.fzu.model.RecommendResult;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;
import java.util.Map;

/**
 * 行业标签
 * 1	开发|测试|运维类
 * 2	产品|需求|项目类
 * 3	运营|编辑|客服类
 * 4	市场|商务类
 * 5	销售类
 * 6	综合职能|高级管理类
 * 7	金融类
 * 8	文娱|传媒|艺术|体育类
 * 9	教育|培训类
 * 10	商业服务|专业服务类
 * 11	贸易|批发|零售|租赁业类
 * 12	交通|运输|物流|仓储类
 * 13	房地产|建筑|物业类
 * 14	生产|加工|制造类
 * 15	能源矿产|农林牧渔类
 * 16	化工|生物|制药|医护类
 * 17	公务员|其他类
 * <p>
 * <p>
 * 学历
 * 1   学历不限
 * 2   大专及以上
 * 3   本科及以上
 * 4   硕士及以上
 * 5   博士及以上
 * <p>
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 * <p>
 * 工作类型
 * 1   实习
 * 2   兼职
 * 3   全职
 */
/**
 * 学历
 * 1   学历不限
 * 2   大专及以上
 * 3   本科及以上
 * 4   硕士及以上
 * 5   博士及以上
 * <p>
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 * <p>
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 * <p>
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 * <p>
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 */
/**
 * 工作时间
 * 1   955
 * 2   965
 * 3   956
 * 4   996
 */

/**
 * 工作类型
 * 1   实习
 * 2   兼职
 * 3   全职
 */

/**
 * 算法接口
 *
 * @author LITM
 */
public class RecommendAlgorithm {

    /**
     * 推荐算法
     *
     * @param upf
     * @param rps
     */
    public List<RecommendResult> recomAlgorithm(UserProfile upf, List<RecruitmentProfile> rps, int n) {

        AlgorithImplement algorithImplement = new AlgorithImplement();
        List<RecruitmentProfile> preScreeningResults = algorithImplement.directFiltration(upf, rps);
        Map<Integer, double[]> weightResults = algorithImplement.quantization(upf, preScreeningResults);
        List<RecommendResult> esrs = algorithImplement.computationalSimilarity(upf.getUserId(), weightResults);
        esrs = algorithImplement.getTopN(esrs, n);

            return esrs;
        }
	/*public static void main(String[] args) {
		
		RecommendAlgorithm ra = new RecommendAlgorithm();
		UserProfile upf = new UserProfile("975835798",1,"北京",0,1,"",3,"");
		List<RecruitmentProfile> rps = new ArrayList<RecruitmentProfile>();
		RecruitmentProfile rp1 = new RecruitmentProfile(975835790,2," 北京 ",1,1,"","","");
		RecruitmentProfile rp2 = new RecruitmentProfile(975835791,1,"  北京 ",1,1,"","","");
		RecruitmentProfile rp3 = new RecruitmentProfile(975835792,1," 北京 ",1,1,"","","");
		RecruitmentProfile rp4 = new RecruitmentProfile(975835793,1," 北京 ",1,1,"","","");
		RecruitmentProfile rp5 = new RecruitmentProfile(975835794,1,"北京 ",1,1,"","","");
		RecruitmentProfile rp6 = new RecruitmentProfile(975835795,1,"北京 ",1,2,"","","");
		RecruitmentProfile rp7 = new RecruitmentProfile(975835796,1,"北京 ",1,1,"","","");
		RecruitmentProfile rp8 = new RecruitmentProfile(975835797,1,"北京 ",1,2,"","","");
		rps.add(rp1);
		rps.add(rp2);
		rps.add(rp3);
		rps.add(rp4);
		rps.add(rp5);
		rps.add(rp6);
		rps.add(rp7);
		rps.add(rp8);
		List<RecommendResult> list = ra.recomAlgorithm(upf, rps, 5);
		for(RecommendResult er : list) {
			System.out.println(er.getUserId()+" "+er.getRecruitmentId()+" "+er.getSimilarityResult());
		}
	}*/
}
