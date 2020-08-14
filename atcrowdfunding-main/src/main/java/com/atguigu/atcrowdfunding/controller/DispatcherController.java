package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
public class DispatcherController {
	Logger log = LoggerFactory.getLogger(DispatcherController.class);
	
	@Autowired
	TAdminService adminService;
	
	@RequestMapping("/index")
	public String index() {
		log.debug("跳转到主页面");
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		log.debug("跳转到登陆页面");
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		log.debug("注销");
		if (session != null) {
			session.removeAttribute(Const.LOGIN_ADMIN);
			session.invalidate();
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd,HttpSession session,Model model) {
		log.debug("开始登陆");
		
		log.debug("loginacct={}" + loginacct);
		log.debug("userpswd={}"+ userpswd);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", userpswd);
		
		try {
			TAdmin admin = adminService.getTAdminByLogin(paramMap);
			session.setAttribute(Const.LOGIN_ADMIN, admin);
			log.debug("登陆成功");
			
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登陆失败={}",e.getMessage());
			model.addAttribute("message",e.getMessage());
			return "login";
		}
		
	}

}	
