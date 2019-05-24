package xz.fzu.createword;

import xz.fzu.model.Resume;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
/**
 * Word生成简历接口
 * @author LITM
 *@since 2019年5月22日
 */
public class CreateWord {
	public static void createWord(Resume resume,String outputFilePath,String ftlName,String ftlPath) throws Exception {
        Map<String, Object> map = new HashMap<>(100);
        map.put("name",resume.getUserName());
//        map.put("name_pinyin","");
//        map.put("work1_location", "");
        map.put("work1_describe", resume.getPracticalExperience());
//        map.put("work1_time", "2017.06-09");
//        map.put("work1_position","安全分析师");
//        map.put("work2_location", "上海");
//        map.put("work2_describe", "系统漏洞分析");
//        map.put("work2_time", "2018.07-11");
//        map.put("work2_position","漏洞检测师");
//        map.put("education_locationAndDegree","福州大学   本科");
//        map.put("education_describe","主修课程---===-----====----===");
//        map.put("education_time","2016 - 2020");
//        map.put("education_major","软件工程");
//        map.put("award1","国家励志奖学金");
//        map.put("award2","校一等奖");
//        map.put("award3","软件设计大赛省赛三等奖");
//        map.put("award4","先进个人");
        map.put("award1",resume.getCertificate());
//        map.put("award_time","2016 - 2020");
        map.put("self_assessment","乐观、自信、好学、虚心、助人");
        map.put("birthday","");
        map.put("school",resume.getSchool());
        map.put("degree",resume.getHighestEducation());
        map.put("political_outlook","共青团");
        map.put("major",resume.getSpeciality());
        map.put("phone",resume.getTelephone());
        map.put("address",resume.getExpectedCity());
        map.put("email", resume.getEmail());
        FileOutputStream os = new FileOutputStream(outputFilePath);
        Docx4JUtil.process(ftlName, map, os,ftlPath);
	}
}
