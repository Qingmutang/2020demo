package com.tony.demo.misc.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * json使用
 * FastJson对于json格式字符串的解析主要用到了一下三个类：
      1.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
      2.JSONObject：fastJson提供的json对象
      3.JSONArray：fastJson提供json数组对象
 * @author zhaoliang
 *
 */
public class JsonUtils {

	//json字符串-简单对象型
	private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

	//json字符串-数组类型
	private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

	//复杂格式json字符串
	private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

	/**
	 * json字符串-简单对象型到JSONObject的转换
	 */
	public void testJSONStrToJSONObject() {

	    JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

	    System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
	            + jsonObject.getInteger("studentAge"));

	}
   
	/**
	 * JSONObject到json字符串-简单对象型的转换
	 */
	public void testJSONObjectToJSONStr() {

	   //已知JSONObject,目标要转换为json字符串
	    JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
	  // 第一种方式
	    String jsonString = JSONObject.toJSONString(jsonObject);

	  // 第二种方式
	  //String jsonString = jsonObject.toJSONString();
	    System.out.println(jsonString);

	}
	
	/**
	 * json字符串-数组类型到JSONArray的转换
	 */
	public void testJSONStrToJSONArray() {

	    JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

	    //遍历方式1
	    int size = jsonArray.size();
	    for (int i = 0; i < size; i++) {

	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
	                + jsonObject.getInteger("studentAge"));
	    }

	    //遍历方式2
	    for (Object obj : jsonArray) {

	        JSONObject jsonObject = (JSONObject) obj;
	        System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
	                + jsonObject.getInteger("studentAge"));
	    }
	}


	/**
	 * json字符串-简单对象到JavaBean之间的转换
	 */
	public void testJSONStrToJavaBeanObj() {

		//第一种方式
	    JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

	    String studentName = jsonObject.getString("studentName");
	    Integer studentAge = jsonObject.getInteger("studentAge");

		//Student student = new Student(studentName, studentAge);

		//第二种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
		//Student student = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});

		//第三种方式,使用Gson的思想
	    Student student = JSONObject.parseObject(JSON_OBJ_STR, Student.class);

	    System.out.println(student);
	    String request="";
	    Map<String, String> map = JSONObject.parseObject(request, new TypeReference<Map<String, String>>(){}); 
	}
	
	public static void main(String[] args) {
		 String request="{\"aa\":\"name\"}";
		 Map<String, String> map = JSONObject.parseObject(request, new TypeReference<Map<String, String>>(){}); 
		 System.out.println(map); 
	}
	

}
