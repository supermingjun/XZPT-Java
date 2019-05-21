package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.CsvErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Recruitment;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.util.ImportDataUtil;
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

    @RequestMapping(value = "/company/importdata", method = RequestMethod.POST)
    public ResponseVO<String> importDataFromFile(@RequestParam("file") String fileName, @RequestParam String token) throws UserNotFoundException, TokenExpiredException, IOException, CsvErrorException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String companyId = iCompanyService.getInfoByToken(token).getCompanyId();
        List<Recruitment> recruitmentList = ImportDataUtil.readData(fileName, companyId);
        for (Recruitment recruitment : recruitmentList) {
            iRecruitmentService.insertRecruitment(recruitment);
        }

        return responseVO;
    }
}
