package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.exception.UserNotFoundException;
import xz.fzu.model.Company;
import xz.fzu.model.User;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ChatDTO;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/5/25 10:43
 */
@RequestMapping(value = "/chat", method = RequestMethod.POST)
@RestController
public class ChatController {

    @Resource
    IUserService iUserService;
    @Resource
    ICompanyService iCompanyService;

    /**
     * 企业端获取用户的信息
     *
     * @param token       token
     * @param userId      用户id
     * @param requestType 请求的用户类型 0为企业，1为用户
     * @param wantType    请求的用户类型 0为企业，1为用户
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/25 10:48
     */
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
    public ResponseVO getUserInfo(@RequestParam String token, @RequestParam String userId, @RequestParam int requestType, @RequestParam int wantType) throws TokenExpiredException, UserNotFoundException {

        ResponseVO<ChatDTO> responseVO = new ResponseVO<>();

        ChatDTO chatDTO = new ChatDTO();
        switch (requestType) {
            case ChatDTO.COMPANY:
                iCompanyService.verifyToken(token);
                chatDTO.setUserId(iCompanyService.getInfoByToken(token).getCompanyId());
                break;
            case ChatDTO.USER:
                iUserService.verifyToken(token);
                chatDTO.setUserId(iUserService.selectUserByToken(token).getUserId());
                break;
            default:
                break;
        }
        switch (wantType) {
            case ChatDTO.COMPANY:
                Company company = iCompanyService.getInfoByToken(token);
                chatDTO.setUserName(company.getCompanyName());
                chatDTO.setHeadUrl(company.getHeadUrl());
                break;
            case ChatDTO.USER:
                User user = iUserService.selectByUserId(userId);
                chatDTO.setHeadUrl(user.getHeadUrl());
                chatDTO.setUserName(user.getUserName());
                break;
            default:
                break;
        }
        responseVO.setResultObject(chatDTO);

        return responseVO;
    }
}
