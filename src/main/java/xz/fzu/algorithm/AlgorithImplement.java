package xz.fzu.algorithm;

import xz.fzu.model.RecommendResult;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类为具体的算法实现
 *
 * @author LITM
 */
public class AlgorithImplement {
    /**
     * 根据硬性指标对招聘信息进行过滤
     *
     * @author LITM
     * @param UserProfile,List<RecruimentProfile>
     * @return List<RecruimentProfile>
     */
    private static final int[] SALARY_SEGMENTATION = {3, 5, 7, 10, 15, 20};
    private static final double[] DEGREE_QUALITY_VALUE = {0.3, 0.5, 0.8, 1.0};
    private static final double[] SALARY_QUALITY_VALUE = {0.1, 0.15, 0.25, 0.35, 0.5, 0.7, 1.0};
    /**
     * 根据用户对学历和薪水的重视程度分别设其权重为0.4,0.6
     */
    private static final double[] USER_WEIGHT = {0.5, 0.3, 0.2};
    private static final double[] WORK_TIME_QUALITY_VALUE = {0.1, 0.3, 0.5, 0.8, 1.0};
    private static final int[] DEFAULT_SALARY_RANGE = {0, 0};
    /**
     * 将学历映射成int方便后面比较
     */
    private Map<String, Integer> typeOfDegree = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;

