package com.xxs.ems.dao;

import com.xxs.ems.model.Gongzi;

import java.util.List;


public interface GongziDao {


    public void insertBean(Gongzi bean);

    public void deleteBean(Gongzi bean);

    public void updateBean(Gongzi bean);

    public Gongzi selectBean(String where);

    public List<Gongzi> selectBeanList(final int start, final int limit, final String where);

    public int selectBeanCount(final String where);


}
