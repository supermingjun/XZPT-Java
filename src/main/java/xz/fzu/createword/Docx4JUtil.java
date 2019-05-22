package xz.fzu.createword;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;

import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class Docx4JUtil {
	public static WordprocessingMLPackage genaratePdfByFtlAndDocx4J(String ftlName, Object obj) throws Exception {
        String generate = FreemarkerUtil.generate(ftlName, obj);
        ByteArrayInputStream in = new ByteArrayInputStream(generate.getBytes());
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(in);
        return wordMLPackage;
    }

    /**
     * 生成pdf文档
     * @param ftlName 模版文件
     * @param obj 数据
     * @param os 输出流
     * @throws Exception 
     */
    public static void process(String ftlName, Object obj, OutputStream os) throws Exception {
        // word doc os = ftl + obj
        String generate = FreemarkerUtil.generate(ftlName, obj);
        // word doc os -> str
        ByteArrayInputStream in = new ByteArrayInputStream(generate.getBytes());
        //File file = new File("t.xml");
        
        // str -> wordMLPackage object
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
        File file = new File("jianli.docx");
	    wordMLPackage.save(os, Docx4J.FLAG_SAVE_ZIP_FILE);
        // wordMLPackage -> pdf os
//        FOSettings foSettings = Docx4J.createFOSettings();
//        foSettings.setWmlPackage(wordMLPackage);
//        foSettings.setApacheFopMime("application/pdf");
//        Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
//        Transformer serializer = new org.docx4j.org.apache.xalan.transformer.TransformerIdentityImpl();
        
//        Docx4J.toPDF(wordMLPackage, os);
    }
}
