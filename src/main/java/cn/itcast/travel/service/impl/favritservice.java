package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static javax.print.attribute.standard.MediaPrintableArea.MM;

/**
 * Created by 22007 on 2020/10/26.
 */
public class favritservice {
    UserDaoImpl ud = new UserDaoImpl();
    public void save(int rid,User use) {
        //存收藏

        int uid = use.getUid();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        ud.savefavrite(rid,date,uid);

    }

    public Favorite findfavrit(int rid, User use) {

        //查收藏
        int uid = use.getUid();
        Favorite fvt = ud.findfavrite(rid,uid);
        return fvt;
    }


    public int findfavrits(User use) {

        int uid = use.getUid();

        int ffs = ud.findfavrites(uid);

        return ffs;
    }


}
