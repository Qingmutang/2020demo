package com.tony.demo.misc.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -605190154101163813L;

	public BaseException() {

	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return getMessage();
	}
	
	

}
