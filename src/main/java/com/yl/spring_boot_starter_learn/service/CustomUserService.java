package com.yl.spring_boot_starter_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yl.spring_boot_starter_learn.dao.SystemUserRepository;
import com.yl.spring_boot_starter_learn.domain.SystemUser;

public class CustomUserService implements UserDetailsService{

	@Autowired
	private SystemUserRepository systemUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = systemUserRepository.findByUsername(username);
		return user;
	}

}
