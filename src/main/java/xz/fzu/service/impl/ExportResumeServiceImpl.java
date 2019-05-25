package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.createword.CreateWord;
import xz.fzu.exception.ExportException;
import xz.fzu.model.Resume;
import xz.fzu.service.IExportResumeService;
import xz.fzu.util.Constants;
import xz.fzu.util.FileUtil;

/**
 * @author Murphy
 * @date 2019/5/23 20:33
 */
@Service
public class ExportResumeServiceImpl implements IExportResumeService {

    @Override
    public String exportResume(Resume resume, String filePath, String userId) throws ExportException {

        String absoluteFilePath = FileUtil.getAbsoluteFilePath(userId, null, FileUtil.getFileName("docx"), true);
        int lastIndex = filePath.lastIndexOf('/') + 1;
        CreateWord.createWord(resume, absoluteFilePath, filePath.substring(lastIndex), Constants.FILE_HOME + "/" + filePath.substring(0, lastIndex));

        return FileUtil.getRelativeFilePath(absoluteFilePath);
    }
}
