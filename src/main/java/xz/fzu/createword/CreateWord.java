package xz.fzu.createword;

import xz.fzu.exception.ExportException;
import xz.fzu.model.Resume;
import xz.fzu.util.Constants;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
/**
 * Word生成简历接口
 *
 * @author LITM
 * @since 2019年5月22日
 */
public class CreateWord {
    public static final Map<Long,String> DEGREE = new HashMap<Long,String>(){
        {
            put((long)1,"本科");
            put((long)2,"大专");
            put((long)3,"本科");
            put((long)4,"硕士");
            put((long)5,"博士");
        }
    };

    /**
     * 将数据中的特殊字符替换为实体的形式
     * @param map
     */
    public static void xmlSpecialCharacterReplace(Map<String,String> map){

        for(String key : map.keySet()){
            String value = map.get(key);
            if(value!=null){
                if(key.equals("head_sculpture")){
                    continue;
                }
                value = value.replace("<","&lt;");
                value = value.replace(">","&gt;");
                value = value.replace("'","&apos");
                value = value.replace("\"","&quot;");
                value = value.replace("&","&amp;");
                map.put(key,value);
            }
        }
    }

    /**
     * 调用该方法生成Word版简历
     * @param resume
     * @param outputFilePath
     * @param ftlName
     * @param ftlPath
     * @throws ExportException
     */
    public static void createWord(Resume resume, String outputFilePath, String ftlName, String ftlPath) throws ExportException {

        Map<String, String> map = new HashMap<>(12);
        map.put("name", resume.getUserName());
        map.put("work_experience", resume.getPracticalExperience());
        map.put("certificate", resume.getCertificate());
        map.put("school", resume.getSchool());
        map.put("degree", DEGREE.get(resume.getHighestEducation()));
        map.put("major", resume.getSpeciality());
        map.put("phone", resume.getTelephone());
        map.put("address", resume.getPresentCity());
        map.put("email", resume.getEmail());
        map.put("job_intention",resume.getExpectWork());
        map.put("project_experience",resume.getProjectExperience());
        String imgFilePath;
        if (resume.getHeadUrl() == null || Constants.DEFAULT_PNG.equals(resume.getHeadUrl())) {
            imgFilePath = Constants.FILE_HOME + "/" + Constants.DEFAULT_PNG;
        } else {
            imgFilePath = Constants.FILE_HOME + "/" + resume.getUserId() + "/" + resume.getHeadUrl();
        }
        String imageBase64 = LoadImage.loadImage(imgFilePath);
        map.put("head_sculpture", imageBase64);
        xmlSpecialCharacterReplace(map);
        FileOutputStream os;
        try {
            os = new FileOutputStream(outputFilePath);
            Docx4jUtil.process(ftlName, map, os, ftlPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExportException();
        }
    }
}
