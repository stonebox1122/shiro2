package com.stone.shiro.service;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {
	
	@RequiresRoles("admin")
	public void testMethod() {
		System.out.println("testMethod,time " + new Date());
		Session session = SecurityUtils.getSubject().getSession();
		System.out.println(session.getAttribute("key"));
	}

}
