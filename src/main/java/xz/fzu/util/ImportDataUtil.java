package xz.fzu.util;

import xz.fzu.exception.CsvErrorException;
import xz.fzu.model.Recruitment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Murphy
 * @date 2019/5/20 23:11
 */
public class ImportDataUtil {

    private static final String JOB_NAME = "jobName(岗位名称)";
    private static final String DESCRIPTION = "description(岗位描述)";
    private static final String CONTACT = "contact(联系方式)";
    private static final String LOCATION = "location(工作地点)";
    private static final String DELIVERY_REQUEST = "deliveryRequest(投递要求)";
    private static final String SALARY = "salary(薪资)";
    private static final String DEGREE = "degree(学历要求)";
    private static final String WORK_TIME = "workTime(工作制度)";
    private static final String JOB_TYPE = "jobType(岗位类型)";
    private static final String HEAD_COUNT = "headCount(招聘人数)";
    private static final int SIZE = 10;

    /**
     * 1为955，2为965，3为956，4为996，0为默认
     */
    private static final int WT955 = 1;
    private static final String STRING_955 = "955";
    private static final int WT965 = 2;
    private static final String STRING_965 = "965";
    private static final int WT956 = 3;
    private static final String STRING_956 = "956";
    private static final int WT996 = 4;
    private static final String STRING_996 = "996";
    private static final int WT0 = 0;

    /**
     * 读取文件并返回数据
     *
     * @param fileName  文件名
     * @param companyId 公司id
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/5/20 23:43
     */
    public static List<Recruitment> readData(String fileName, String companyId) throws IOException, CsvErrorException {

        List<Recruitment> res = new ArrayList<>();

        Map<Integer, String> map = new HashMap<>(64);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getPath(companyId, Constants.UPLOAD, fileName)))));
        String line = bufferedReader.readLine();
        String[] labels = line.split(",");
        if (labels.length != SIZE) {
            throw new CsvErrorException("csv标题长度不是12！");
        } else {
            int i = 0;
            for (String label : labels) {
                map.put(i++, label);
            }
        }
        while ((line = bufferedReader.readLine()) != null) {
            int i = 0;
            labels = line.split(",");
            Recruitment recruitment = new Recruitment();
            for (String label : labels) {
                String property = map.get(i++);
                setProperty(property, label, recruitment);
            }
            recruitment.setCompanyId(companyId);
            res.add(recruitment);
        }

        return res;
    }

    /**
     * 根据所给条件给实例的字段设置属性
     *
     * @param property    字段名
     * @param value       字段值
     * @param recruitment 实例
     * @return void
     * @author Murphy
     * @date 2019/5/20 23:48
     */
    private static void setProperty(String property, String value, Recruitment recruitment) throws CsvErrorException {
        switch (property) {
            case JOB_NAME:
                recruitment.setJobName(value);
                break;
            case DESCRIPTION:
                recruitment.setDescription(value);
                break;
            case CONTACT:
                recruitment.setContact(value);
                break;
            case LOCATION:
                recruitment.setLocation(value);
                break;
            case DELIVERY_REQUEST:
                recruitment.setDeliveryRequest(value);
                break;
            case SALARY:
                recruitment.setSalary(value);
                break;
            case DEGREE:
                recruitment.setDegree(value);
                break;
            case WORK_TIME:
                switch (value) {
                    case STRING_996:
                        recruitment.setWorkTime(WT996);
                        break;
                    case STRING_955:
                        recruitment.setWorkTime(WT955);
                        break;
                    case STRING_956:
                        recruitment.setWorkTime(WT956);
                        break;
                    case STRING_965:
                        recruitment.setWorkTime(WT965);
                        break;
                    default:
                        recruitment.setWorkTime(WT0);
                        break;
                }
                break;
            case JOB_TYPE:
                recruitment.setJobType(Integer.valueOf(value));
                break;
            case HEAD_COUNT:
                recruitment.setHeadCount(Integer.valueOf(value));
                break;
            default:
                throw new CsvErrorException();
        }
    }

    /**
     * 根据名字和操作类型获得文件路径
     *
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:47
     */
    public static String getPath(String userId, String opType, String fileName) {

        return Constants.FILE_HOME + userId + opType + fileName;
    }
}
