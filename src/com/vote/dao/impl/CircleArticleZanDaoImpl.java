package com.vote.dao.impl;

import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.vote.dao.CircleArticleZanDao;
import com.vote.po.CircleArticleZan;

@Repository("circleArticleZanDao")
public class CircleArticleZanDaoImpl implements CircleArticleZanDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public CircleArticleZan get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (CircleArticleZan) session.get(CircleArticleZan.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CircleArticleZan load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (CircleArticleZan) session.load(CircleArticleZan.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(CircleArticleZan entity) throws Exception {
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
	public boolean save(CircleArticleZan entity) throws Exception {
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
	public boolean saveOrUpdate(CircleArticleZan entity) throws Exception {
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
	public boolean delete(CircleArticleZan entity) throws Exception {
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

	@Override
	public CircleArticleZan get(long id, long uid) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From CircleArticleZan c where c.userCommon.id=? and c.circleArticle.id=?");
			query.setLong(0, uid);
			query.setLong(1, id);
			if(query !=null &&query.list() != null && query.list().size()>0)
			  return (CircleArticleZan) query.list().get(0);
			else 
				return null;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
