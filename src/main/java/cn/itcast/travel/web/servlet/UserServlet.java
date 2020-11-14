package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.Pageservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by 22007 on 2020/10/24.
 */
@WebServlet("/use/*")
public class UserServlet extends BeanServlet {
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check = request.getParameter("check");
        HttpSession checkcode_server = request.getSession();
        String cs = (String) checkcode_server.getAttribute("CHECKCODE_SERVER");
        if (check.equalsIgnoreCase(cs)) {

            Map<String, String[]> rMap = request.getParameterMap();
            User user = new User();
            BeanUtils beanUtils = new BeanUtils();
            try {
                beanUtils.populate(user, rMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UserDaoImpl rd = new UserDaoImpl();
            User byUsername = rd.findByUsername(user.getUsername());
            if (byUsername == null) {
                rd.save(user);
                response.setContentType("text/json,setchar=ut8");
                response.getWriter().write("{\"errorMsg\":\"注册成功\",\"flag\":true}");
            } else {
                //有该用户不能注册
                response.setContentType("text/json,setchar=ut8");
                response.getWriter().write("{\"errorMsg\":\"注册失败该用户也存在\",\"flag\":false}");
            }
        } else {
            response.setContentType("text/json,setchar=yt8");
            response.getWriter().write("{\"errorMsg\":\"验证码错误\",\"flag\":false}");
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String sc = (String) session.getAttribute("CHECKCODE_SERVER");
        if (check.equalsIgnoreCase(sc)) {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils beanUtils = new BeanUtils();
            try {
                beanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            UserDaoImpl userDao = new UserDaoImpl();
            User bup = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (bup != null) {
                request.getSession().setAttribute("users", bup);
                response.setContentType("text/json,setchar=utf8");
                response.getWriter().write("{\"errorMsg\":\"登录成功\",\"flag\":true}");
            } else {
                response.setContentType("text/json,setchar=utf8");
                response.getWriter().write("{\"errorMsg\":\"登陆失败用户或密码错误\",\"flag\":false}");
            }
        } else {
            response.setContentType("text/json,setchar=utf8");
            response.getWriter().write("{\"errorMsg\":\"验证码错误\",\"flag\":false}");
        }
    }

    public void finduser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Object user = request.getSession().getAttribute("users");
        if (user == null) {
            response.setContentType("text/json,setchar=utf8");
            response.getWriter().write("");
        } else {
            response.setContentType("text/json,setchar=utf8");
            new ObjectMapper().writeValue(response.getOutputStream(), user);

        }
    }

    public void loseuser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();
        response.sendRedirect("/travel/index.html");
    }

    public void headname(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List findheadname = new UserDaoImpl().findheadname();
        response.setContentType("text/json,setchar=utf8");
        new ObjectMapper().writeValue(response.getOutputStream(), findheadname);

    }


    public void page(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}
