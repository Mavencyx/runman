package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
//            //1.定义sql
//            String sql = "select * from tab_user where username = ?";
//            //2.执行sql
//            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);

            String sql="select *from tab_user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
//        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
//        //2.执行sql
//
//        template.update(sql,user.getUsername(),
//                    user.getPassword(),
//                user.getName(),
//                user.getBirthday(),
//                user.getSex(),
//                user.getTelephone(),
//                user.getEmail(),
//                user.getStatus(),
//                user.getCode()
//                );


        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email)values(?,?,?,?,?,?,?)";

        template.update(sql,user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";

            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 修改指定用户激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }

    /**
     * 根据用户名和密码查询的方法
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
//        try {
//            //1.定义sql
//            String sql = "select * from tab_user where username = ? and password = ?";
//            //2.执行sql
//            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
//        } catch (Exception e) {
//
//        }
        String sql ="select *from tab_user where username=? and password=?";

        try {
            user=template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        }catch (Exception e){


        }
        return user;
    }

    public List findheadname(){

        String sql="select *from tab_category";
        List<Category> querys = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return querys;
    }
    public List<Route> findpage(int cid, int star, int end){
        String sql="SELECT *FROM tab_route WHERE cid=? LIMIT ? ,?";
        List<Route> query = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),cid, star, end);


        return query;
    }
    public int toupage(int cid){
        String sql="select count(*)from tab_route WHERE cid=?";
        int i = template.queryForInt(sql,cid);
        return i;
    }
    public List<Route> findrname(String rname,int star,int end){
        String sql="SELECT *FROM tab_route WHERE rname like ? limit ?,?";
        List<Route> query = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),'%'+rname+'%',star,end);


        return query;
    }
    public int rnamepage(String rname){
        String sql="select count(*)from tab_route WHERE rname like ?";

        int i = template.queryForInt(sql,  '%' + rname + '%');



        return i;
    }

    public Route findrid(int rid) {
        String sql="select *from tab_route where rid=?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }

    public List<RouteImg> finImg(int rid) {

        String sql="select * from tab_route_img where rid=?";
        List<RouteImg> query = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return query;
    }

    public Seller findsid(int sid) {

        String sql="select *from tab_seller where sid=?";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        return seller;
    }

    public void savefavrite(int rid, String date, int uid) {
        //存fav
        String sql="insert into tab_favorite(rid,date,uid) values(?,?,?)";

       template.update(sql,rid,date,uid);

    }

    public Favorite findfavrite(int rid,int uid) {
        //查fav
        Favorite fvt=null;
        String sql = "select *from tab_favorite where rid=? AND uid=?";
        try {
           fvt = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fvt;

    }

    public int findfavrites(int uid) {

        String sql="select count(*) from tab_favorite WHERE uid=?";
        int i = template.queryForInt(sql,uid);
        return i;
    }

    public List<Favorite> finrid(int uid) {
        List<Favorite> s=null;

        try{
            String sql="select *from tab_favorite where uid=?";
            s = template.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid);
        }catch (Exception e){e.printStackTrace();}

        return s;
    }


    public Route findridroute(int rid) {

        Route route=null;
        try{
            String sql="select *from tab_route where rid=?";
            route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        }catch (Exception e){
            e.printStackTrace();
        }

        return route;
    }

    public int findfavrit(int rid) {

            String sql="select COUNT(*)from tab_favorite where rid=?";
           int i = template.queryForInt(sql, rid);
        return i;
    }

    public int findallroute() {

        String sql="select count(*)from tab_route";
        int i = template.queryForInt(sql);
        return i;
    }

    public List<Favorite> findfavrits() {
        List<Favorite> f=null;
        try{
            String sql="select *from tab_route";
            f = template.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class));
        }catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }
    public void updatecount(int count,int rid){

        String sql="update rid_counts set count=? where rid=?";
        int i = template.update(sql, count, rid);
        System.out.println(i);

    }

    public Desc findcount(int rid) {
        String sql="select *from rid_counts where rid=?";
        Desc count = template.queryForObject(sql, new BeanPropertyRowMapper<Desc>(Desc.class), rid);
        return count;
    }

    public List<Desc> finrid_counts(int start,int cent) {
        String sql="SELECT *FROM rid_counts ORDER BY COUNT DESC LIMIT ?,?";
        List<Desc> desc = template.query(sql, new BeanPropertyRowMapper<Desc>(Desc.class), start, cent);
        return desc;
    }

    public int findtollrid_counts() {
        String sql="select count(*)from rid_counts";
        int i = template.queryForInt(sql);
        return i;
    }

    public void addrid_count(int rid,int count) {
        String sql="insert into rid_counts(rid,count)values(?,?)";
        template.update(sql,rid,count);
    }
}
