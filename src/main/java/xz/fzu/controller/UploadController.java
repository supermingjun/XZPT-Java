package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ResponseVO;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


/**
 * 文件上传相关的控制器
 *
 * @author Murphy
 * @date 2019/4/24 19:01
 */
@RestController
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController {

    private static final String APP_HOME = "/var/lib/tomcat8/webapps/XZPT-Java-1.0-SNAPSHOT/file/";
    private static final String UPLOAD = "/upload/";
    private static final String DOWNLOAD = "/download/";
    private static final String IMAGE = "/image/";
    private static final String CSV = "csv";
    private static final String JPG = "jpg";
    /**
     * 上传文件接口
     *
     * @param file 文件
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/2 21:43
     */
    @Resource
    IUserService iUserService;

    /**
     * 上传文件接口
     *
     * @param file 文件
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/2 21:43
     */
    @RequestMapping("/image")
    public ResponseVO<String> imageUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam String token) throws IOException, TokenExpiredException {

        return upload(file, token, JPG, IMAGE);
    }

    @RequestMapping("/file")
    public ResponseVO<String> fileUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam String token) throws IOException, TokenExpiredException {

        return upload(file, token, CSV, UPLOAD);
    }

    /**
     * 随机生成文件名
     *
     * @param file 文件
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:44
     */
    private String getFileName(CommonsMultipartFile file, String defaultFormat) {
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = defaultFormat;
        }
        return UUID.randomUUID() + "." + format;
    }

    /**
     * 根据token获得用户id
     *
     * @param token token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/5/19 13:47
     */
    private String getPath(String token, String dir, String fileName) throws TokenExpiredException {
        String userId = iUserService.selectUserByToken(token).getUserId();

        return APP_HOME + userId + dir + fileName;
    }


    /**
     * 提取公共的上传方法
     *
     * @param file              上传的文件
     * @param token             token
     * @param defaultFileFormat 文件的默认格式
     * @param opType            操作类型
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/19 13:55
     */
    private ResponseVO<String> upload(CommonsMultipartFile file, String token, String defaultFileFormat, String opType) throws TokenExpiredException, IOException {
        ResponseVO<String> responseVO = new ResponseVO<>();

        String fileName = getFileName(file, defaultFileFormat);
        String path = getPath(token, opType, fileName);

        file.transferTo(new File(path));
        responseVO.setResultObject(fileName);

        return responseVO;
    }

}
