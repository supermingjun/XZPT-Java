package xz.fzu.algorithm;

import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类为具体的算法实现
 * @author LITM
 *
 */
public class AlgorithImplement {
	/**
	 * 根据硬性指标对招聘信息进行过滤
	 * @author LITM
	 * @param UserProfile,List<RecruimentProfile>
	 * @return List<RecruimentProfile>
	 */
	private double[] degreeQuanValue = {0.3,0.5,0.8,1.0};
	private double[] salaryQuanValue = {0.1,0.15,0.25,0.35,0.5,0.7,1.0};
	private double[] uWeight = {0.5,0.3,0.2};//根据用户对学历和薪水的重视程度分别设其权重为0.4,0.6
	private double[] workTimeQuanValue = {0.1,0.3,0.5,1.0};
 	//将学历映射成int方便后面比较
	private Map<String,Integer> typeOfDegree = new HashMap<String,Integer>(){
		private static final long serialVersionUID = 1L;

	{put("学历不限",1);put("大专及以上",2);put("本科及以上",3);put("硕士及以上",4);put("博士及以上",5);}
	
	};
	/**
	 * 根据硬性指标对招聘信息进行初筛返回筛选结果
	 * 硬性指标 行业标签 int，工作时间int，工作地点String，工作性质int
	 * @param upf
	 * @param rps
	 * @return
	 */
    public List<RecruitmentProfile> directFiltration(UserProfile upf, List<RecruitmentProfile> rps) {
		
		Iterator<RecruitmentProfile> iterator = rps.iterator();
		while (iterator.hasNext()) {
			
			RecruitmentProfile rp = iterator.next();
			if(upf.getIndustryLabel()!=rp.getIndustryLabel()) {
				iterator.remove();
			}
			else if(!upf.getExpectedCity().equals(rp.getLocation())) {
				iterator.remove();
			}
			else if(upf.getJobType()!=rp.getJobType()) {
				iterator.remove();
			}
		}
		return rps; 
		
	}
	/**
	 * 利用正则取得薪水值转换为数字方便后续比较
	 * @param salary
	 * @return
	 */
	public int[] regExSalary(String salary) {
		
		int[] salaryRange = new int[2];
		String regex1 = "^\\d*(?=k)";
		String regex2 = "(?<=~)\\d*";
		Pattern p1 = Pattern.compile(regex1);
		Pattern p2 = Pattern.compile(regex2);
		Matcher m1 = p1.matcher(salary);
		Matcher m2 = p2.matcher(salary);
		m1.find();
		m2.find();
		salaryRange[0] = Integer.parseInt(m1.group());
		salaryRange[1] = Integer.parseInt(m2.group());
		return salaryRange;
		
	}
	/**
	 * 对薪水进行量化
	 * @param salaryRange
	 * @return
	 */
	public double salaryQuan(int[] salaryRange) {
		
		if (salaryRange[0]>=20) {
			return salaryRange[6];
		}
		if (salaryRange[0]>=15) {
			return salaryQuanValue[5];
		}
		else if (salaryRange[0]>=10) {
			return salaryQuanValue[4];
		}
		else if (salaryRange[0]>=7) {
			return salaryQuanValue[3];
		}
		else if (salaryRange[0]>=5) {
			return salaryQuanValue[2];
		}
		else if (salaryRange[0]>=3)
			return salaryQuanValue[1];
		else 
			return salaryQuanValue[0];
		
	}
	/**
	 * 对学历进行量化
	 * @param upf
	 * @param rp
	 * @return
	 */
    public double degreeQuan(UserProfile upf, RecruitmentProfile rp) {
		
		int uDegree = upf.getHighestEducation();
		String rDegreeRequire = rp.getDegree();
        int rdegreeRequire = 1;
        try {
            rdegreeRequire = typeOfDegree.get(rDegreeRequire);
        } catch (NullPointerException e) {
            rdegreeRequire = 1;
        }
		if (uDegree==rdegreeRequire) {
			return degreeQuanValue[3];
		}
		else if (1==rdegreeRequire) {
			return degreeQuanValue[2];
		}
		else if (uDegree>rdegreeRequire){
			return degreeQuanValue[1];
		}
		else {
			return degreeQuanValue[0];
		}
		
	}
	public double workTimeQuan(UserProfile upf,RecruitmentProfile rp) {
		
		int uWorkTime = upf.getWorkTime();
		int rWorkTime = rp.getWorkTime();
		if(uWorkTime == rWorkTime) {
			return workTimeQuanValue[3];
		}
		else if(uWorkTime > rWorkTime) {
			return workTimeQuanValue[2];
		}
		else if(uWorkTime < rWorkTime) {
			return workTimeQuanValue[0];
		}
		else return workTimeQuanValue[1];
	}
	/**
	 * 对招聘信息关键字段进行量化加权
	 * 目前只用学历和薪水进行量化加权
	 * @param upf
	 * @return
	 */

    public Map<Integer, double[]> quantization(UserProfile upf, List<RecruitmentProfile> preScreeningResults) {

        Map<Integer, double[]> weightResults = new HashMap<Integer, double[]>();
		Iterator<RecruitmentProfile> iterator = preScreeningResults.iterator();
		while(iterator.hasNext()) {
			RecruitmentProfile rp = iterator.next();
			double[] quanValue = new double[3];
			int[] sarlaryRange = regExSalary(rp.getSalary());
			quanValue[0] = degreeQuan(upf,rp)*uWeight[0];//学历量化加权
			quanValue[1] = salaryQuan(sarlaryRange)*uWeight[2];//薪水量化加权
			quanValue[2] = workTimeQuan(upf, rp)*uWeight[1];//工作时间量化
			weightResults.put(rp.getRecruitmentId(), quanValue);
		}
		
		return weightResults;
	}
	
	/**
	 * 对招聘信息进行相似度计算
	 * @param weightedResults
	 */
    public List<EnterpriseSimilarityResult> computationalSimilarity(String userId, Map<Integer, double[]> weightedResults) {
		
		List<EnterpriseSimilarityResult> esrs = new ArrayList<EnterpriseSimilarityResult>();
		double result = 0;
		double r1 = 0;
		double r2 = 0;
		double r3 = 0;
        Iterator<Map.Entry<Integer, double[]>> iterator = weightedResults.entrySet().iterator();
		while (iterator.hasNext()) {
            Map.Entry<Integer, double[]> entry = iterator.next();
            Integer str = entry.getKey();
			double[] dou = entry.getValue();
			for (int i=0;i<dou.length;i++) {
				r1+=dou[i]*uWeight[i];
				r2+=dou[i]*dou[i];
				r3+=uWeight[i]*uWeight[i];
			}
			result = Double.parseDouble(String.format("%.6f", r1/Math.sqrt(r2*r3)));
			EnterpriseSimilarityResult esr = new EnterpriseSimilarityResult();
            esr.setUserId(userId);
			esr.setRecruitmentId(str);
			esr.setSimilarityResult(result);
			esrs.add(esr);
		}


        return esrs;
 	}
	/**
	 * 获取相似度最高的Top-N
	 * @param n
	 */
    public List<EnterpriseSimilarityResult> getTopN(List<EnterpriseSimilarityResult> esrs, int n) {

        List<EnterpriseSimilarityResult> tesrs = esrs;
        Collections.sort(esrs);
		if (n<esrs.size()) {
            tesrs = esrs.subList(0, n - 1);
		}

        return tesrs;
	}
}
