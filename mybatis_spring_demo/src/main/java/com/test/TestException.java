package com.test;

/**
 * @author qinglin
 * @create 2020-09-06 20:39
 */
public class TestException {

    public static void main(String[] args) {

        try{
            if(true){
                throw new Exception();
            }


        }catch (RuntimeException e){
            System.out.println("run...");
            System.out.println(e);
        }catch (Error e){
            System.out.println("error"+e);
            System.out.println(e);
        }catch (Throwable e){
            System.out.println("throwable...");
            System.out.println(e);
        }
    }
}
