package cn.itcast.travel.domain;

/**
 * Created by 22007 on 2020/10/30.
 */
public class Desc {

    private int rid;
    private int count;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Desc{" +
                "rid=" + rid +
                ", count=" + count +
                '}';
    }
}
