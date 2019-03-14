package com.tony.demo.misc.test.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * ObjectMapper
 * @author zhaoliang
 *
 */
public class ObjectMapperTestDemo {
	
	public void jsonShow() throws Exception {

		TonyUser user = new TonyUser();
		
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		
		Map<String,String> map = new ConcurrentHashMap<>();
		map.put("aa", "bb");
		map.put("cc", "dd");
		map.put("ff", "ee");
		
		user.setClasses(list);
		user.setTeachers(map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		/**
		 * Include.ALWAYS  是序列化对像所有属性
           Include.NON_NULL 只有不为null的字段才被序列化
           Include.NON_EMPTY 如果为null或者 空字符串和空集合都不会被序列化
		 */
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		
		
		
		String userJsonString = objectMapper.writeValueAsString(user);
		System.out.println(userJsonString); 
		
		TonyUser readValue = objectMapper.readValue(userJsonString, TonyUser.class);
		
		System.out.println(readValue); 
		
		String mapString = objectMapper.writeValueAsString(map);
		System.out.println(mapString); 
		
		Map mapObj = objectMapper.readValue(mapString, Map.class);
		System.out.println(mapObj); 
		
		
	}
	
	public static void main(String[] args) throws Exception {
		ObjectMapperTestDemo obj = new ObjectMapperTestDemo();
		obj.jsonShow(); 
		
		
	}

	

}

@Data
class TonyUser{
	private String name ="tony";
	private Integer age = 1;
	
	private Date birthDay = new Date();
	
	List<String> classes;
	
	Map<String,String> teachers;

	@Override
	public String toString() {
		return "TonyUser [name=" + name + ", age=" + age + ", birthDay=" + birthDay + ", classes=" + classes
				+ ", teachers=" + teachers + "]";
	}
	
	
	
}
