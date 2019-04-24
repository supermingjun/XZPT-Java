package xz.fzu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import xz.fzu.util.Constants;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/24 19:01
 */
@RestController
@RequestMapping(value = "/upload/", method = RequestMethod.POST)
public class UploadController {
    @RequestMapping("/*")
    public Map<Object, Object> fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {

        Map<Object, Object> returnMap = initResultMap();


        try {
            String[] names = file.getOriginalFilename().split("\\.");
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
            returnMap.put(Constants.resultObject, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put(Constants.resultCode, Constants.UNKNOWN_ERROR);
            returnMap.put(Constants.resultMsg, e.getMessage());
        }
        return returnMap;
    }

    private Map<Object, Object> initResultMap() {

        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        returnMap.put(Constants.resultCode, Constants.OK);
        return returnMap;
    }
}
