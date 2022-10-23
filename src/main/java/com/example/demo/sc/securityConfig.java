package com.example.demo.sc;

import javax.sql.DataSource;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("admin").password("{noop}12345").roles("user","admin");
		auth.inMemoryAuthentication().withUser("yousef").password("{noop}12345").roles("user");
		auth.inMemoryAuthentication().withUser("hamza").password("{noop}12345").roles("user");
		
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select login as principal,pass as credentianls,active from users where login=?")
//		.authoritiesByUsernameQuery("select login as principal,role as role from user_roles where login=?")
//		.passwordEncoder((PasswordEncoder) new Md5PasswordEncoder()).rolePrefix("ROLE_");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/user/*").hasRole("user");
		
		http.authorizeRequests().antMatchers("/admin/*").hasRole("admin");
		
		http.exceptionHandling().accessDeniedPage("/Error");
		
	}

}
