package com.xxs.ems.model;

import javax.persistence.*;
import java.util.Date;

//用户
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private String username;

    private String password;

    private Date createtime;

    private int role;//1表示系统管理员,0表示员工

    private String truename;


    @ManyToOne
    @JoinColumn(name = "bumenid")
    private Bumen bumen;

    private String zhiwu;//普通员工 。部门经理  （部门基本工资+2000）

    private String dizhi;//地址

    private String lianxifangshi;//联系方式

    private String wenhuachengdu;//文化程度

    private String zhengzhimianmao;//政治面貌

    private String jiguan;//籍贯

    private String ruzhishijian;//入职时间

    private String xingbie;//性别

    private String xingxiang;//员工形象

    public String getXingxiang() {
        return xingxiang;
    }

    public void setXingxiang(String xingxiang) {
        this.xingxiang = xingxiang;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Bumen getBumen() {
        return bumen;
    }

    public void setBumen(Bumen bumen) {
        this.bumen = bumen;
    }

    public String getZhiwu() {
        return zhiwu;
    }

    public void setZhiwu(String zhiwu) {
        this.zhiwu = zhiwu;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getLianxifangshi() {
        return lianxifangshi;
    }

    public void setLianxifangshi(String lianxifangshi) {
        this.lianxifangshi = lianxifangshi;
    }

    public String getWenhuachengdu() {
        return wenhuachengdu;
    }

    public void setWenhuachengdu(String wenhuachengdu) {
        this.wenhuachengdu = wenhuachengdu;
    }

    public String getZhengzhimianmao() {
        return zhengzhimianmao;
    }

    public void setZhengzhimianmao(String zhengzhimianmao) {
        this.zhengzhimianmao = zhengzhimianmao;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    public String getRuzhishijian() {
        return ruzhishijian;
    }

    public void setRuzhishijian(String ruzhishijian) {
        this.ruzhishijian = ruzhishijian;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }


}
