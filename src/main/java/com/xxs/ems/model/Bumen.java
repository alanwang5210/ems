package com.xxs.ems.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//部门
@Entity
@Table(name = "t_Bumen")
@Data
public class Bumen {

    @Id
    @GeneratedValue
    private int id;

    private int deletestatus;//表示是否删除的状态，0表示未删除，1表示删除

    private String name;//部门名称

    private String jibengongzi;//基本工资

}
