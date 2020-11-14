package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 22007 on 2020/10/24.
 */
@WebServlet("/BeanServlet")
public class BeanServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String rui = req.getRequestURI();
        String methodname = rui.substring(rui.lastIndexOf("/")+1);
        try {
            Method method = this.getClass().getMethod(methodname, HttpServletRequest.class,HttpServletResponse.class);
            Object invoke = method.invoke(this, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
