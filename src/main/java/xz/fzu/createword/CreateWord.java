package xz.fzu.createword;

import java.io.FileOutputStream;
import java.util.Map;

import xz.fzu.createword.Docx4JUtil;

public class CreateWord {
	public static void createWord(Map<String,Object> resume) throws Exception {
		String ftlName = "jianli.ftl";
        String outputFilePath = "E:\\java代码\\生成pdf\\src\\ceshi\\jianli2.docx";
        FileOutputStream os = new FileOutputStream(outputFilePath);
        Docx4JUtil.process(ftlName, resume, os);
	}
}
