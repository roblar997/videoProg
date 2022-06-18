package com.example.demo.servlets;

import com.example.demo.DAO.FeatureDAO;
import com.example.demo.DAO.InitFenwickDAO;
import com.example.demo.DAO.TidslinjeDAO;
import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;

import com.example.demo.wrapper.InitFenwickTidslinjeFeatureWrapper;
import com.example.demo.wrapper.tidslinjeMethodWrapper;
import com.example.demo.wrapperServices.WrapperService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;


@WebServlet(name = "videoServlet", value = "/videoServlet")
public class videoServlet extends HttpServlet {

    @EJB
    private TidslinjeDAO tidslinjeDAO;

    @EJB
    private InitFenwickDAO initFenwickDAO;

    @EJB
    private FeatureDAO featureDAO;

    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        if(request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")){
            String remoteMethod = request.getParameter("remoteMethod");
            if(remoteMethod.equals("getChanges")){
                out.println("getChanges");
            }
            else if(remoteMethod.equals("addTimeLine")){
                out.println("addTimeLine");
            }
            else if(remoteMethod.equals("getInitState")){
                response.setContentType("application/json");
                Type typeInfo = new TypeToken<InitFenwickTidslinjeFeatureWrapper>() {}.getType();

                List<Tidslinje> tidslinjeListe = tidslinjeDAO.getTidslinjer();
                List<Feature>   features       = featureDAO.getFeatures();

                InitFenwick initFenwick = initFenwickDAO.getFenwick();
                InitFenwickTidslinjeFeatureWrapper wrapped = WrapperService.assembleInitFenwickTidslinjeWrapper(initFenwick,tidslinjeListe,features);

                String json = gson.toJson(wrapped, typeInfo);
                out.println(json);
            }
            else {
                out.println("elseStatement");
            }
        }
        else if(request.getContentType().equals("application/json; charset=UTF-8")){
            StringBuffer string = new StringBuffer();
            String line = null;
            try(BufferedReader reader = request.getReader()){
                while ((line = reader.readLine()) != null)
                    string.append(line);
            } catch (Exception e) { }

            Type typeInfo = new TypeToken<tidslinjeMethodWrapper>() {}.getType();
            tidslinjeMethodWrapper wrapp = gson.fromJson(string.toString(),tidslinjeMethodWrapper.class);
            out.println(wrapp.toString());

        }
      else {
          out.println(request.getContentType());
        }


        out.close();
    }
}