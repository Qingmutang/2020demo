package com.servelt.demo.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qinglin
 * @create 2020-09-08 10:32
 */
public class MyHelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if("add".equalsIgnoreCase(method)){
            req.getSession().setAttribute("msg","执行了add");
        }

        if("delete".equalsIgnoreCase(method)){
            req.getSession().setAttribute("msg","执行了delete");
        }

        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
