package com.xxs.ems.model;


import javax.persistence.*;
import java.util.Date;

//考勤
@Entity
@Table(name = "t_Kaoqin")
public class Kaoqin {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private String leixing;//旷工 迟到  早退  出差  请假

    private String riqi;//日期

    private String beizhu;//备注

    private String kouqian;//扣钱

    private Date createtime;


    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(int deletestatus) {
        this.deletestatus = deletestatus;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getKouqian() {
        return kouqian;
    }

    public void setKouqian(String kouqian) {
        this.kouqian = kouqian;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }


}
