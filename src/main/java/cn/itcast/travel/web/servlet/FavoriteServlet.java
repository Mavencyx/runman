package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.Pageservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22007 on 2020/10/27.
 */
@WebServlet("/myfavorite")
public class FavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json,setchar=utf8");

        User use = (User) request.getSession().getAttribute("users");
        String nowpages = request.getParameter("nowpage");
        Integer nowpage = Integer.valueOf(nowpages);

        Page fpage = new Pageservice().favoritpages(use,nowpage);

        new ObjectMapper().writeValue(response.getOutputStream(),fpage);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
