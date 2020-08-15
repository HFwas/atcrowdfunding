package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TAdminController {
	
	@Autowired
	TAdminService adminService;
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNume",required =false,defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",required =false,defaultValue="1")Integer pageSize,
			Model model) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		model.addAttribute("page",page);
		
		return "admin/index";
	}
	
}
