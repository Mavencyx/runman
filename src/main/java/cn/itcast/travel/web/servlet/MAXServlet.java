package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.Pageservice;
import cn.itcast.travel.service.impl.findMAXservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 22007 on 2020/10/28.
 */
@WebServlet("/max")
public class MAXServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json,setchar=utf8");


        String nowpages = request.getParameter("nowpage");
        Integer nowpage = Integer.valueOf(nowpages);

        Page<Route> fpage = new findMAXservice().findMax(nowpage);

        new ObjectMapper().writeValue(response.getOutputStream(),fpage);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
