package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.createexcel.CreateExcel;
import xz.fzu.createword.CreateWord;
import xz.fzu.exception.ExportException;
import xz.fzu.model.Resume;
import xz.fzu.service.IExportService;
import xz.fzu.util.Constants;
import xz.fzu.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 20:33
 */
@Service
public class ExportServiceImpl implements IExportService {

    @Override
    public String exportResume(Resume resume, String filePath, String userId) throws ExportException {

        String absoluteFilePath = FileUtil.getAbsoluteFilePath(userId, null, FileUtil.getFileName("docx"), true);

        // 保存文件路径的文件夹下标
        int lastIndex = absoluteFilePath.lastIndexOf('/') + 1;
        File file = new File(absoluteFilePath.substring(0, lastIndex));
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("文件路径不存在，生成" + absoluteFilePath.substring(0, lastIndex));
        }

        // 模板文件路径的文件夹下标
        lastIndex = filePath.lastIndexOf('/') + 1;
        CreateWord.createWord(resume, absoluteFilePath, filePath.substring(lastIndex), Constants.FILE_HOME + "/" + filePath.substring(0, lastIndex));

        return FileUtil.getRelativeFilePath(absoluteFilePath);
    }

    @Override
    public String exportExcel(List<Resume> res, String userId) {
        String absoluteFilePath = FileUtil.getAbsoluteFilePath(userId, null, FileUtil.getFileName("xls"), true);
        CreateExcel.createExcel(res, absoluteFilePath);

        return FileUtil.getRelativeFilePath(absoluteFilePath);
    }
}