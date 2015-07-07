package com.vote.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.dao.BrowsingHistoryDao;
import com.vote.po.BrowsingHistory;
@Component("browsingHistoryDao")
public class BrowsingHistoryDaoImpl implements BrowsingHistoryDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public BrowsingHistory get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (BrowsingHistory) session.get(BrowsingHistory.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BrowsingHistory load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (BrowsingHistory) session.load(BrowsingHistory.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(BrowsingHistory entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean save(BrowsingHistory entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveOrUpdate(BrowsingHistory entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(BrowsingHistory entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public BrowsingHistory getBrowsingHistory(String ip, int pid)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From BrowsingHistory b where b.ip=? and b.projectId=?");
			query.setString(0, ip);
			query.setInteger(1, pid);
			if (query.list()!=null && query.list().size()>0) {
				return (BrowsingHistory) query.list().get(0);
			}
			else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BrowsingHistory getBrowsingHistory(long id, int pid)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From BrowsingHistory b where b.userCommon.id=? and b.projectId=?");
			query.setLong(0, id);
			query.setInteger(1, pid);
			if (query.list()!=null && query.list().size()>0) {
				return (BrowsingHistory) query.list().get(0);
			}
			else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BrowsingHistory> getListByIp(String ip) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From BrowsingHistory b where b.ip=? group by b.projectId order by b.number desc"); //group by 用在 order之前
            query.setString(0, ip);
            query.setFirstResult(0);
            query.setMaxResults(3);
			if (query.list()!=null && query.list().size()>0) {
				return query.list();
			}
			else {
				return null;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
