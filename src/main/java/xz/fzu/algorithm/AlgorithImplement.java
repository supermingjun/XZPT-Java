package xz.fzu.algorithm;

import xz.fzu.model.RecommendResult;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;
import xz.fzu.util.CityUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类为具体的算法实现
 *
 * @author LITM
 */
public class AlgorithImplement {

    private static final long DEFAULT = 0;
    private static final int[] SALARY_SEGMENTATION = {5, 15, 20, 25, 30, 40};
    private static final double[] CITY_QUALITY_VALUE = {0.1, 0.5, 1.0};
    private static final double[] SALARY_QUALITY_VALUE = {0.1, 0.15, 0.25, 0.35, 0.5, 0.7, 1.0};
    private static final double[] JOB_NAME_QUALITY_VALUE = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
    /**
     * 根据用户对地点、工作时长、薪水、岗位名称的重视程度分别设其权重为0.1,0.2,0.3,0.4
     */
    private static final double[] USER_WEIGHT = {0.3, 0.1, 0.2, 0.4};
    private static final double[] WORK_TIME_QUALITY_VALUE = {0.1, 0.3, 0.5, 0.8, 1.0};
    private static final int[] DEFAULT_SALARY_RANGE = {0, 0};
    /**
     * 将学历映射成int方便后面比较
     */
    private Map<String, Long> typeOfDegree = new HashMap<String, Long>() {
        private static final long serialVersionUID = 1L;

        {
            put("无要求", (long) 0);
            put("初中及以上", (long) 1);
            put("中专/中技及以上", (long) 2);
            put("高中及以上", (long) 3);
            put("大专及以上", (long) 4);
            put("本科及以上", (long) 5);
            put("硕士及以上", (long) 6);
            put("博士及以上", (long) 7);
        }
    };

    /**
     * 处理招聘信息某些为空的字段，如果某些字段为null，则把该字段设置为默认值
     *
     * @param rp
     */
    public static void handleNull(RecruitmentProfile rp) {
        if (rp.getIndustryLabel() == null) {
            rp.setIndustryLabel(DEFAULT);
        }
        if (rp.getLocation() == null) {
            rp.setLocation("福州");
        }
        if (rp.getJobType() == null) {
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

        List<RecruitmentProfile> tRecruitmentProfiles = new ArrayList<>(400);
        for (int i = 0; i < recruitmentProfiles.size(); i++) {
            RecruitmentProfile rp = recruitmentProfiles.get(i);
            handleNull(rp);
            if (userProfile.getIndustryLabel() != null) {
                long industryLabel = userProfile.getIndustryLabel();
                if (industryLabel != DEFAULT) {
                    if (!rp.getIndustryLabel().equals(industryLabel)) {
                        recruitmentProfiles.remove(i--);
                    }
                }
            }
        }
        for (int i = 0; i < recruitmentProfiles.size(); i++) {
            RecruitmentProfile rp = recruitmentProfiles.get(i);
            handleNull(rp);
            //对学历进行筛选
            if (userProfile.getHighestEducation() != null && rp.getDegree() != null) {
                Long educationNum = userProfile.getHighestEducation();
                Long rNum = typeOfDegree.get(rp.getDegree());
                if (educationNum != 0 && rNum != null && rNum != 0 && (educationNum + 3) < rNum) {
                    tRecruitmentProfiles.add(rp);
                    recruitmentProfiles.remove(i--);
                }
            }
        }
        for (int i = 0; i < recruitmentProfiles.size(); i++) {
            RecruitmentProfile rp = recruitmentProfiles.get(i);
            //对岗位名称进行筛选
            String uJobName = userProfile.getStationLabel();
            String rJobName = rp.getJobName();
            if (uJobName != null && rJobName != null) {
                uJobName = uJobName.toLowerCase();
                rJobName = rJobName.toLowerCase();
                float similary = getSimilarityRatio(uJobName, rJobName);
                if (similary < 0.3) {
                    tRecruitmentProfiles.add(rp);
                    recruitmentProfiles.remove(i--);
                }
            }
        }
        if (recruitmentProfiles.size() != 0) {
            return recruitmentProfiles;
        }
        return tRecruitmentProfiles;
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
            //解析异常
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
        }
        if (salaryRange[0] >= SALARY_SEGMENTATION[3]) {
            return SALARY_QUALITY_VALUE[4];
        }
        if (salaryRange[0] >= SALARY_SEGMENTATION[2]) {
            return SALARY_QUALITY_VALUE[3];
        }
        if (salaryRange[0] >= SALARY_SEGMENTATION[1]) {
            return SALARY_QUALITY_VALUE[2];
        }
        if (salaryRange[0] >= SALARY_SEGMENTATION[0]) {
            return SALARY_QUALITY_VALUE[1];
        } else {
            return SALARY_QUALITY_VALUE[0];
        }
    }

