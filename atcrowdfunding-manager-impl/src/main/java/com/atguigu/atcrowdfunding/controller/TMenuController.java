package com.atguigu.atcrowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;

@Controller
public class TMenuController {
	
	@Autowired
	TMenuService menuService;
	
	@ResponseBody
	@RequestMapping("/menu/doDelete")
	public String doDelete(Integer id) {
		menuService.deleteTMenu(id);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/menu/doUpdate")
	public String doUpdate(TMenu menu) {
		menuService.updateTMenu(menu);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/menu/getMenuById")
	public TMenu getMenuById(Integer id) {
		TMenu menu = menuService.getMenuById(id);
		return menu;
	}
	
	@ResponseBody
	@RequestMapping("/menu/doAdd")
	public String doAdd(TMenu menu) {
		menuService.saveTMenu(menu);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/menu/loadTree")
	public List<TMenu> loadTree() {
		List<TMenu> list = menuService.listMenuAllTree();
		return list;
	}
	
	@RequestMapping("/menu/index")
	public String index() {
		return "menu/index";
	}
	
}
