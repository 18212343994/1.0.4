package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author vson
 */
public class SwipeCardBean {

    private int postition;
    private String url;
    private String name;

    public SwipeCardBean(int postition, String url, String name) {
        this.postition = postition;
        this.url = url;
        this.name = name;
    }

    public int getPosition() {
        return postition;
    }

    public SwipeCardBean setPosition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SwipeCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public SwipeCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SwipeCardBean> initDatas() {
        List<SwipeCardBean> datas = new ArrayList<>();
        int i = 1;


        datas.add(new SwipeCardBean(i, "1", "1"));
        datas.add(new SwipeCardBean(i, "1", "1"));
        datas.add(new SwipeCardBean(i, "1", "1"));


        return datas;

    }









}