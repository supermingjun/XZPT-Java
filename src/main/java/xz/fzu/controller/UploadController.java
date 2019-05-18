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
    public ResponseVO<String> imageUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = "jpg";
        }
        String fileName = UUID.randomUUID() + "." + format;
        String path = APP_HOME + "image/" + UUID.randomUUID() + "." + fileName;

        File newFile = new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        responseVO.setResultObject(fileName);

        return responseVO;
    }

    @RequestMapping("/file")
    public ResponseVO<String> fileUpload(@RequestParam("file") CommonsMultipartFile file, @RequestParam String token) throws IOException, TokenExpiredException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String userId = iUserService.selectUserByToken(token).getUserId();
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = "csv";
        }
        String fileName = UUID.randomUUID() + "." + format;
        String path = APP_HOME + userId +
                "/upload/" +
                UUID.randomUUID() + "." + fileName;

        File newFile = new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        responseVO.setResultObject(fileName);
        return responseVO;
    }
}
