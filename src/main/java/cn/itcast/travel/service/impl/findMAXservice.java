package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Desc;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Page;
import cn.itcast.travel.domain.Route;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 22007 on 2020/10/28.
 */
public class findMAXservice {

    public Page<Route> findMax(int nowpage){
        //定义的容器
        UserDaoImpl ud = new UserDaoImpl();
        ArrayList<Route> routes = new ArrayList<>();



        int star=0;
        star=(nowpage-1)*8;
        List<Desc> descs = ud.finrid_counts(star, 8);
        for (Desc drid:descs) {
            int rid = drid.getRid();
            Route fr = ud.findridroute(rid);
            Desc d= ud.findcount(rid);
            int counts = d.getCount();
            fr.setCount(counts);
            routes.add(fr);
        }
        int i = ud.findtollrid_counts();
        Page<Route> routePage = new Page<>();
        routePage.setList(routes);
        routePage.setTollcount(i);
        int pages= i%8==0 ? i/8:(i/8)+1;
        routePage.setPages(pages);


        return routePage;

    }
}
