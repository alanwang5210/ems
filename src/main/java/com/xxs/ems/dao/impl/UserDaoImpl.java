package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.UserDao;
import com.xxs.ems.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {



    public void deleteBean(User bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(User bean) {
        this.getHibernateTemplate().save(bean);

    }

    @SuppressWarnings("unchecked")
    public User selectBean(String where) {


        return (User) this.getHibernateTemplate().find("from User " + where).get(0);
    }

    public int selectBeanCount(String where) {
        long count = (Long) this.getHibernateTemplate().find("select count(*) from User " + where).get(0);
        return (int) count;
    }

    @SuppressWarnings("unchecked")
    public List<User> selectBeanList(final int start, final int limit, final String where) {
        return (List<User>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
                List<User> list = session.createQuery("from User " + where).setFirstResult(start).setMaxResults(limit)
                        .list();
                return list;
            }
        });
    }

    public void updateBean(User bean) {
        this.getHibernateTemplate().update(bean);

    }

}
