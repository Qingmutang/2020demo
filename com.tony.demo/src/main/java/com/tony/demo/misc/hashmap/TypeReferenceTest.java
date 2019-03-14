package com.tony.demo.misc.hashmap;

import java.lang.reflect.Type;

public class TypeReferenceTest {
	
	 public static void main(String[] args) {
		 TypeReference<String> typeReference = new TypeReference<String>(){};
		 Type type = typeReference.getType();
		 System.out.println(type.getTypeName());
		 if (type instanceof Class<?>) {
			 System.out.println(type.getTypeName());
		 }
	} 

}
