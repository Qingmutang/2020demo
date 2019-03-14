package com.tony.demo.obj;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ObjDemo {
	
 public static void main(String[] args) {
	 Object obj = new ObjDemo();
	 System.out.println(obj.getClass());
	 Type type1 = obj.getClass().getGenericSuperclass();
	 System.out.println(type1.getTypeName()); 
	 if(type1 instanceof ParameterizedType) {
		 
	 }
	 
	 Object obj2 = new ObjDemo() {}; 
	 System.out.println(obj2.getClass()); 
	 Type type2 = obj2.getClass().getGenericSuperclass();
	 System.out.println(type2.getTypeName()); 
	 
     if(type2 instanceof ParameterizedType) {
		 
	 }
}
 
 public ObjDemo() {
	 System.out.println("obj demo..."); 
 }

}
