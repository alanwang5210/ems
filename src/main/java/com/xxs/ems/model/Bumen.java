package com.xxs.ems.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//部门
@Entity
@Table(name = "t_Bumen")
public class Bumen {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private String name;//部门名称

    private String jibengongzi;//基本工资

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJibengongzi() {
        return jibengongzi;
    }

    public void setJibengongzi(String jibengongzi) {
        this.jibengongzi = jibengongzi;
    }


}
