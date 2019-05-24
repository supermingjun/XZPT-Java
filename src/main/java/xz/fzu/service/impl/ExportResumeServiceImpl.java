package xz.fzu.service.impl;

import xz.fzu.model.Resume;
import xz.fzu.service.IExportResumeService;
import xz.fzu.util.FileUtil;

/**
 * @author Murphy
 * @date 2019/5/23 20:33
 */
public class ExportResumeServiceImpl implements IExportResumeService {

    @Override
    public String exportResume(Resume resume, String templatePath) {

        String filePath = "";

        // TODO 调用导出文件
        return FileUtil.getRelativeFilePath(filePath);
    }
}
