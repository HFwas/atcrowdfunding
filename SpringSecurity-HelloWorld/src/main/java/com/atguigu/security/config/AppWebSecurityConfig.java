package com.atguigu.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

@Configuration//声明当前类是一个配置类，相当于xml配置文件
@EnableWebSecurity
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;//用户详情查询服务组件的接口
	
	//@Autowired
	//PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
	
		//实验四：自定义认证用户信息   基于内存认证方式
//		auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("学徒")
//				.and()
//				.withUser("lisi").password("123456").authorities("武当拳","罗汉拳");
		
		//采用数据库认证方式
		//auth.userDetailsService(userDetailsService);  //默认密码校验，按照明文进行校验。
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http); //默认的权限规则，所有请求都受限制
		
		//实验一：授权首页和静态资源
//		http.authorizeRequests()
//					.antMatchers("/layui/**","/index.jsp").permitAll()
//					.anyRequest().authenticated();
		
		//实验6，授权访问
		http.authorizeRequests()
				.antMatchers("/layui/**","/index.jsp").permitAll()
				//.antMatchers("/level1/**").hasRole("学徒")  //相当于调用hasAuthority("ROLE_学徒")
				//.antMatchers("/level2/**").hasRole("大师")
				//.antMatchers("/level3/**").hasRole("宗师")
				//.antMatchers("/level3/**").hasAuthority("葵花宝典")
				.anyRequest().authenticated();
		
		
		//实验二：默认及自定义登录页
		//http.formLogin();//默认登录页
		http.formLogin().loginPage("/index.jsp")//自定义登录页
					.usernameParameter("loginacct")
					.passwordParameter("userpswd")
					.loginProcessingUrl("/doLogin")
					.defaultSuccessUrl("/main.html");
		
		http.exceptionHandling().accessDeniedPage("/unauth.html");
		
		//开启记住我功能。
		//http.rememberMe();  //基于Cookie的方式实现记住我功能
		JdbcTokenRepositoryImpl ptr = new JdbcTokenRepositoryImpl();
		ptr.setDataSource(dataSource);
		http.rememberMe().tokenRepository(ptr);
		
		http.csrf().disable();//禁用csrf
		
		//http.logout(); //默认注销请求   请求路径："/logout"
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/index.jsp");
	}

	//MD5+盐+随机数
	//$2a$10$.gt2E3i5WHU6XrDQ/tbJ2uhLtutfCpNMX1I.CH8LSiKjgSc41o1hy
	//$2a$10$6e8Tv0Z/kp.xDMJirgm1jepkllup0z7Fet8XVGMV/ZqXUfTCN2XR.
	//$2a$10$sJrXoUlOp3es.IAFH6YPGuQVwD74iot79T4rc8AhkW7THJFEPr226
	public static void main(String[] args) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String encode = bcpe.encode("123456");
		System.out.println(encode);
	}

}
