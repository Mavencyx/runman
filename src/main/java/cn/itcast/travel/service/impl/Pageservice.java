package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22007 on 2020/10/24.
 */
public class Pageservice {


    public Page<Route> findpages(int cid,int concentpage,int nowpage){

        Page<Route> pag = new Page<Route>();
        UserDaoImpl ud = new UserDaoImpl();
        int toupage = ud.toupage(cid);

        int pages= toupage%concentpage==0 ? toupage/concentpage:(toupage/concentpage)+1;
        int star=0;
        star=(nowpage-1)*concentpage;
        List<Route> findpage = ud.findpage(cid, star, concentpage);

        pag.setTollcount(toupage);
        pag.setList(findpage);
        pag.setPages(pages);
        return pag;

    }
    public Page<Route> firname (String rname,int concentpage,int nowpage){


        Page<Route> pag = new Page<Route>();
        UserDaoImpl ud = new UserDaoImpl();
        int toupage = ud.rnamepage(rname);

        int pages= toupage%concentpage==0 ? toupage/concentpage:(toupage/concentpage)+1;
        int star=0;
        star=(nowpage-1)*concentpage;
        List<Route> findpage = ud.findrname(rname, star, concentpage);

        pag.setTollcount(toupage);
        pag.setList(findpage);
        pag.setPages(pages);
        return pag;

    }


    public Page favoritpages(User user,int nowpage) {

        UserDaoImpl ud = new UserDaoImpl();

        List<Favorite> fa = ud.finrid(user.getUid());

        int toupage=fa.size();
        int pages= toupage%8==0 ? toupage/8:(toupage/8)+1;
        int star=0;
        star=(nowpage-1)*8;
        int end=star+8;

        if(end>toupage){
            end=toupage;
        }else {
           end=star+8;
        }

        List<Route> routes = new ArrayList<Route>();
        for (int i = star; i <end ; i++) {

            int rid = fa.get(i).getRid();
            Route frru = new UserDaoImpl().findridroute(rid);
            routes.add(frru);
        }
        Page<Route> fpage = new Page<>();

        fpage.setList(routes);
        fpage.setPages(pages);
        fpage.setTollcount(toupage);


        return fpage;

    }
}
