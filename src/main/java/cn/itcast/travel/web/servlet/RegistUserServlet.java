package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
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
import java.util.Map;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends BeanServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //验证校验
//        String check = request.getParameter("check");
//        //从sesion中获取验证码
//        HttpSession session = request.getSession();
//        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
//        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
//        //比较
//        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
//            //验证码错误
//            ResultInfo info = new ResultInfo();
//            //注册失败
//            info.setFlag(false);
//            info.setErrorMsg("验证码错误");
//            //将info对象序列化为json
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(info);
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(json);
//            return;
//        }
//
//        //1.获取数据
//        Map<String, String[]> map = request.getParameterMap();
//
//        //2.封装对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        //3.调用service完成注册
//        UserService service = new UserServiceImpl();
//        boolean flag = service.regist(user);
//        ResultInfo info = new ResultInfo();
//        //4.响应结果
//        if(flag){
//            //注册成功
//            info.setFlag(true);
//        }else{
//            //注册失败
//            info.setFlag(false);
//            info.setErrorMsg("注册失败!");
//        }
//
//        //将info对象序列化为json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//
//        //将json数据写回客户端
//        //设置content-type
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);
//验证码的验证
        String check = request.getParameter("check");
        HttpSession checkcode_server = request.getSession();
       String cs = (String) checkcode_server.getAttribute("CHECKCODE_SERVER");
        if(check.equalsIgnoreCase(cs)){

            Map<String, String[]> rMap = request.getParameterMap();
            User user = new User();
            BeanUtils beanUtils = new BeanUtils();
            try {
                beanUtils.populate(user,rMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UserDaoImpl rd = new UserDaoImpl();

            User byUsername = rd.findByUsername(user.getUsername());


            if (byUsername==null){

                rd.save(user);

                response.setContentType("text/json,setchar=ut8");
                response.getWriter().write( "{\"errorMsg\":\"注册成功\",\"flag\":true}");
            }else {

                //有该用户不能注册
                response.setContentType("text/json,setchar=ut8");
                response.getWriter().write("{\"errorMsg\":\"注册失败该用户也存在\",\"flag\":false}");
            }

        }else{
            response.setContentType("text/json,setchar=yt8");
            response.getWriter().write("{\"errorMsg\":\"验证码错误\",\"flag\":false}");
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
