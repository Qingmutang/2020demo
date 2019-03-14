package com.tony.demo.reflect;

public class ReflectDemo implements IReflectDemo {
	
	private Integer age;
	private String name;

	@Override
	public String getName(String name) {
		this.name = name;
		return name;

	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
}
