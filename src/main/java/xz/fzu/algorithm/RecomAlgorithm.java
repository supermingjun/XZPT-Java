package xz.fzu.algorithm;

/**
 * 行业标签
 *  1	开发|测试|运维类
	2	产品|需求|项目类
	3	运营|编辑|客服类
	4	市场|商务类
	5	销售类
	6	综合职能|高级管理类
	7	金融类
	8	文娱|传媒|艺术|体育类
	9	教育|培训类
	10	商业服务|专业服务类
	11	贸易|批发|零售|租赁业类
	12	交通|运输|物流|仓储类
	13	房地产|建筑|物业类
	14	生产|加工|制造类
	15	能源矿产|农林牧渔类
	16	化工|生物|制药|医护类
	17	公务员|其他类
 *
 */
/**
 * 学历
 * 1   学历不限
 * 2   大专及以上
 * 3   本科及以上
 * 4   硕士及以上
 * 5   博士及以上
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

import xz.fzu.model.FiltrationResult;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.List;
import java.util.Map;

public class RecomAlgorithm {
	
	/**
	 * 推荐算法
	 * @param upf
	 * @param rps
	 */
	public FiltrationResult recomAlgorithm(UserProfile upf, List<RecruitmentProfile> rps, int n) {
		
		AlgorithImplement algorithImplement = new AlgorithImplement();
		List<RecruitmentProfile> preScreeningResults = algorithImplement.directFiltration(upf,rps);
		Map<String,double[]> weightResults = algorithImplement.quantization(upf,preScreeningResults);
		FiltrationResult filtrationResult = algorithImplement.computationalSimilarity(upf.getUserId(),weightResults);
		filtrationResult = algorithImplement.getTopN(filtrationResult,n);
		
		return filtrationResult;
	}
}
