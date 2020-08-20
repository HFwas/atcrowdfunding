package com.atguigu.atcrowdfunding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.service.TRoleService;

@Controller
public class TRoleController {
	
	@Autowired
	TRoleService roleService;
	
	Logger log = LoggerFactory.getLogger(TRoleController.class);
	
	@RequestMapping("/role/index")
	public String index() {
		return "role/index";
	}
	
}
