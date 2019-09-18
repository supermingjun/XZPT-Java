package xz.fzu.createword;

import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * 将xml转换为docx
 * @author LITM
 */
public class Docx4jUtil {
	public static WordprocessingMLPackage genaratePdfByFtlAndDocx4J(String ftlName, Object data,String ftlPath) throws Exception {
        String generate = FreemarkerUtil.generate(ftlName, data,ftlPath);
        ByteArrayInputStream in = new ByteArrayInputStream(generate.getBytes());
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(in);
        return wordMLPackage;
    }

    /**
     * 生成word文档
     * @param ftlName
     * @param data
     * @param os
     * @param ftlPath
     * @throws Exception
     */
    public static void process(String ftlName, Object data, OutputStream os,String ftlPath) throws Exception {
    	
        String generate = FreemarkerUtil.generate(ftlName, data,ftlPath);
        ByteArrayInputStream in = new ByteArrayInputStream(generate.getBytes());
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(in);
        Mapper fontMapper = new IdentityPlusMapper();
        wordMLPackage.setFontMapper(fontMapper);
        fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
        fontMapper.put("宋体",PhysicalFonts.get("SimSun"));
        fontMapper.put("微软雅黑",PhysicalFonts.get("Microsoft Yahei"));
        fontMapper.put("黑体",PhysicalFonts.get("SimHei"));
        fontMapper.put("楷体",PhysicalFonts.get("KaiTi"));
        fontMapper.put("新宋体",PhysicalFonts.get("NSimSun"));
        fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
        fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
        fontMapper.put("宋体扩展",PhysicalFonts.get("simsun-extB"));
        fontMapper.put("仿宋",PhysicalFonts.get("FangSong"));
        fontMapper.put("仿宋_GB2312",PhysicalFonts.get("FangSong_GB2312"));
        fontMapper.put("幼圆",PhysicalFonts.get("YouYuan"));
        fontMapper.put("华文宋体",PhysicalFonts.get("STSong"));
        fontMapper.put("华文中宋",PhysicalFonts.get("STZhongsong"));
	    wordMLPackage.save(os, Docx4J.FLAG_SAVE_ZIP_FILE);
    }
}
