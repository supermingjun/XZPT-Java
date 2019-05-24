package xz.fzu.createword;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		
		 
		 Map<String, Object> map = new HashMap<>(100);
		 map.put("name","李彦文");
		 map.put("name_pinyin","LIYANWEN");
	     map.put("work1_location", "北京");
	     map.put("work1_describe", "检测网络流量");
	     map.put("work1_time", "2017.06-09");
	     map.put("work1_position","安全分析师");
	     map.put("work2_location", "上海");
	     map.put("work2_describe", "系统漏洞分析");
	     map.put("work2_time", "2018.07-11");
	     map.put("work2_position","漏洞检测师");
	     map.put("education_locationAndDegree","福州大学   本科");
	     map.put("education_describe","主修课程---===-----====----===");
	     map.put("education_time","2016 - 2020");
	     map.put("education_major","软件工程");
	     map.put("award1","国家励志奖学金");
	     map.put("award2","校一等奖");
	     map.put("award3","软件设计大赛省赛三等奖");
	     map.put("award4","先进个人");
	     map.put("award_time","2016 - 2020");
	     map.put("self_assessment","乐观、自信、好学、虚心、助人");
	     map.put("birthday","1998/09/11");
	     map.put("school","福州大学");
	     map.put("degree","本科");
	     map.put("political_outlook","共青团");
	     map.put("major","软件工程");
	     map.put("phone","1121112112");
	     map.put("address","南京市民巷186号");
	     map.put("email", "litm.kf@126.com");
//	     String imageBase64 = LoadImage.loadImage("E:\\ps材料\\8.14\\沙沙-PS打造帅气纹身效果\\沙沙-PS打造帅气纹身效果\\ps打造帅气纹身效果素材\\35373.jpg");
	   //map.put("head_sculpture",imageBase64);
	     CreateWord.createWord(map,"E:\\java代码\\生成pdf\\src\\ceshi\\jianli2.docx","jianli.ftl","E:\\java代码\\简历生成Word\\src");
	}
}
