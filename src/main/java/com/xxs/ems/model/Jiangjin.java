package com.xxs.ems.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//奖金
@Entity
@Table(name = "t_Jiangjin")
@Data
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
}
