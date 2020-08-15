package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.github.pagehelper.PageInfo;

@Service
public class TAdminServiceImpl implements TAdminService {
	
	@Autowired
	TAdminMapper adminMapper;

	@Override
	public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {

		//1.密码加密
		
		//2.查询用户
		String loginacct = (String)paramMap.get("loginacct");
		String userpswd = (String)paramMap.get("userpswd");
		
		//3.判断用户是否位null
		TAdminExample example = new TAdminExample();
		
		example.createCriteria().andLoginacctEqualTo(loginacct);
		
		List<TAdmin> list = adminMapper.selectByExample(example);
		if (list != null && list.size() == 1) {
			TAdmin admin = list.get(0);
			//4.判断密码是否一致
			if (admin.getUserpswd().equals(MD5Util.digest(userpswd))) {
				//5.返回结果
				return admin;
			}else {
				throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
			}
		}else {
			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
		}
	}

	@Override
	public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {

		adminMapper.selectByExample(null);
		
		return null;
	}
}
