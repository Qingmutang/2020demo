package com.tony.demo.misc.base;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseRequest implements Serializable{
	
	private String token;

}
