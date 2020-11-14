package cn.itcast.travel.domain;

import java.util.List;

/**
 * Created by 22007 on 2020/10/24.
 */
public class Page<T> {
    private int tollcount;
    private int pages;
    private List<T> list;
    private int dunpage;

    public int getTollcount() {
        return tollcount;
    }

    public void setTollcount(int tollcount) {
        this.tollcount = tollcount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getDunpage() {
        return dunpage;
    }

    public void setDunpage(int dunpage) {
        this.dunpage = dunpage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "tollcount=" + tollcount +
                ", pages=" + pages +
                ", list=" + list +
                ", dunpage=" + dunpage +
                '}';
    }
}
