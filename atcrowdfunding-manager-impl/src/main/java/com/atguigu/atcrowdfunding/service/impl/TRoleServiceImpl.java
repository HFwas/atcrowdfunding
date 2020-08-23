package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.bean.TRoleExample.Criteria;
import com.atguigu.atcrowdfunding.mapper.TAdminRoleMapper;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;

@Service
public class TRoleServiceImpl implements TRoleService {
	
	@Autowired
	TRoleMapper roleMapper;
	
	@Autowired
	TAdminRoleMapper adminRoleMapper;

	@Override
	public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
		
		String condition = (String)paramMap.get("condition");
		
		List<TRole> list = null;
		
		if (StringUtils.isEmpty(condition)) {
			list = roleMapper.selectByExample(null);
		}else {
			TRoleExample example = new TRoleExample();
			
			example.createCriteria().andNameLike("%"+condition+"%");
			
			roleMapper.selectByExample(example);
		}
		
		PageInfo<TRole> page = new PageInfo<>(list,5);
		
		return page;
	}

	@Override
	public void saveTRole(TRole role) {
		roleMapper.insertSelective(role);
	}

	@Override
	public TRole getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateTRole(TRole role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void deleteTRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TRole> listAllRole() {
		return roleMapper.selectByExample(null);
	}

	@Override
	public List<Integer> getRoleIdByAdminId(String id) {
		return adminRoleMapper.getRoleIdByAdminId(id);
	}
}
