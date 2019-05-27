package xz.fzu.createexcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import xz.fzu.model.Resume;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将投递的简历信息导出到excel
 * @author LITM
 *
 */
public class CreateExcel {
    /**
     * 定义Excel表头
     *
     */
    public static final String[] COLUMN_NAME = {"orderNumber","userName","sex","age","telephone"
            , "email","school","speciality","highestEducation","certificate"
            ,"projectExperience","practicalExperience"
    };
    /**
     * 定义学历
     */
    public static final Map<Long,String> DEGREE = new HashMap<Long,String>(){
        {
            put((long)1,"未知");
            put((long)2,"大专");
            put((long)3,"本科");
            put((long)4,"硕士");
            put((long)5,"博士");
        }
    };
    /**
     * 定义性别
     */
    public static final Map<Long,String> SEX = new HashMap<Long,String>(){
        {
            put((long)0,"男");
            put((long)1,"男");
            put((long)2,"女");
        }

    };
    /**
     * 生成Excel表格
     * @param resumes
     * @param outputPath
     */
    public static void createExcel(List<Resume>resumes, String outputPath){

        //创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表单
        HSSFSheet sheet = workbook.createSheet("对象报表");
        //创建HSSFRow对象 （行）
        HSSFRow row = sheet.createRow(0);
        List<HSSFCell> cells = new ArrayList<HSSFCell>();
        int orderNumber = 1;
        //生成表头
        for(int i=0;i<COLUMN_NAME.length;i++){
            //创建HSSFCell对象  （单元格）
            HSSFCell cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(COLUMN_NAME[i]);
        }
        for(int j=0;j<resumes.size();j++){
            HSSFRow row1 = sheet.createRow(j+1);
            for(int k=0;k<COLUMN_NAME.length;k++) {
                cells.add(row1.createCell(k));
            }
            cells.get(0).setCellValue(orderNumber);
            cells.get(1).setCellValue(resumes.get(j).getUserName());
            cells.get(2).setCellValue(SEX.get(resumes.get(j).getSex()));
            cells.get(3).setCellValue(resumes.get(j).getAge());
            cells.get(4).setCellValue(resumes.get(j).getTelephone());
            cells.get(5).setCellValue(resumes.get(j).getEmail());
            cells.get(6).setCellValue(resumes.get(j).getSchool());
            cells.get(7).setCellValue(resumes.get(j).getSpeciality());
            cells.get(8).setCellValue(DEGREE.get(resumes.get(j).getHighestEducation()));
            cells.get(9).setCellValue(resumes.get(j).getCertificate());
            cells.get(10).setCellValue(resumes.get(j).getProjectExperience());
            cells.get(11).setCellValue(resumes.get(j).getPracticalExperience());
        }
        FileOutputStream output;
        try {
            output = new FileOutputStream(outputPath);
            workbook.write(output);
            output.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    /**
//     * 测试函数
//     * @param args
//     */
//    public static void main(String[] args) {
//        Resume resume = new Resume();
//        resume.setResumeId((long)455);
//        resume.setUserId("");
//        resume.setAge((long)2);
//        resume.setSex((long)1);
//        resume.setProjectExperience("sdfs");
//        resume.setUserName("李烈");
//        resume.setTelephone("12222222");
//        resume.setSpeciality("软工");
//        resume.setSchool("福州大学");
//        resume.setPracticalExperience("dsfsafsadfdsaf");
//        resume.setHighestEducation((long)1);
//        resume.setHeadUrl("E:\\java代码\\简历生成Word\\src\\image.jpg");
//        resume.setEmail("11111111");
//        resume.setCertificate("jdslfsa");
//        resume.setPresentCity("北京");
//        List<Resume> resumes = new ArrayList<Resume>();
//        resumes.add(resume);
//        CreateExcel.createExcel(resumes,"E:\\java代码\\生成excel\\result.xls");
//    }
}
