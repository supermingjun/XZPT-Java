package xz.fzu.createword;

import java.io.FileOutputStream;
import java.util.Map;

import xz.fzu.createword.Docx4JUtil;
/**
 * Word生成简历接口
 * @author LITM
 *@since 2019年5月22日
 */
public class CreateWord {
	public static void createWord(Map<String,Object> data,String outputFilePath,String ftlName,String ftlPath) throws Exception {
        FileOutputStream os = new FileOutputStream(outputFilePath);
        Docx4JUtil.process(ftlName, data, os,ftlPath);
	}
}
