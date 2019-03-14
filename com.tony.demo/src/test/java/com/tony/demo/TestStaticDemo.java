package com.tony.demo;

public class TestStaticDemo {
	
	private static int a;
	int b;
	static int c;
	
	public int ma() {
		a++;
		return a;
	}
	
	public int mb() {
		b++;
		return b;
	}
	
	public int mc() {
		c++;
		return c;
	}

	public static void main(String[] args) {
		TestStaticDemo demo = new TestStaticDemo();
	/*	demo.ma();
		System.out.println(demo.ma()); 
		System.out.println("======");
		
		demo.mb();
		System.out.println(demo.mb()); 
        System.out.println("======");
		
		demo.mc();
		System.out.println(demo.mc());*/ 
		demo.a++;
		System.out.println(a);

		TestStaticDemo.a++;
		System.out.println(a);
	}
}
