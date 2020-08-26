package com.atguigu.atcrowdfunding.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AtcrowdFundingSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
		auth.userDetailsService(userDetailsService).
				passwordEncoder(new BCryptPasswordEncoder());
		
	
	}
	
	//自定义访问请求规则
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static/**","/welcome.jsp","/toLogin").permitAll()
		.anyRequest().authenticated();//剩下都需要认证
		
		// /login.jsp==POST  用户登陆请求发给Security
		http.formLogin().loginPage("/toLogin")
			.usernameParameter("loginacct")
			.loginProcessingUrl("/login")
			.passwordParameter("userpswd")
			.defaultSuccessUrl("/main")
			.permitAll();		
		
		http.csrf().disable();
		
		http.logout().logoutSuccessUrl("/index");
		
		http.rememberMe();
		
		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {

			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				
				//response.getWriter().print("403");//403权限不够，访问被拒绝
				
				//request.getRequestDispatcher("/WEB-INF/jsp/error/error403.jsp").forward(request, response);
				//   X-Requested-With: XMLHttpRequest
				
				String type = request.getHeader("X-Requested-With");
				
				if("XMLHttpRequest".equals(type)) {//异步的处理
					response.getWriter().print("403"); // 403 权限不够，访问被拒绝
				}else {//同步的处理
					request.getRequestDispatcher("/WEB-INF/jsp/error/error403.jsp").forward(request, response);
				}
			}
			
		});
		
		//super.configure(http);
	}
	
}
