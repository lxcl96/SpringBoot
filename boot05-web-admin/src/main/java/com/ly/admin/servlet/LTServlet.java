package com.ly.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FileName:servlet.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
//@WebServlet(urlPatterns = "/lao_tie")
public class LTServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("666，老铁没毛病！");
    }
}
