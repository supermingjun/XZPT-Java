package xz.fzu.algorithm;

import java.util.ArrayList;
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
import java.util.List;
import java.util.Map;

import xz.fzu.algorithmentry.RecomAlgorithm;
import xz.fzu.needobject.EnterpriseSimilarityResult;
import xz.fzu.needobject.FiltrationResult;
import xz.fzu.needobject.RecruitmentProfile;
import xz.fzu.needobject.UserProfile;

public class RecomAlgorithm {
	
	/**
	 * 推荐算法
	 * @param upf
	 * @param rps
	 */
	public  List<EnterpriseSimilarityResult> recomAlgorithm(UserProfile upf,List<RecruitmentProfile> rps,int n) {
		
		AlgorithImplement algorithImplement = new AlgorithImplement();
		List<RecruitmentProfile> preScreeningResults = algorithImplement.directFiltration(upf,rps);
		Map<Integer,double[]> weightResults = algorithImplement.quantization(upf,preScreeningResults);
		List<EnterpriseSimilarityResult> esrs= algorithImplement.computationalSimilarity(upf.getUserId(),weightResults);
		esrs = algorithImplement.getTopN(esrs,n);
		
		return esrs;
	}
//	public static void main(String[] args) {
//		
//		RecomAlgorithm ra = new RecomAlgorithm();
//		UserProfile upf = new UserProfile("975835798",1,"全职",1,1,"",3,"");
//		List<RecruitmentProfile> rps = new ArrayList<RecruitmentProfile>();
//		RecruitmentProfile rp1 = new RecruitmentProfile(975835790,2,"全职",1,1,"9k~20k","学历不限","");
//		RecruitmentProfile rp2 = new RecruitmentProfile(975835791,1,"全职",1,1,"3k~5k","大专及以上","");
//		RecruitmentProfile rp3 = new RecruitmentProfile(975835792,1,"全职",1,1,"6k~7k","本科及以上","");
//		RecruitmentProfile rp4 = new RecruitmentProfile(975835793,1,"全职",1,1,"4k~6k","","");
//		RecruitmentProfile rp5 = new RecruitmentProfile(975835794,1,"全职",1,1,"3k~4k","本科及以上","");
//		RecruitmentProfile rp6 = new RecruitmentProfile(975835795,1,"全职",1,2,"5k~7k","本科及以上","");
//		RecruitmentProfile rp7 = new RecruitmentProfile(975835796,1,"全职",1,1,"5k~7k","本科及以上","");
//		RecruitmentProfile rp8 = new RecruitmentProfile(975835797,1,"全职",1,2,"6k~9k","本科及以上","");
//		rps.add(rp1);
//		rps.add(rp2);
//		rps.add(rp3);
//		rps.add(rp4);
//		rps.add(rp5);
//		rps.add(rp6);
//		rps.add(rp7);
//		rps.add(rp8);
//		List<EnterpriseSimilarityResult> list = ra.recomAlgorithm(upf, rps, 5);
//		for(EnterpriseSimilarityResult er : list) {
//			System.out.println(er.getUserId()+" "+er.getRecruitmentId()+" "+er.getSimilarityResult());
//		}
//	}
}
