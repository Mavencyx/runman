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
 * Created by 22007 on 2020/10/24.
 */
@WebServlet("/apage")
public class ServletPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cids = request.getParameter("cid");
        String nowpage = request.getParameter("nowpage");
        Integer nowpages= Integer.valueOf(nowpage);
        Integer cid = Integer.valueOf(cids);


        Pageservice ps = new Pageservice();
        Page<Route> fp = ps.findpages(cid, 8, nowpages);
        response.setContentType("text/json,setchar=utf8");
//        new ObjectMapper().writeValue(response.getOutputStream(),fp);
        ObjectMapper mp = new ObjectMapper();

        response.getWriter().write(mp.writeValueAsString(fp));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
