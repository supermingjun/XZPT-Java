package xz.fzu.createword;

import xz.fzu.model.Resume;

public class Main {
	public static void main(String[] args) throws Exception {
		Resume resume = new Resume();
		resume.setUserName("李烈");
		resume.setTelephone("12222222");
		resume.setSpeciality("软工");
		resume.setSchool("福州大学");
		resume.setPracticalExperience("dsfsafsadfdsaf");
		resume.setHighestEducation((long)1);
		resume.setHeadUrl("E:\\java代码\\简历生成Word\\src\\image.jpg");
		resume.setEmail("11111111");
		resume.setCertificate("jdslfsa");
		resume.setPresentCity("北京");
		CreateWord.createWord(resume,"E:\\java代码\\生成pdf\\src\\ceshi\\jianli2.docx","jianli.ftl","E:\\gitworkspace\\XZPT-Java\\src\\main\\resources\\resume_template");
	}
}
