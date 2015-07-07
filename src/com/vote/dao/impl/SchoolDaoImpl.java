package com.vote.dao.impl;


import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.SchoolDao;
import com.vote.po.School;

@Repository("schoolDao")
public class SchoolDaoImpl implements SchoolDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public School get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (School) session.get(School.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public School load(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (School) session.load(School.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(School entity) throws Exception {
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
	public boolean save(School entity) throws Exception {
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
	public boolean saveOrUpdate(School entity) throws Exception {
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
	public boolean delete(School entity) throws Exception {
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
