package test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
public class test1 {

    //定义字符串成员变量
    private String FirstProperty = new String("");
    //空参构造方法
    public test1() {

    }

    //get   set方法
    public String getFirstProperty() {
        return FirstProperty;
    }
    public void setFirstProperty(String value) {
        FirstProperty = value;
    }



    //主方法
    public static void main(String[] args)
    {
        System.out.println("My First JavaBean!");
    }
}