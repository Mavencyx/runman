package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.Pageservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 22007 on 2020/10/25.
 */
@WebServlet("/route/*")
public class RouteServlet extends BeanServlet {
    public void frname(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String searchname = request.getParameter("search");
        response.getWriter().write(searchname);
        String nowpage = request.getParameter("nowpage");
        Integer nowpages = Integer.valueOf(nowpage);
        Pageservice pg = new Pageservice();
        Page<Route> fn = pg.firname(searchname,8,nowpages);
        new ObjectMapper().writeValue(response.getOutputStream(),fn);
    }
}
