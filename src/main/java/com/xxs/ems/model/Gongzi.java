package com.xxs.ems.model;


import lombok.Data;

import javax.persistence.*;

//工资
@Entity
@Table(name = "t_Gongzi")
@Data
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
}
