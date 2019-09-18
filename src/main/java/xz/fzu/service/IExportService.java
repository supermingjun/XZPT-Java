package xz.fzu.service;

import xz.fzu.exception.ExportException;
import xz.fzu.model.Resume;

import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 16:44
 */
public interface IExportService {
    /**
     * 导出简历并返回导出word的文件路径
     *
     * @param resume       简历实例
     * @param filePath 模板路径
     * @param userId 用户id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/23 20:29
     * @throws ExportException 导出简历错误异常
     */
    String exportResume(Resume resume, String filePath, String userId) throws ExportException;

    /**
     * 导出投递的人的信息
     *
     * @param res    简历集合
     * @param userId 用户id
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/27 20:32
     */
    String exportExcel(List<Resume> res, String userId);
}
