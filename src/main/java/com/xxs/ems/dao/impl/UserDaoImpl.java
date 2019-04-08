package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.UserDao;
import com.xxs.ems.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    public void deleteBean(User bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(User bean) {
        this.getHibernateTemplate().save(bean);
    }

    public User selectBean(String sql) {

        List<User> list = (List<User>) this.getHibernateTemplate().find(sql);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public int selectBeanCount(String where) {

        long count = (Long) this.getHibernateTemplate().find("select count(*) from User " + where).get(0);
        return (int) count;
    }

    public List<User> selectBeanList(final int start, final int limit, final String where) {


        return (List<User>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException {
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
