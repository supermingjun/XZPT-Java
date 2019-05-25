package xz.fzu.service;

import xz.fzu.exception.ExportException;
import xz.fzu.model.Resume;

/**
 * @author Murphy
 * @date 2019/5/23 16:44
 */
public interface IExportResumeService {
    /**
     * 导出简历并返回导出word的文件路径
     *
     * @param resume       简历实例
     * @param filePath 模板路径
     * @param userId 用户id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/23 20:29
     */
    String exportResume(Resume resume, String filePath, String userId) throws ExportException;
}
