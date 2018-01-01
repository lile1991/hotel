package com.hotel.shiro;

import com.hotel.manage.user.UserManage;
import com.hotel.manage.user.UserResourceManage;
import com.hotel.user.entity.Resource;
import com.hotel.user.entity.User;
import com.hotel.vo.UserSessionVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserManage userManage;

    @Autowired
    UserResourceManage userResourceManage;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserSessionVo userSessionVo = (UserSessionVo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userApi.findRoles(username));

        List<Resource> resources = userResourceManage.findByUserId(userSessionVo.getId());
        if(resources != null) {
            authorizationInfo.setStringPermissions(resources.stream().map(Resource::getValue).collect(Collectors.toSet()));
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // subject.login(userToken);用的是UsernamePasswordToken实例， 所以这里接收到的也是UsernamePasswordToken实例
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = (String) authenticationToken.getPrincipal();
        char[] password = (char[]) authenticationToken.getCredentials();

        User user = userManage.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        UserSessionVo userSessionVo = new UserSessionVo();
        userSessionVo.setId(user.getId());
        userSessionVo.setCredentials(user.getPassword());

        // 密码加密， 数据库已经是加密后的， 这里无需再次加密
        // new Md5Hash(user.getPassword(), salt, 2).toString();

        String salt = "CBD";
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        return new SimpleAuthenticationInfo(
                userSessionVo, //用户ID
                userSessionVo.getCredentials(), // 数据库中的密码
                ByteSource.Util.bytes(salt),// 有点咸
                getName()  //realm name
        );
    }
}
