package com.atguigu.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TMenuController {
	
	@RequestMapping("/menu/index")
	public String index() {
		return "menu/index";
	}
	
}
