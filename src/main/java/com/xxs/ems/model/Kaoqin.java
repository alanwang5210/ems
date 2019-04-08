package com.xxs.ems.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//考勤
@Entity
@Table(name = "t_Kaoqin")
@Data
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
}
