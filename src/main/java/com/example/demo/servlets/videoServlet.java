package com.example.demo.servlets;

import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.entities.Tidslinje;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;


@WebServlet(name = "videoServlet", value = "/videoServlet")
public class videoServlet extends HttpServlet {

    @EJB
    private TidslinjeDAO tidslinjeDAO;

    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //Type typeInfo = new TypeToken<List<Tidslinje>>() {}.getType();
        //List<Tidslinje> tidslinjeListe = tidslinjeDAO.getTidslinjer();
        //String json = gson.toJson(tidslinjeListe, typeInfo);
        PrintWriter out = response.getWriter();
        out.println("te");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}