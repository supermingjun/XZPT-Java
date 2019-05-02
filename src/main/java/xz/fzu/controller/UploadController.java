package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.vo.ResponseData;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


/**
 * @author Murphy
 * @title: UploadController
 * @projectName XZPT-Java
 * @description: 文件上传相关的控制器
 * @date 2019/4/24 19:01
 */
@RestController
@RequestMapping(value = "/upload/", method = RequestMethod.POST)
public class UploadController {
    @RequestMapping("/*")
    public ResponseData<String> fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {

        ResponseData<String> responseData = new ResponseData<>();
        String[] names = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String format;
        if (names.length - 1 >= 0) {
            format = names[names.length - 1];
        } else {
            format = "jpg";
        }
        String fileName = UUID.randomUUID() + "." + format;
        String path = "../webapps/img/" + UUID.randomUUID() + "." + fileName;
        ;

        File newFile = new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        responseData.setResultObject(fileName);

        return responseData;
    }
}
