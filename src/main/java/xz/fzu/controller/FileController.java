package xz.fzu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;
import xz.fzu.service.IFileService;
import xz.fzu.service.IUserService;
import xz.fzu.util.Constants;
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
        String fileName = iFileService.saveFile(user.getUserId(), Constants.JPG, file);
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
        String fileName = iFileService.saveFile(user.getUserId(), Constants.CSV, file);
        responseVO.setResultObject(fileName);

        return responseVO;
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @param token    token
     * @return org.springframework.http.ResponseEntity<byte [ ]>
     * @author Murphy
     * @date 2019/5/20 22:43
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownload(@RequestParam("file") String fileName, @RequestParam String token) throws IOException, TokenExpiredException {

        String userId = iUserService.selectUserByToken(token).getUserId();
        byte[] body = iFileService.readFile(userId, fileName);

        //防止中文乱码
        fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        //设置响应吗
        HttpStatus statusCode = HttpStatus.OK;

        return new ResponseEntity<>(body, headers, statusCode);

        //public ResponseEntity（T  body，
        //                       MultiValueMap < String，String > headers，
        //                       HttpStatus  statusCode）
        //HttpEntity使用给定的正文，标题和状态代码创建一个新的。
        //参数：
        //body - 实体机构
        //headers - 实体头
        //statusCode - 状态码
    }

}
