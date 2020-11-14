package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.Detailservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 22007 on 2020/10/26.
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String rids = request.getParameter("rid");
        Integer rid = Integer.valueOf(rids);
        Detailservice ds = new Detailservice();
        Route findone = ds.findone(rid);
        ObjectMapper mp = new ObjectMapper();
        response.setContentType("text/json,setchar=utf8");
        mp.writeValue(response.getOutputStream(),findone);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
