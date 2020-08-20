package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	@RequestMapping("/admin/doDeleteBatch")
	public String doDelete(String ids, Integer pageNum) {
		List<Integer> idList = new ArrayList<Integer>();
		
		String[] split = ids.split(",");
		
		for (String isStr : split) {
			int id = Integer.parseInt(isStr);
			idList.add(id);
		}
		
		adminService.deleteBatch(idList);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/doDelete")
	public String doDelete(Integer id, Integer pageNum) {
		
		adminService.deleteTAdmin(id);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	@RequestMapping("/admin/doUpdate")
	public String doUpdate(TAdmin admin, Integer pageNum) {
		
		adminService.updateTAdmin(admin);
		
		return "redirect:/admin/index?pageNum="+pageNum;
	}
	
	
	@RequestMapping("/admin/toUpdate")
	public String toUpdate(Integer id, Model model) {
		
		TAdmin admin = adminService.getTAdminById(id);
		model.addAttribute("admin",admin);
		
		return "admin/update";
	}
	
	@RequestMapping("/admin/doAdd")
	public String doAdd(TAdmin admin) {
		
		adminService.saveTAdmin(admin);
		//return "redirect:/admin/index";
		return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
	}
	
	
	@RequestMapping("/admin/toAdd")
	public String toAdd() {
		
		return "admin/add";
	}
	
	@RequestMapping("/admin/index")
	public String index(@RequestParam(value="pageNum",required =false,defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",required =false,defaultValue="10")Integer pageSize,
			Model model,
			@RequestParam(value="condition",required=false,defaultValue="") String condition) {
		
		log.debug("pageNum={}",pageNum);
		log.debug("pageSize={}",pageSize);
		log.debug("condition={}",condition);
		
		
		PageHelper.startPage(pageNum, pageSize);//线程绑定
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("condition", condition);
		
		PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);
		
		model.addAttribute("page",page);
		
		return "admin/index";
	}
	
}
