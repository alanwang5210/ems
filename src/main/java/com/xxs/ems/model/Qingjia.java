package com.xxs.ems.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//请假
@Entity
@Table(name = "t_Qingjia")
@Data
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
}
