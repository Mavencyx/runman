package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Desc;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.favritservice;
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
@WebServlet("/favrite")
public class favriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json,setchar=uft8");
        ObjectMapper mp = new ObjectMapper();
        UserDaoImpl ud = new UserDaoImpl();
        User use = (User) request.getSession().getAttribute("users");
        String rids = request.getParameter("rid");
        Integer rid = Integer.valueOf(rids);
        ResultInfo rf = new ResultInfo();
        favritservice fs = new favritservice();
        String a = request.getParameter("a");


        if(use!=null){
                Favorite fv = new Favorite();
                Favorite fvt = fs.findfavrit(rid,use);
                //一旦有用户就要查收藏了多少条
                int ffs = fs.findfavrits(use);
                rf.setData(ffs);
                 if(a.equals("false")){
                    fs.save(rid,use);
                     ffs+=1;
                     rf.setData(ffs);
                     rf.setFlag(true);
                     mp.writeValue(response.getOutputStream(),rf);


                     //存排名
                     Desc count = ud.findcount(rid);
                     int counts = count.getCount();
                     System.out.println(counts);
                     if(counts!=0){
                        int count1=counts+1;
                        ud.updatecount(count1,rid);

                    }else {
                        ud.addrid_count(rid,1);
                    }

                 }else {

                    if(fvt==null){
                        //有用户但是没有收藏

                        mp.writeValue(response.getOutputStream(),rf);

                    }else {
                        //有用户且有收藏
                        rf.setFlag(true);
                        mp.writeValue(response.getOutputStream(),rf);
                }
            }



        }else {
            //没有用户

            rf.setErrorMsg("请先登录");
            mp.writeValue(response.getOutputStream(),rf);
        };
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
