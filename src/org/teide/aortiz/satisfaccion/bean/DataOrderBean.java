/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.teide.aortiz.satisfaccion.bean;

/**
 *
 * @author antonio
 */
public class DataOrderBean implements Comparable<DataOrderBean>{
    
    private String resp;
    private int nResp;
    
    public DataOrderBean () {}

    public DataOrderBean(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public int getnResp() {
        return nResp;
    }

    public void setnResp(int nResp) {
        this.nResp = nResp;
    }
    
    public void increment () {
        nResp++;
    }

    @Override
    public int compareTo(DataOrderBean o) {
        if (nResp > o.getnResp()) return 1;
        else if (nResp < o.getnResp()) return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        DataOrderBean dob = (DataOrderBean) obj;
        return resp.equals(dob.getnResp());
    }
    
}
