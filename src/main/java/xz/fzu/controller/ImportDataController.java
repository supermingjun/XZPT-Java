package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.CsvErrorException;
import xz.fzu.exception.OverLimitException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Recruitment;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.util.FileUtil;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/20 23:03
 */
@RestController
public class ImportDataController {

    @Resource
    ICompanyService iCompanyService;
    @Resource
    IRecruitmentService iRecruitmentService;

    /**
     * 导入数据
     *
     * @param fileName  文件名
     * @param token     token
     * @param isPrivate 是否属于私密文件
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/22 20:09
     */
    @RequestMapping(value = "/company/importdata", method = RequestMethod.POST)
    public ResponseVO<String> importDataFromFile(@RequestParam("file") String fileName, @RequestParam String token, @RequestParam("private") int isPrivate) throws UserNotFoundException, TokenExpiredException, IOException, CsvErrorException, OverLimitException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String companyId = iCompanyService.getInfoByToken(token).getCompanyId();
        List<Recruitment> recruitmentList = FileUtil.readCsvData(fileName, companyId, isPrivate==1);
        for (Recruitment recruitment : recruitmentList) {
            iRecruitmentService.insertRecruitment(recruitment);
        }

        return responseVO;
    }
}
