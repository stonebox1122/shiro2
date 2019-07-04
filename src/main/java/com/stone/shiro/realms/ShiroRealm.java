package com.stone.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @author stone
 * @date 2019/7/3 21:23
 * description
 */
public class ShiroRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.把AuthenticationToken转换为UsernamePasswordToken
    	UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    	
    	// 2.从UsernamePasswordToken中获取username
    	String username = upToken.getUsername();
    	
    	// 3.调用数据库的方法，从数据库中查询username对应的用户记录
    	System.out.println("从数据库中获取username：" + username);
    	
    	// 4.若用户不存在，则可以抛出异常UnknownAccountException
    	if ("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在！");
		}
    	
    	// 5.根据用户信息的情况，决定是否需要抛出其他异常
    	if ("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定！");
		}
    	
    	// 6.根据用户的情况，构建AuthenticationInfo对象并返回。通常是由的实现类为：SimpleAuthenticationInfo
    	// 以下信息从数据库中获取
    	// 1).principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象。
    	Object principal = username;
    	// 2).credentials：密码
    	Object credentials = "123456";
    	// 3).realmName：当前realm对象的name，调用父类的getName()方法即可
    	String realmName = getName();
    	
    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
    	
        return info;
    }
}
