package xz.fzu.security;

/**
 * @author Murphy
 * @date 2019/4/20 15:04
 */

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;

import javax.annotation.Resource;

/**
 * @author Nicky
 * @description 基于Shiro框架的权限安全认证和授权
 * @date 2017年3月12日
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 注解引入业务类
     **/
    @Resource
    IUserService iUserService;

    /**
     * 登录信息和用户验证信息验证(non-Javadoc)
     *
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();                //得到用户名
        String password = new String((char[]) token.getCredentials());  //得到密码

        User user = iUserService.selectByEmail(username);

        /**检测是否有此用户 **/
        if (user == null) {
            throw new UnknownAccountException();//没有找到账号异常
        }
        /**检验账号是否被锁定 **/
//        if(Boolean.TRUE.equals(user.getLocked())){
//            throw new LockedAccountException();//抛出账号锁定异常
//        }
        /**AuthenticatingRealm使用CredentialsMatcher进行密码匹配**/
        if (null != username && null != password) {
            return new SimpleAuthenticationInfo(username, password, getName());
        } else {
            return null;
        }

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
     *
     * @see AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        String username = (String) pc.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(iUserService.get(username));
//        authorizationInfo.setStringPermissions(iUserService.getPermissions(username));
        System.out.println("Shiro授权");
        return authorizationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

}