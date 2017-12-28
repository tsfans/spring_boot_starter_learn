package com.yl.spring_boot_starter_learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yl.spring_boot_starter_learn.domain.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser,Long>{

	SystemUser findByUsername(String name);
	
}
