package xz.fzu.controller;

import org.springframework.web.bind.annotation.*;
import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Company;
import xz.fzu.model.Recruitment;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.ILabelService;
import xz.fzu.service.IRecruitmentService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.PageData;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * 招聘信息相关的控制器
 *
 * @author Murphy
 * @date 2019/5/2 13:56
 */
@RestController
public class RecruitmentController {


    // Company

    @Resource
    ICompanyService iCompanyService;
    @Resource
    IRecruitmentService iRecruitmentService;
    @Resource
    IUserService iUserService;
    @Resource
    ILabelService iLabelService;

    /**
     * @param recruitment 招聘细腻实例
     * @param token       token
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:06
     * @description 发布招聘信息
     */
    @RequestMapping(value = "/company/releaserecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO releaseRecruitment(@RequestBody Recruitment recruitment, @RequestParam String token) throws TokenExpiredException, UserNotFoundException {

        ResponseVO responseVO = new ResponseVO();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInfoByToken(token);
        recruitment.setCompanyId(company.getCompanyId());
        recruitment.setValidate(0);
        iRecruitmentService.insertRecruitment(recruitment);

        return responseVO;
    }

    /**
     * @param token         token
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定的招聘信息
     */
    @RequestMapping(value = "/company/getrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO getRecruitment(@RequestParam String token, @RequestParam long recruitmentId) throws InstanceNotExistException, TokenExpiredException {

        iCompanyService.verifyToken(token);

        return getRecruitmentById(recruitmentId);
    }

    /**
     * @param token token
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定的所有招聘信息
     */
    @RequestMapping(value = "/company/getlistrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<PageData> getRecruitment(@RequestParam String token, @RequestBody PageData<Recruitment> pageData) throws InstanceNotExistException, TokenExpiredException, UserNotFoundException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInfoByToken(token);
        List<Recruitment> list = iRecruitmentService.getListRecruitmentByCompanyId(company.getCompanyId(), pageData);
        listSetCompanyName(list);
        pageData.setContentList(list);
        responseVO.setResultObject(pageData);

        return responseVO;
    }


    //User

    /**
     * @param token token
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 修改指定的招聘信息
     */
    @RequestMapping(value = "/company/updaterecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateRecruitment(@RequestParam String token, @RequestBody xz.fzu.model.Recruitment recruitment) throws TokenExpiredException, UserNotFoundException, EvilIntentions {

        ResponseVO responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInfoByToken(token);
        iRecruitmentService.updateRecruitment(recruitment, company.getCompanyId());

        return responseVO;
    }

    /**
     * @param token token
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 删除指定的招聘信息
     */
    @RequestMapping(value = "/company/deleterecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO deleteRecruitment(@RequestParam String token, @RequestParam long recruitmentId) throws TokenExpiredException, UserNotFoundException, EvilIntentions {

        ResponseVO responseVO = new ResponseVO<>();
        iCompanyService.verifyToken(token);
        Company company = iCompanyService.getInfoByToken(token);
        iRecruitmentService.deleteRecruitment(recruitmentId, company.getCompanyId());

        return responseVO;
    }

    /**
     * @param token         token
     * @param recruitmentId 招聘信息的id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定的招聘信息
     */
    @RequestMapping(value = "/user/getrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO getRecruitmentById(@RequestParam String token, @RequestParam long recruitmentId) throws InstanceNotExistException, TokenExpiredException {

        iUserService.verifyToken(token);

        return getRecruitmentById(recruitmentId);
    }

    /**
     * @param token     token
     * @param companyId 企业id
     * @param pageData  页码信息相关
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/4/27 11:15
     * @description 按id获得指定公司所有招聘信息
     */
    @RequestMapping(value = "/user/getlistrecruitmentbycompanyid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<PageData> getRecruitmentById(@RequestParam String token, @RequestParam String companyId, @RequestBody PageData<Recruitment> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        List<Recruitment> recruitmentList = iRecruitmentService.getListRecruitmentByCompanyId(companyId, pageData);
        listSetCompanyName(recruitmentList);
        pageData.setContentList(recruitmentList);
        responseVO.setResultObject(pageData);

        return responseVO;
    }


    //Same

    /**
     * @param token   token
     * @param keyWord 关键词
     * @return xz.fzu.vo.ResponseVO<java.util.List < xz.fzu.model.Recruitment>>
     * @author Murphy
     * @date 2019/4/29 21:34
     * @description 搜索招聘信息
     */
    @RequestMapping(value = "/user/searchrecruitment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<PageData> searchRecruitment(@RequestParam String token, @RequestParam String keyWord, @RequestBody PageData<Recruitment> pageData) throws InstanceNotExistException, TokenExpiredException {

        ResponseVO<PageData> responseVO = new ResponseVO<>();
        iUserService.verifyToken(token);
        List<Recruitment> recruitmentList = iRecruitmentService.getListRecruitmentByKeyWord(keyWord, pageData);
        listSetCompanyName(recruitmentList);
        pageData.setContentList(recruitmentList);
        responseVO.setResultObject(pageData);

        return responseVO;
    }

    /**
     * @param recruitmentId 招聘信息id
     * @return xz.fzu.vo.ResponseVO<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/5/2 14:01
     * @description 根据招聘信息id获得招聘信息
     */
    private ResponseVO<Recruitment> getRecruitmentById(long recruitmentId) throws InstanceNotExistException {

        ResponseVO<Recruitment> responseVO = new ResponseVO<>();
        Recruitment recruitment = iRecruitmentService.getRecruitmentById(recruitmentId);
        setCompanyName(recruitment);
        setLabel(recruitment);
        responseVO.setResultObject(recruitment);

        return responseVO;
    }

    /**
     * 为招聘信息设置标签
     *
     * @param recruitment 招聘信息
     * @return void
     * @author Murphy
     * @date 2019/5/4 19:23
     */
    private void setLabel(Recruitment recruitment) {
        String stationLabel = recruitment.getStationLabel();
        StringBuilder stationBuilder = new StringBuilder();
        try {
            String[] integers = stationLabel.split(",");
            for (String string : integers) {
                int integer = Integer.parseInt(string);
                stationBuilder.append(iLabelService.getStationLabel(integer));
                stationBuilder.append(",");

            }
            recruitment.setStation(stationBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            recruitment.setStation(stationBuilder.toString());
        }
        try {
            recruitment.setIndustry(iLabelService.getIndustryLabel((int) recruitment.getIndustryLabel()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * list设置招聘信息的公司名字
     *
     * @param list 招聘信息数组
     * @return java.util.List<xz.fzu.vo.Recruitment>
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void listSetCompanyName(List<Recruitment> list) {

        for (Recruitment recruitment : list) {
            setCompanyName(recruitment);
        }
    }

    /**
     * 设置招聘信息公司名字
     *
     * @param recruitment 招聘信息
     * @return xz.fzu.vo.Recruitment
     * @author Murphy
     * @date 2019/5/3 0:37
     */
    private void setCompanyName(Recruitment recruitment) {

        String companyName = "公司不存在";
        try {
            companyName = iCompanyService.getInfoByCompanyId(recruitment.getCompanyId()).getCompanyName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recruitment.setCompanyName(companyName);
    }
}
