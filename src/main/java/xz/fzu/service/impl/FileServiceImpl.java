package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.service.IFileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/5/20 21:18
 */
@Service
public class FileServiceImpl implements IFileService {


    @Override
    public String saveFile(String userId, String format, CommonsMultipartFile commonsMultipartFile) throws IOException {

        String fileName = getFileName(commonsMultipartFile, format);
        String path = getPath(userId, UPLOAD, fileName);
        commonsMultipartFile.transferTo(new File(path));

        return fileName;
    }

    @Override
    public byte[] readFile(String userId, String fileName) throws IOException {

        String filePath = getPath(userId, DOWNLOAD, fileName);
        //将该文件加入到输入流之中
        InputStream in = new FileInputStream(new File(filePath));
        // 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        byte[] body = new byte[in.available()];
        //读入到输入流里面
        in.read(body);

        return body;
    }


    /**
     * 随机生成文件名
     *
     * @param file 文件
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:44
     */
    private String getFileName(CommonsMultipartFile file, String defaultFormat) {
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = defaultFormat;
        }
        return UUID.randomUUID() + "." + format;
    }

    /**
     * 根据token获得用户id
     *
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:47
     */
    private String getPath(String userId, String opType, String fileName) {

        return FILE_HOME + userId + opType + fileName;
    }
}
