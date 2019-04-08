package com.xxs.ems.dao.impl;

import com.xxs.ems.dao.QingjiaDao;
import com.xxs.ems.model.Qingjia;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class QingjiaDaoImpl extends HibernateDaoSupport implements QingjiaDao {

    @Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {
        super.setSessionFactory(sessionFacotry);
    }

    public void deleteBean(Qingjia bean) {
        this.getHibernateTemplate().delete(bean);

    }

    public void insertBean(Qingjia bean) {
        this.getHibernateTemplate().save(bean);

    }

    @SuppressWarnings("unchecked")
    public Qingjia selectBean(String where) {

        return (Qingjia) this.getHibernateTemplate().find("from Qingjia " + where).get(0);
    }

    public int selectBeanCount(String where) {
        long count = (Long) this.getHibernateTemplate().find("select count(*) from Qingjia " + where).get(0);
        return (int) count;
    }

    public List<Qingjia> selectBeanList(final int start, final int limit, final String where) {
        return (List<Qingjia>) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException {
                List<Qingjia> list = session.createQuery("from Qingjia " + where)
                        .setFirstResult(start)
                        .setMaxResults(limit)
                        .list();
                return list;
            }
        });
    }

    public void updateBean(Qingjia bean) {
        this.getHibernateTemplate().update(bean);

    }


}
