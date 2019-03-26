package com.xxs.ems.model;


import javax.persistence.*;

//工资
@Entity
@Table(name = "t_Gongzi")
public class Gongzi {

    @Id
    @GeneratedValue
    private int id;

    private String yuefen;//月份

    private String jibengongzi;//基本工资

    private String qingjia;//请假

    private String kuangong;//旷工

    private String chidao;//迟到

    private String zaotui;//早退

    private String chuchai;//出差

    private String jiangjin;//奖金

    private String zongji;//总计


    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getYuefen() {
        return yuefen;
    }


    public void setYuefen(String yuefen) {
        this.yuefen = yuefen;
    }


    public String getJibengongzi() {
        return jibengongzi;
    }


    public void setJibengongzi(String jibengongzi) {
        this.jibengongzi = jibengongzi;
    }


    public String getQingjia() {
        return qingjia;
    }


    public void setQingjia(String qingjia) {
        this.qingjia = qingjia;
    }


    public String getKuangong() {
        return kuangong;
    }


    public void setKuangong(String kuangong) {
        this.kuangong = kuangong;
    }


    public String getChidao() {
        return chidao;
    }


    public void setChidao(String chidao) {
        this.chidao = chidao;
    }


    public String getZaotui() {
        return zaotui;
    }


    public void setZaotui(String zaotui) {
        this.zaotui = zaotui;
    }


    public String getChuchai() {
        return chuchai;
    }


    public void setChuchai(String chuchai) {
        this.chuchai = chuchai;
    }


    public String getJiangjin() {
        return jiangjin;
    }


    public void setJiangjin(String jiangjin) {
        this.jiangjin = jiangjin;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getZongji() {
        return zongji;
    }


    public void setZongji(String zongji) {
        this.zongji = zongji;
    }


}
