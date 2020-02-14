package com.example.demo765.realm;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/2/14 22:47
 **/

import com.example.demo765.entity.User;
import com.example.demo765.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/2/14 22:47
 **/
public class HhyRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;
    //简单重写获取授权信息的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        User userByUsername = userMapper.findUserByUsername(username);
        String salt = userByUsername.getSalt();
        String password = userByUsername.getPassword();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(salt),getName());
        return simpleAuthenticationInfo;
    }
}
