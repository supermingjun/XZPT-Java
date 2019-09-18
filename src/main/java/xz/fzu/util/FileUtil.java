package xz.fzu.util;

import xz.fzu.exception.CsvErrorException;
import xz.fzu.model.Recruitment;

import java.io.*;
import java.util.*;


/**
 * @author Murphy
 * @date 2019/5/20 23:11
 */
public class FileUtil {

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
    public static List<Recruitment> readCsvData(String fileName, String companyId, boolean isPrivate) throws IOException, CsvErrorException {

        List<Recruitment> res = new ArrayList<>();

        Map<Integer, String> map = new HashMap<>(64);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Constants.FILE_HOME + "/" + fileName))));
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
     * 根据名字和操作类型获得绝对文件路径
     *
     * @param userId    用户id
     * @param opType    操作类型
     * @param fileName  文件名
     * @param isPrivate 是否为私密文件
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/22 20:06
     */
    public static String getAbsoluteFilePath(String userId, String opType, String fileName, boolean isPrivate) {

        if (isPrivate) {
//            String path = Constants.FILE_HOME + opType + "/" + userId + "/" + fileName;
            String path = Constants.FILE_HOME + "/" + userId + "/" + fileName;
            System.out.println("文件保存到：" + path);
            return path;
        } else {
//            String path = Constants.FILE_HOME + opType + "/" + "public" + "/" + fileName;
            String path = Constants.FILE_HOME + "/public/" + fileName;
            System.out.println("文件保存到：" + path);
            return path;
        }
    }

    /**
     * 根据绝对路径获得相对路径
     *
     * @param absoluteFilePath 绝对文件路径
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/23 20:31
     */
    public static String getRelativeFilePath(String absoluteFilePath) {
        return absoluteFilePath.substring((Constants.FILE_HOME + "/").length());
    }

    /**
     * 随机生成文件名
     *
     * @param fileName 文件名
     * @param defaultFormat 默认文件格式
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:44
     */
    public static String getFileName(String fileName, String defaultFormat) {
        String[] names = fileName.split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = defaultFormat;
        }
        return UUID.randomUUID() + "." + format;
    }

    /**
     * 由默认格式获得一个随机文件名
     *
     * @param defaultFormat 默认格式
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/28 20:19
     */
    public static String getFileName(String defaultFormat) {
        return UUID.randomUUID() + "." + defaultFormat;
    }

    /**
     * 由文件路径生成不存在的文件夹
     *
     * @param absoluteFilePath 文件的绝对路径
     * @return void
     * @author Murphy
     * @date 2019/5/28 20:20
     */
    public static void mkdirs(String absoluteFilePath) {

        int lastIndex = absoluteFilePath.lastIndexOf('/') + 1;
        File file = new File(absoluteFilePath.substring(0, lastIndex));
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("文件路径不存在，生成" + absoluteFilePath.substring(0, lastIndex));
        }
    }
}
