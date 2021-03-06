package com.stone.shiro.handler;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stone.shiro.service.ShiroService;

/**
 * @author stone
 * @date 2019/7/4 10:42
 * description
 */
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	
	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value12345");
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                // 执行登录，看ini文件中是否有对应的用户名和密码
                currentUser.login(token);
                // 若没有指定的账户，将会抛出UnknownAccountException异常
            }
            catch (AuthenticationException ae) {
                System.out.println("登录失败：" + ae.getMessage());
            }
        }

        return "redirect:/list.jsp";
    }
}
