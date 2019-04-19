package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Murphy
 * @date 2019/4/19 23:19
 */
//    @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@RestController
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class UserController {
    @Resource
    private IUserService iUserService;
    @Autowired
    public UserController(IUserService iUserService){
        this.iUserService =iUserService;
    }

    /**
     * @param user
     * @return java.util.Map
     * @author Murphy
     * @date 2019/4/20 3:40
     * @description 注册方法 //TODO
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)@ResponseBody
    public  Map register(@RequestBody User user){
        Map map=new HashMap<>();
        user.setStudentId("123");
        iUserService.register(user);
        map.put("status",200);
        map.put("id",user.getStudentId());
        return map;
    }

    /**
     * @param
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/20 3:40
     * @description 测试方法
     */
    @RequestMapping(value = "/*",method = RequestMethod.POST)@ResponseBody
    public String other() {
        return "nimabi";
    }
}