        {
            put("学历不限", 1);
            put("大专及以上", 2);
            put("本科及以上", 3);
            put("硕士及以上", 4);
            put("博士及以上", 5);
        }

    };

    /**
     * 根据硬性指标对招聘信息进行初筛返回筛选结果
     * 硬性指标 行业标签 int，工作时间int，工作地点String，工作性质int
     *
     * @param upf
     * @param rps
     * @return
     */
    public List<RecruitmentProfile> directFiltration(UserProfile upf, List<RecruitmentProfile> rps) {

        Iterator<RecruitmentProfile> iterator = rps.iterator();
        while (iterator.hasNext()) {

            RecruitmentProfile rp = iterator.next();
            if (upf.getIndustryLabel() != null && upf.getIndustryLabel().equals(rp.getIndustryLabel())) {
                iterator.remove();
                //去掉字符串首尾的空白后进行比较
            } else if (upf.getExpectedCity() != null) {
                if (!upf.getExpectedCity().trim().equals(rp.getLocation().trim())) {
                    iterator.remove();
                }
            } else if (upf.getJobType() != null && upf.getIndustryLabel().equals(rp.getJobType())) {
                iterator.remove();
            }
        }
        return rps;

    }

    /**
     * 利用正则取得薪水值转换为数字方便后续比较
     *
     * @param salary
     * @return
     */
    public int[] regExSalary(String salary) {
        if (salary == null) {
            return DEFAULT_SALARY_RANGE;
        }
        int[] salaryRange = new int[2];
        String regex1 = "^\\d*(?=[k|K])";
        String regex2 = "(?<=[-|~])\\d*";
        Pattern p1 = Pattern.compile(regex1);
        Pattern p2 = Pattern.compile(regex2);
        Matcher m1 = p1.matcher(salary);
        Matcher m2 = p2.matcher(salary);
        m1.find();
        m2.find();
        try {
            salaryRange[0] = Integer.parseInt(m1.group());
            salaryRange[1] = Integer.parseInt(m2.group());
        } catch (Exception e) {

        }
        return salaryRange;

    }

    /**
     * 对薪水进行量化
     *
     * @param salaryRange
     * @return
     */
    public double salaryQuan(int[] salaryRange) {

        if (salaryRange[0] >= SALARY_SEGMENTATION[5]) {
            return SALARY_QUALITY_VALUE[6];
        }
        if (salaryRange[0] >= SALARY_SEGMENTATION[4]) {
            return SALARY_QUALITY_VALUE[5];
        } else if (salaryRange[0] >= SALARY_SEGMENTATION[3]) {
            return SALARY_QUALITY_VALUE[4];
        } else if (salaryRange[0] >= SALARY_SEGMENTATION[2]) {
            return SALARY_QUALITY_VALUE[3];
        } else if (salaryRange[0] >= SALARY_SEGMENTATION[1]) {
            return SALARY_QUALITY_VALUE[2];
        } else if (salaryRange[0] >= SALARY_SEGMENTATION[0]) {
            return SALARY_QUALITY_VALUE[1];
        } else {
            return SALARY_QUALITY_VALUE[0];
        }

    }

    /**
     * 对学历进行量化
     *
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
        if (uDegree == rdegreeRequire) {
            return DEGREE_QUALITY_VALUE[3];
        } else if (1 == rdegreeRequire) {
            return DEGREE_QUALITY_VALUE[2];
        } else if (uDegree > rdegreeRequire) {
            return DEGREE_QUALITY_VALUE[1];
        } else {
            return DEGREE_QUALITY_VALUE[0];
        }

    }

    public double workTimeQuan(UserProfile upf, RecruitmentProfile rp) {

        int uWorkTime = upf.getWorkTime();
        int rWorkTime = rp.getWorkTime();
        if (uWorkTime == 0) {
            return WORK_TIME_QUALITY_VALUE[3];
        } else if (uWorkTime == rWorkTime) {
            return WORK_TIME_QUALITY_VALUE[4];
        } else if (uWorkTime > rWorkTime) {
            return WORK_TIME_QUALITY_VALUE[2];
        } else if (uWorkTime < rWorkTime) {
            return WORK_TIME_QUALITY_VALUE[0];
        } else {
            return WORK_TIME_QUALITY_VALUE[1];
        }
    }

    /**
     * 对招聘信息关键字段进行量化加权
     * 目前只用学历和薪水进行量化加权
     *
     * @param upf
     * @return
     */

    public Map<Integer, double[]> quantization(UserProfile upf, List<RecruitmentProfile> preScreeningResults) {

        Map<Integer, double[]> weightResults = new HashMap<>(512);
        Iterator<RecruitmentProfile> iterator = preScreeningResults.iterator();
        while (iterator.hasNext()) {
            RecruitmentProfile rp = iterator.next();
            double[] quanValue = new double[3];
            int[] sarlaryRange = regExSalary(rp.getSalary());
            //学历量化加权
            quanValue[0] = degreeQuan(upf, rp) * USER_WEIGHT[0];
            //薪水量化加权
            quanValue[1] = salaryQuan(sarlaryRange) * USER_WEIGHT[2];
            //工作时间量化
            quanValue[2] = workTimeQuan(upf, rp) * USER_WEIGHT[1];
            weightResults.put(rp.getRecruitmentId(), quanValue);
        }

        return weightResults;
    }

    /**
     * 对招聘信息进行相似度计算
     *
     * @param weightedResults
     */
    public List<RecommendResult> computationalSimilarity(String userId, Map<Integer, double[]> weightedResults) {

        List<RecommendResult> esrs = new ArrayList<RecommendResult>();
        double result = 0;
        double r1 = 0;
        double r2 = 0;
        double r3 = 0;
        Iterator<Map.Entry<Integer, double[]>> iterator = weightedResults.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, double[]> entry = iterator.next();
            Integer str = entry.getKey();
            double[] dou = entry.getValue();
            for (int i = 0; i < dou.length; i++) {
                r1 += dou[i] * USER_WEIGHT[i];
                r2 += dou[i] * dou[i];
                r3 += USER_WEIGHT[i] * USER_WEIGHT[i];
            }
            result = Double.parseDouble(String.format("%.6f", r1 / Math.sqrt(r2 * r3)));
            RecommendResult esr = new RecommendResult();
            esr.setUserId(userId);
            esr.setRecruitmentId(str);
            esr.setSimilarityResult(result);
            esrs.add(esr);
        }

        return esrs;
    }

    /**
     * 获取相似度最高的Top-N
     *
     * @param n
     */
    public List<RecommendResult> getTopN(List<RecommendResult> esrs, int n) {

        List<RecommendResult> tesrs = esrs;
        Collections.sort(esrs);
        if (n < esrs.size()) {
            tesrs = esrs.subList(0, n);
        }

        return tesrs;
    }
}
