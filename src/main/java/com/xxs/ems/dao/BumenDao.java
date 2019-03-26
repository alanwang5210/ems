package com.xxs.ems.dao;

import com.xxs.ems.model.Bumen;

import java.util.List;


public interface BumenDao {


    public void insertBean(Bumen bean);

    public void deleteBean(Bumen bean);

    public void updateBean(Bumen bean);

    public Bumen selectBean(String where);

    public List<Bumen> selectBeanList(final int start, final int limit, final String where);

    public int selectBeanCount(final String where);


}
