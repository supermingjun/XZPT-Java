package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;
import xz.fzu.service.IFileService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * 文件上传相关的控制器
 *
 * @author Murphy
 * @date 2019/4/24 19:01
 */
@RestController
public class FileController {

    /**
     * 用户service主要用来获取用户id
     */
    @Resource
    IUserService iUserService;

    /**
     * 文件service，主要用来读写文件
     */
    @Resource
    IFileService iFileService;

    /**
     * 上传图片接口
     *
     * @param file 文件
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/2 21:43
     */
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public ResponseVO<String> imageUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam String token) throws IOException, TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();

        User user = iUserService.selectUserByToken(token);
        String fileName = iFileService.saveFile(user.getUserId(), IFileService.JPG, file);
        responseVO.setResultObject(fileName);

        return responseVO;
    }

    /**
     * 上传文件
     *
     * @param file  文件流
     * @param token token
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/20 21:35
     */
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    public ResponseVO<String> fileUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam String token) throws IOException, TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();

        User user = iUserService.selectUserByToken(token);
        String fileName = iFileService.saveFile(user.getUserId(), IFileService.CSV, file);
        responseVO.setResultObject(fileName);

        return responseVO;
    }


}
