package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IUserDao;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;

import javax.annotation.Resource;

/**
 * @author Murphy
 * @date 2019/4/19 23:14
 */
@Service
public class UserServiceImpl implements IUserService {

//    @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入
    @Resource
    IUserDao IUserDao;
    @Override
    public void register(User user) {
        IUserDao.register(user);
    }
}
