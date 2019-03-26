package com.xxs.ems.model;


import javax.persistence.*;
import java.util.Date;

//奖金
@Entity
@Table(name = "t_Jiangjin")
public class Jiangjin {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private Date createtime;

    private String content;//发放事由

    private String jine;//金额

    private String riqi;//日期


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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }


}
