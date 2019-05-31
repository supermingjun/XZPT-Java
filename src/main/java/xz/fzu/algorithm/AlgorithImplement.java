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
    private static final long DEFAULT = 0;
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
     * 处理招聘信息某些为空的字段，如果某些字段为null，则把该字段设置为默认值
     * @param rp
     */
    public static void handleNull(RecruitmentProfile rp){
        if(rp.getIndustryLabel()==null){
            rp.setIndustryLabel(DEFAULT);
        }
        if(rp.getLocation()==null){
            rp.setLocation("福州");
        }
        if(rp.getJobType()==null){
            rp.setJobType(DEFAULT);
        }
    }
    /**
     * 根据硬性指标对招聘信息进行初筛返回筛选结果
     * 硬性指标 行业标签 long，工作时间long，工作地点String
     *
     * @param userProfile
     * @param recruitmentProfiles
     * @return
     */
    public List<RecruitmentProfile> directFiltration(UserProfile userProfile, List<RecruitmentProfile> recruitmentProfiles) {

        List<RecruitmentProfile> tRecruitmentProfiles =new ArrayList<>(400);
        //对行业进行筛选
        Iterator<RecruitmentProfile> iterator = recruitmentProfiles.iterator();
        while(iterator.hasNext()){
            RecruitmentProfile rp = iterator.next();
            handleNull(rp);
            if(userProfile.getIndustryLabel()!=null){
                long industryLabel = userProfile.getIndustryLabel();
                if(industryLabel!=DEFAULT){
                    if(!rp.getIndustryLabel().equals(industryLabel)){
                          iterator.remove();
                    }
                }
            }
        }
          //对工作类型进行筛选
        iterator = recruitmentProfiles.iterator();
        while(iterator.hasNext()){
          RecruitmentProfile rp = iterator.next();
          if(userProfile.getJobType()!=null){
              long jobType = userProfile.getJobType();
              if(jobType!=DEFAULT){
                  if(!rp.getJobType().equals(jobType)){
                      iterator.remove();
                  }
              }
          }
        }
        //对城市进行筛选
        iterator = recruitmentProfiles.iterator();
        while(iterator.hasNext()){
            RecruitmentProfile rp = iterator.next();
            if(userProfile.getExpectedCity()!=null){
                if (!userProfile.getExpectedCity().trim().equals(rp.getLocation().trim())) {
                  tRecruitmentProfiles.add(rp);
                  iterator.remove();
                }
            }
        }
        if(recruitmentProfiles.size()!=0){
            return recruitmentProfiles;
        }
        else {
            return tRecruitmentProfiles;
        }
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
     * @param userProfile
     * @param recruitmentProfile
     * @return
     */
    public double degreeQuan(UserProfile userProfile, RecruitmentProfile recruitmentProfile) {

        long uDegree = userProfile.getHighestEducation() == null ? 0 : userProfile.getHighestEducation();
        String rDegreeRequire = recruitmentProfile.getDegree();
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

    /**
     * 对工作时间进行量化
     * @param userProfile
     * @param recruitmentProfile
     * @return
     */
    public double workTimeQuan(UserProfile userProfile, RecruitmentProfile recruitmentProfile) {

        long uWorkTime = userProfile.getWorkTime() == null ? 0 : userProfile.getWorkTime();
        long rWorkTime = recruitmentProfile.getWorkTime() == null ? 0 : recruitmentProfile.getWorkTime();
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
     * 目前只用学历、薪水和工作时间进行量化加权
     * @param userProfile
     * @param preScreeningResults
     * @return
     */

    public Map<Long, double[]> quantization(UserProfile userProfile, List<RecruitmentProfile> preScreeningResults) {

        Map<Long, double[]> weightResults = new HashMap<>(512);
        for (RecruitmentProfile recruitmentProfile : preScreeningResults) {
            double[] quanValue = new double[3];
            int[] sarlaryRange = regExSalary(recruitmentProfile.getSalary());
            //学历量化加权
            quanValue[0] = degreeQuan(userProfile, recruitmentProfile) * USER_WEIGHT[0];
            //薪水量化加权
            quanValue[1] = salaryQuan(sarlaryRange) * USER_WEIGHT[2];
            //工作时间量化
            quanValue[2] = workTimeQuan(userProfile, recruitmentProfile) * USER_WEIGHT[1];
            weightResults.put(recruitmentProfile.getRecruitmentId(), quanValue);
        }
        return weightResults;
    }

    /**
     * 对招聘信息进行相似度计算
     * @param userId
     * @param weightedResults
     * @return
     */
    public List<RecommendResult> computationalSimilarity(String userId, Map<Long, double[]> weightedResults) {

        List<RecommendResult> recommendResults = new ArrayList<RecommendResult>();
        double result = 0;
        double r1 = 0;
        double r2 = 0;
        double r3 = 0;
        for (Map.Entry<Long, double[]> entry : weightedResults.entrySet()) {
            Long str = entry.getKey();
            double[] dou = entry.getValue();
            for (int i = 0; i < dou.length; i++) {
                r1 += dou[i] * USER_WEIGHT[i];
                r2 += dou[i] * dou[i];
                r3 += USER_WEIGHT[i] * USER_WEIGHT[i];
            }
            result = Double.parseDouble(String.format("%.6f", r1 / Math.sqrt(r2 * r3)));
            RecommendResult recommendResult = new RecommendResult();
            recommendResult.setUserId(userId);
            recommendResult.setRecruitmentId(str);
            recommendResult.setSimilarityResult(result);
            recommendResults.add(recommendResult);
        }
        return recommendResults;
    }

    /**
     * 获取相似度最高的Top-N
     * @param recommendResults
     * @param n
     * @return
     */
    public List<RecommendResult> getTopN(List<RecommendResult> recommendResults, int n) {

        List<RecommendResult> tRecommendResult = recommendResults;
        Collections.sort(recommendResults);
        if (n < recommendResults.size()) {
            tRecommendResult = recommendResults.subList(0, n);
        }
        return tRecommendResult;
    }
}
