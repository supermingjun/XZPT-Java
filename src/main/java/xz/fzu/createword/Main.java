package xz.fzu.createword;

import xz.fzu.model.Resume;

public class Main {
	public static void main(String[] args) throws Exception {
		Resume resume = new Resume();
		resume.setCertificate("sfdsd");
		resume.setEmail("sdfsdf");
		resume.setHeadUrl("E:\\java代码\\简历生成Word\\src\\image.jpg");
		resume.setHighestEducation((long)3);
		resume.setPracticalExperience("sdfsdf");
		resume.setSchool("fzu");
		resume.setSpeciality("software");
		resume.setTelephone("1111111");
		resume.setUserName("liyanwen");
		CreateWord.createWord(resume,"E:\\java代码\\生成pdf\\src\\ceshi\\jianli2.docx","jianli.ftl","E:\\gitworkspace\\XZPT-Java\\src\\main\\resources\\resume_template");
	}
}
