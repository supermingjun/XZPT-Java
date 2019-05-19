package xz.fzu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Murphy
 * @date 2019/5/19 18:24
 */
@Controller
@RequestMapping(value = "/interviewskill", method = RequestMethod.GET)
public class ArticleController {

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String showArticle(Map<String, String> attribute, @RequestParam int index) {
        attribute.put("key", "value");
        return "article";
    }
}
