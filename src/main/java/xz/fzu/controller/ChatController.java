package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;
import xz.fzu.service.ICompanyService;
import xz.fzu.service.IUserService;
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
     * @param token  token
     * @param userId 用户id
     * @return xz.fzu.vo.ResponseVO
     * @author Murphy
     * @date 2019/5/25 10:48
     */
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
    public ResponseVO getUserInfo(@RequestParam String token, @RequestParam String userId) throws TokenExpiredException {

        ResponseVO<User> responseVO = new ResponseVO<>();

        iCompanyService.verifyToken(token);
        User user = iUserService.selectByUserId(userId);
        String headUrl = user.getHeadUrl();
        String userName = user.getUserName();
        User userRes = new User();
        userRes.setUserName(userName);
        userRes.setHeadUrl(headUrl);
        responseVO.setResultObject(userRes);

        return responseVO;
    }
}
