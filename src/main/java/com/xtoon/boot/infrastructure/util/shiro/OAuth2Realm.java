package com.xtoon.boot.infrastructure.util.shiro;

import com.xtoon.boot.domain.model.user.User;
import com.xtoon.boot.domain.model.user.types.Token;
import com.xtoon.boot.domain.repository.PermissionRepository;
import com.xtoon.boot.domain.repository.UserRepository;
import com.xtoon.boot.domain.specification.LoginByTokenSpecification;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author haoxin
 * @date 2021-01-25
 **/
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(user.getUserPermissionCodes());
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        Token accountToken = new Token(accessToken,null);
        //根据accessToken，查询用户信息
        User user = userRepository.find(accountToken);
        LoginByTokenSpecification loginByTokenSpecification = new LoginByTokenSpecification();
        loginByTokenSpecification.isSatisfiedBy(user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
