package com.xxs.ems.model;


import javax.persistence.*;
import java.util.Date;

//请假
@Entity
@Table(name = "t_Qingjia")
public class Qingjia {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private String content;//请假事由

    private String qingjiariqi;//请假日期

    private String shenhe;//审核状态  未审核  ，审核通过  审核不通过

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getShenhe() {
        return shenhe;
    }

    public void setShenhe(String shenhe) {
        this.shenhe = shenhe;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQingjiariqi() {
        return qingjiariqi;
    }

    public void setQingjiariqi(String qingjiariqi) {
        this.qingjiariqi = qingjiariqi;
    }


}
