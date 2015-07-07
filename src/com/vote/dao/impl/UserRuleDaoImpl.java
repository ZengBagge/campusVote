package com.vote.dao.impl;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.UserRuleDao;
import com.vote.po.UserRule;
@Repository("userRuleDao")
public class UserRuleDaoImpl implements UserRuleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public UserRule get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (UserRule) session.get(UserRule.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserRule load(Integer id) throws Exception {
	
		try {
			Session session = sessionFactory.getCurrentSession();
			return (UserRule) session.load(UserRule.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(UserRule entity) throws Exception {
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
	public boolean save(UserRule entity) throws Exception {
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
	public boolean saveOrUpdate(UserRule entity) throws Exception {
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
	public boolean delete(UserRule entity) throws Exception {
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

	
}
