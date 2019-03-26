package com.xxs.ems.dao;

import com.xxs.ems.model.Kaoqin;

import java.util.List;


public interface KaoqinDao {


    public void insertBean(Kaoqin bean);

    public void deleteBean(Kaoqin bean);

    public void updateBean(Kaoqin bean);

    public Kaoqin selectBean(String where);

    public List<Kaoqin> selectBeanList(final int start, final int limit, final String where);

    public int selectBeanCount(final String where);


}
