package com.yl.spring_boot_starter_learn.domain;

import javax.validation.constraints.Size;

public class People {

	@Size(max=4,min=2)
	private String name;
	
	private Integer age;
	
	private String nation;
	
	private String address;
    public People() {}
	public People(String name, Integer age, String nation, String address) {
		super();
		this.name = name;
		this.age = age;
		this.nation = nation;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
