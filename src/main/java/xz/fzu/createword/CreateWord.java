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
    public static void createWord(Resume resume, String outputFilePath, String ftlName, String ftlPath) throws ExportException {
        Map<String, Object> map = new HashMap<>(10);
        map.put("name", resume.getUserName());
        map.put("work_experience", resume.getPracticalExperience());
        map.put("certificate", resume.getCertificate());
        map.put("school", resume.getSchool());
        map.put("degree", resume.getHighestEducation());
        map.put("major", resume.getSpeciality());
        map.put("phone", resume.getTelephone());
        map.put("address", resume.getPresentCity());
        map.put("email", resume.getEmail());
        String imgFilePath;
        if (resume.getHeadUrl() != null) {
            imgFilePath = Constants.FILE_HOME + "/" + resume.getUserId() + "/" + resume.getHeadUrl();
        } else {
            imgFilePath = Constants.FILE_HOME + "/public/default_head_url.png";
        }
        String imageBase64 = LoadImage.loadImage(imgFilePath);
        map.put("head_sculpture", imageBase64);
        FileOutputStream os;
        try {
            os = new FileOutputStream(outputFilePath);
            Docx4JUtil.process(ftlName, map, os, ftlPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExportException();
        }
    }
}
