package com.example.demo0905.controller;

import com.example.demo0905.model.Student;
import com.example.demo0905.model.WebContext;

/**
 * @author qinglin
 * @create 2020-09-05 11:16
 */
public  abstract class BaseController {



    public <T> T handler(HandlerCallback<T> handler){

        WebContext context = WebContext.builder().student(new Student("xiaoming", "133", 12)).build();
        //这里的处理是通用处理，后面在异常里面加入了全局异常捕获

        return handler.callback(context);

    }


    public void handlerWithoutReturn(HandlerCallbackWithoutReturn handler){

          handler((context)->{

              handler.callback(context);

              return null;
          });
    }


        @FunctionalInterface
        protected interface HandlerCallback<T>{

            T callback(WebContext context);
        }



       @FunctionalInterface
        protected interface HandlerCallbackWithoutReturn{

            void callback(WebContext context);
        }
}
