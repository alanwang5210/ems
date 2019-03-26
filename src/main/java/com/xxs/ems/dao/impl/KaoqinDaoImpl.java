package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.KaoqinDao;
import com.xxs.ems.model.Kaoqin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

public class KaoqinDaoImpl extends HibernateDaoSupport implements KaoqinDao {

    public void deleteBean(Kaoqin bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(Kaoqin bean) {
        this.getHibernateTemplate().save(bean);

    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public Kaoqin selectBean(String where) {
        return (Kaoqin) this.getHibernateTemplate().find("from Kaoqin " + where).get(0);
    }

    public int selectBeanCount(String where) {
        long count = (Long) this.getHibernateTemplate().find("select count(*) from Kaoqin " + where).get(0);
        return (int) count;
    }

    @SuppressWarnings("unchecked")
    public List<Kaoqin> selectBeanList(final int start, final int limit, final String where) {
        return (List<Kaoqin>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                List<Kaoqin> list = session.createQuery("from Kaoqin " + where).setFirstResult(start)
                        .setMaxResults(limit).list();
                return list;
            }
        });
    }

    public void updateBean(Kaoqin bean) {
        this.getHibernateTemplate().update(bean);

    }

}
