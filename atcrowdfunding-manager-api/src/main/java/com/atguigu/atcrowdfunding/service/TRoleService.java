package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.github.pagehelper.PageInfo;

public interface TRoleService {

	PageInfo<TRole> listRolePage(Map<String, Object> paramMap);

	void saveTRole(TRole role);

	TRole getRoleById(Integer id);

	void updateTRole(TRole role);

	void deleteTRole(Integer id);

}