    /**
     * 对工作地点进行量化
     *
     * @param userProfile
     * @param recruitmentProfile
     * @return
     */
    public double locateQuan(UserProfile userProfile, RecruitmentProfile recruitmentProfile) {
        String uCity = userProfile.getExpectedCity();
        String rCity = recruitmentProfile.getLocation();
        if (uCity == null || rCity == null) {
            return CITY_QUALITY_VALUE[0];
        }
        if (rCity.contains(uCity)) {
            return CITY_QUALITY_VALUE[2];
        }
        String uProvince = CityUtil.findObjectProvince(uCity);
        String rProvince = CityUtil.findObjectProvince(rCity);
        if (uProvince == null || rProvince == null) {
            return CITY_QUALITY_VALUE[0];
        }
        if (uProvince.equals(rProvince)) {
            return CITY_QUALITY_VALUE[2];
        }
        return CITY_QUALITY_VALUE[0];
    }

    /**
     * 对工作时间进行量化
     *
     * @param userProfile
     * @param recruitmentProfile
     * @return
     */
    public double workTimeQuan(UserProfile userProfile, RecruitmentProfile recruitmentProfile) {
        if (userProfile == null) {
            long rWorkTime = recruitmentProfile.getWorkTime() == null ? 0 : recruitmentProfile.getWorkTime();
            return WORK_TIME_QUALITY_VALUE[Integer.valueOf(rWorkTime + "")];
        } else {
            long uWorkTime = userProfile.getWorkTime() == null ? 0 : userProfile.getWorkTime();
            return WORK_TIME_QUALITY_VALUE[Integer.valueOf(uWorkTime + "")];
        }
    }

    /**
     * 对岗位进行量化
     *
     * @param userProfile
     * @param recruitmentProfile
     * @return
     */
    public double jobNameQuan(UserProfile userProfile, RecruitmentProfile recruitmentProfile) {
        String uJobName = userProfile.getStationLabel();
        String rJobName = recruitmentProfile.getJobName();
        if (uJobName == null || rJobName == null) {
            return JOB_NAME_QUALITY_VALUE[0];
        }
        float similary = getSimilarityRatio(uJobName, rJobName);

        if (similary <= 0.1) {
            return JOB_NAME_QUALITY_VALUE[0];
        }
        if (similary > 0.1 && similary <= 0.2) {
            return JOB_NAME_QUALITY_VALUE[1];
        }
        if (similary > 0.2 && similary <= 0.3) {
            return JOB_NAME_QUALITY_VALUE[2];
        }
        if (similary > 0.3 && similary <= 0.4) {
            return JOB_NAME_QUALITY_VALUE[3];
        }
        if (similary > 0.4 && similary <= 0.5) {
            return JOB_NAME_QUALITY_VALUE[4];
        }
        if (similary > 0.5 && similary <= 0.6) {
            return JOB_NAME_QUALITY_VALUE[5];
        }
        if (similary > 0.6 && similary <= 0.7) {
            return JOB_NAME_QUALITY_VALUE[6];
        }
        if (similary > 0.7 && similary <= 0.8) {
            return JOB_NAME_QUALITY_VALUE[7];
        }
        if (similary > 0.8 && similary <= 0.9) {
            return JOB_NAME_QUALITY_VALUE[8];
        }
        if (similary > 0.9) {
            return JOB_NAME_QUALITY_VALUE[9];
        }
        return JOB_NAME_QUALITY_VALUE[9];
    }

    /**
     * 对招聘信息关键字段进行量化加权
     * 目前只用学历、薪水和工作时间进行量化加权
     *
     * @param userProfile
     * @param preScreeningResults
     * @return
     */

