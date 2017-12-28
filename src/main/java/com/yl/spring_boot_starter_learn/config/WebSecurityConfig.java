package com.yl.spring_boot_starter_learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.yl.spring_boot_starter_learn.service.CustomUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .authorizeRequests()
//		    .antMatchers("/**").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    .failureUrl("/login?error")
		    .permitAll()
		    .and()
		    .logout()
		    .permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(customUserService());
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/js/**",
				"/bootstrap/**",
				"/jquery-ui-1.12.1/**",
				"/angular/**"
				);
	}
}
