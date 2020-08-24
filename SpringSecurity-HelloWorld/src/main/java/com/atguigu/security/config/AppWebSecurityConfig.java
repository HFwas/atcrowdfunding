package com.atguigu.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration//声明当前类是一个配置类，相当于xml配置文件
@EnableWebSecurity
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

}
