package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.JiangjinDao;
import com.xxs.ems.model.Jiangjin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class JiangjinDaoImpl extends HibernateDaoSupport implements JiangjinDao {

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    public void deleteBean(Jiangjin bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(Jiangjin bean) {
        this.getHibernateTemplate().save(bean);

    }


    public Jiangjin selectBean(String where) {

        return (Jiangjin) this.getHibernateTemplate().find("from Jiangjin " + where).get(0);
    }

    public int selectBeanCount(String where) {
        long count = (Long) this.getHibernateTemplate().find("select count(*) from Jiangjin " + where).get(0);
        return (int) count;
    }

    public List<Jiangjin> selectBeanList(final int start, final int limit, final String where) {
        return (List<Jiangjin>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException {
                List<Jiangjin> list = session.createQuery("from Jiangjin " + where).setFirstResult(start)
                        .setMaxResults(limit).list();
                return list;
            }
        });
    }

    public void updateBean(Jiangjin bean) {
        this.getHibernateTemplate().update(bean);

    }

}
