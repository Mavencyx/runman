package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;

/**
 * Created by 22007 on 2020/10/26.
 */
public class Detailservice {

    public Route findone(int rid){

        UserDaoImpl ud = new UserDaoImpl();
        Route r = ud.findrid(rid);
        List<RouteImg> ri = ud.finImg(rid);
        int sid= r.getSid();
        Seller fi = ud.findsid(sid);
        r.setRouteImgList(ri);
        r.setSeller(fi);
        return r;

    }
}
