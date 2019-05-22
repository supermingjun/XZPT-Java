package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.service.IFileService;
import xz.fzu.util.Constants;
import xz.fzu.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Murphy
 * @date 2019/5/20 21:18
 */
@Service
public class FileServiceImpl implements IFileService {


    @Override
    public String saveFile(String userId, String format, CommonsMultipartFile commonsMultipartFile, boolean isPrivate) throws IOException {

        String originFilName = Objects.requireNonNull(commonsMultipartFile.getOriginalFilename());
        String fileName = FileUtil.getFileName(originFilName, format);
        String absoluteFilePath = FileUtil.getFilePath(userId, Constants.UPLOAD, fileName, isPrivate);
        File file = new File(absoluteFilePath);
        if (!file.exists()) {
            file.mkdirs();// 目录不存在的情况下，创建目录。
        }
        commonsMultipartFile.transferTo(file);

        return fileName;
    }

    @Override
    public byte[] readFile(String userId, String fileName, boolean isPrivate) throws IOException {

        String filePath = FileUtil.getFilePath(userId, Constants.DOWNLOAD, fileName, isPrivate);
        //将该文件加入到输入流之中
        InputStream in = new FileInputStream(new File(filePath));
        // 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        byte[] body = new byte[in.available()];
        //读入到输入流里面
        in.read(body);

        return body;
    }

}
