package com.atguigu.atcrowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {
	Logger log = LoggerFactory.getLogger(DispatcherController.class);
	
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
	
	@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpswd) {
		log.debug("开始登陆");
		
		log.debug("loginacct={}" + loginacct);
		log.debug("userpswd={}"+ userpswd);
		
		return "main";
	}

}	
