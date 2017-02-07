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
public class DataBean {
    
    private String sex;
    private String[] resp;
    private String[] know;
    private String[] opinion;
    
    public DataBean () {}

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getResp() {
        return resp;
    }

    public void setResp(String[] resp) {
        this.resp = resp;
    }

    public String[] getKnow() {
        return know;
    }

    public void setKnow(String[] know) {
        this.know = know;
    }

    public String[] getOpinion() {
        return opinion;
    }

    public void setOpinion(String[] opinion) {
        this.opinion = opinion;
    }
}