    public Map<Long, double[]> quantization(UserProfile userProfile, List<RecruitmentProfile> preScreeningResults) {

        Map<Long, double[]> weightResults = new HashMap<>(1024);
        for (RecruitmentProfile recruitmentProfile : preScreeningResults) {
            double[] quanValue = new double[4];
            int[] sarlaryRange = regExSalary(recruitmentProfile.getSalary());
            //地点量化加权
            quanValue[0] = locateQuan(userProfile, recruitmentProfile) * USER_WEIGHT[0];
            //工作时间量化
            quanValue[1] = workTimeQuan(null, recruitmentProfile) * USER_WEIGHT[1];
            //薪水量化加权
            quanValue[2] = salaryQuan(sarlaryRange) * USER_WEIGHT[2];
            //岗位名称量化
            quanValue[3] = jobNameQuan(userProfile, recruitmentProfile) * USER_WEIGHT[3];
            weightResults.put(recruitmentProfile.getRecruitmentId(), quanValue);
        }
        return weightResults;
    }

    /**
     * 对招聘信息进行相似度计算
     *
     * @param upf
     * @param weightedResults
     * @return
     */
    public List<RecommendResult> computationalSimilarity(UserProfile upf, Map<Long, double[]> weightedResults) {

        List<RecommendResult> recommendResults = new ArrayList<RecommendResult>();
        double result = 0;
        double r1 = 0;
        double r2 = 0;
        double r3 = 0;
        RecruitmentProfile recruitmentProfile = new RecruitmentProfile();
        recruitmentProfile.setJobName(upf.getStationLabel());
        recruitmentProfile.setLocation(upf.getExpectedCity());
        //用户向量q0
        double[] quanValue = new double[4];
        int[] sarlaryRange = regExSalary(upf.getExpectSalary());
        //地点量化加权
        quanValue[0] = locateQuan(upf, recruitmentProfile) * USER_WEIGHT[0];
        //工作时间量化
        quanValue[1] = workTimeQuan(upf, null) * USER_WEIGHT[1];
        //薪水量化加权
        quanValue[2] = salaryQuan(sarlaryRange) * USER_WEIGHT[2];
        //岗位名称量化
        quanValue[3] = jobNameQuan(upf, recruitmentProfile) * USER_WEIGHT[3];

        for (Map.Entry<Long, double[]> entry : weightedResults.entrySet()) {
            Long str = entry.getKey();
            double[] dou = entry.getValue();
            for (int i = 0; i < dou.length; i++) {
                r1 += dou[i] * quanValue[i];
                r2 += dou[i] * dou[i];
                r3 += quanValue[i] * quanValue[i];
            }
            result = Double.parseDouble(String.format("%.6f", r1 / Math.sqrt(r2 * r3)));
            RecommendResult recommendResult = new RecommendResult();
            recommendResult.setUserId(upf.getUserId());
            recommendResult.setRecruitmentId(str);
            recommendResult.setSimilarityResult(result);
            recommendResults.add(recommendResult);
        }
        return recommendResults;
    }

    /**
     * 比较两个字符串的相识度
     * 核心算法：用一个二维数组记录每个字符串是否相同，如果相同记为0，不相同记为1，每行每列相同个数累加
     * 则数组最后一个数为不相同的总数，从而判断这两个字符的相识度
     *
     * @param str
     * @param target
     * @return
     */
    private static int compare(String str, String target) {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }


    /**
     * 获取最小的值
     */
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {
        if (str == null || target == null) {
            return 0;
        }
        int max = Math.max(str.length(), target.length());
        return 1 - (float) compare(str, target) / max;
    }

    /**
     * 获取相似度最高的Top-N
     *
     * @param recommendResults
     * @param k
     * @return
     */
    public List<RecommendResult> getTopN(List<RecommendResult> recommendResults, int k) {
        PriorityQueue<RecommendResult> queue = new PriorityQueue<>(k);

        for (int i = 0; i < recommendResults.size(); i++) {
            if (queue.size() < k) {
                queue.offer(recommendResults.get(i));
            } else {
                RecommendResult value = queue.peek();
                // 如果发现数据比堆顶元素大，则加入到小顶堆中
                if (recommendResults.get(i).getSimilarityResult() > value.getSimilarityResult()) {
                    queue.poll();
                    queue.offer(recommendResults.get(i));
                }
            }
        }


        List<RecommendResult> result = new ArrayList<>();
        int index = 0;
        // 遍历完成后，小顶堆的数据就为需要求得的topk的数据
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

}
