package xz.fzu.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

/**
 * @author Murphy
 * @date 2019/5/20 21:14
 */
public interface IFileService {


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
     * 读取文件
     * @param userId 用户id
     * @param filePath 上传文件路径
     * @return void
     * @author Murphy
     * @date 2019/5/20 21:17
     * @throws IOException 抛出IO异常
     */
    byte[] readFile(String userId, String filePath) throws IOException;
}
