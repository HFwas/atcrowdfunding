package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TRoleController {
	
	@Autowired
	TRoleService roleService;
	
	Logger log = LoggerFactory.getLogger(TRoleController.class);
	
	@ResponseBody
	@RequestMapping("/role/doAdd")
	public String doAdd(TRole role) {
		
		roleService.saveTRole(role);
		return "ok";
	}
	
	/**
	 * 启用消息转换器：HttpMessageConverter
	 * 如果返回结果为对象（Entity Class,List,Map..）类型：启用这个转换器->MappingJackson2HttpMessageConverter 将对象序列化为json串，使用Jackson组件转换
	 * 如果返回结果为String类型：启用这个转换器->StringHttpMessageConverter  将字符串原样输出。
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/role/loadData")
	public PageInfo<TRole> loadData(@RequestParam(value ="pageNum",required=false,defaultValue="1") Integer pageNum, 
			@RequestParam(value ="pageSize",required=false,defaultValue="2")Integer pageSize,
			@RequestParam(value ="condition",required=false,defaultValue="")String condition) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("condition", condition);
		
		PageInfo<TRole> page = roleService.listRolePage(paramMap);
		
		return page;//转换为JSON串返回
	}
	
	@RequestMapping("/role/index")
	public String index() {
		return "role/index";
	}
	
}
