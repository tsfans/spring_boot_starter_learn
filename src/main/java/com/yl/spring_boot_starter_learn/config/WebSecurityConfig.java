package com.yl.spring_boot_starter_learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .authorizeRequests()
		    .antMatchers("/","/login").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    .defaultSuccessUrl("/chat")
		    .permitAll()
		    .and()
		    .logout()
		    .permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth
             .inMemoryAuthentication()
             .withUser("swift").password("123456").roles("USER")
             .and()
             .withUser("hl").password("123456").roles("USER");
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
