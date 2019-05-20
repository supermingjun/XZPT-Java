package xz.fzu.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

/**
 * @author Murphy
 * @date 2019/5/20 21:14
 */
public interface IFileService {

    /**
     * 问价家目录
     */
    String FILE_HOME = "/home/tomcat8/";
    /**
     * 文件上传目录
     */
    String UPLOAD = "/upload/";
    /**
     * 文件下载目录
     */
    String DOWNLOAD = "/download/";
    /**
     * csv格式
     */
    String CSV = "csv";
    /**
     * JPG格式
     */
    String JPG = "jpg";

    /**
     * 保存文件
     *
     * @param userId               用户id
     * @param format               格式
     * @param commonsMultipartFile 上传文件流
     * @return void
     * @throws IOException 抛出IO异常
     * @author Murphy
     * @date 2019/5/20 21:16
     */
    String saveFile(String userId, String format, CommonsMultipartFile commonsMultipartFile) throws IOException;

    /**
     * @param filePath 上传文件路径
     * @return void
     * @author Murphy
     * @date 2019/5/20 21:17
     */
    void readFile(String filePath);
}
