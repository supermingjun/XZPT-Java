package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.vo.ResponseVO;

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
        String path = "../webapps/img/" + UUID.randomUUID() + "." + fileName;

        File newFile = new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        responseVO.setResultObject(fileName);

        return responseVO;
    }

    /**
     * 上传文件接口
     *
     * @param file 文件
     * @return xz.fzu.vo.ResponseVO<java.lang.String>
     * @author Murphy
     * @date 2019/5/2 21:43
     */
    @RequestMapping("/file")
    public ResponseVO<String> fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {

        ResponseVO<String> responseVO = new ResponseVO<>();
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = "csv";
        }
        String fileName = UUID.randomUUID() + "." + format;
        String path = "../webapps/csv/" + UUID.randomUUID() + "." + fileName;

        File newFile = new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        responseVO.setResultObject(fileName);

        return responseVO;
    }
}
