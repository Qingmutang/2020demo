package com.tony.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class ReflectTestDemo {
	
	public void getInfo() {
		User<List> user = new User<List>();
		user.setName("xiaoming");
		List obj = new ArrayList<String>();
		obj.add("aa");
		obj.add("bb");
		
		user.setObj(obj);
	    user.setClazz(ReflectTestDemo.class); 
		
		System.out.println(user); 
	}
	
	public static void main(String[] args) {
		//new ReflectTestDemo().getInfo();
		try {
			ReflectDemo reflectDemo = ReflectDemo.class.newInstance();
			Method method = ReflectDemo.class.getDeclaredMethod("getName", String.class);
			
			method.invoke(reflectDemo, "aa");
			
			Field ageField = ReflectDemo.class.getDeclaredField("age");
			ageField.setAccessible(true);
			
			ageField.set(reflectDemo, 12); 
			
			System.out.println(reflectDemo);
			/**
			 * 由于接口没有无参构造，而反射基于类，所以接口不可以使用反射
			 */
			IReflectDemo iReflectDemo = IReflectDemo.class.newInstance();
			Method method2 = IReflectDemo.class.getDeclaredMethod("getName", String.class);
			method2.invoke(iReflectDemo , "aa");
			
			System.out.println(iReflectDemo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
	@Data
	public class User<T>{
		private String name;
		private T obj;
		private Class<?> clazz;
		@Override
		public String toString() {
			return "User [name=" + name + ", obj=" + obj + ", clazz=" + clazz + "]";
		}
	   
	}

}
