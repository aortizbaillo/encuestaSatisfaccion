/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teide.aortiz.satisfaccion.bean;

import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class CourseBean {
    
    private ArrayList<DataBean> list;
    private String name;
    private String date;

    public CourseBean() {
    }
    
    public ArrayList<DataBean> getList() {
        return list;
    }

    public void setList(ArrayList<DataBean> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
