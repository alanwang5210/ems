package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.BumenDao;
import com.xxs.ems.model.Bumen;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("deprecation")
public class BumenDaoImpl extends HibernateDaoSupport implements BumenDao {

    public void deleteBean(Bumen bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(Bumen bean) {
        this.getHibernateTemplate().save(bean);

    }

    @SuppressWarnings("unchecked")
    public Bumen selectBean(String where) {

        return (Bumen) this.getHibernateTemplate().find("from Bumen " + where).get(0);
    }

    public int selectBeanCount(String where) {
        long count = (Long) this.getHibernateTemplate().find("select count(*) from Bumen " + where).get(0);
        return (int) count;
    }

    @SuppressWarnings("unchecked")
    public List<Bumen> selectBeanList(final int start, final int limit, final String where) {
        return (List<Bumen>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                List<Bumen> list = session.createQuery("from Bumen " + where).setFirstResult(start).setMaxResults(limit)
                        .list();
                return list;
            }
        });
    }

    public void updateBean(Bumen bean) {
        this.getHibernateTemplate().update(bean);

    }

}
