package com.vote.dao.impl;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.vote.dao.VoteTypeDao;
import com.vote.po.VoteType;

@Repository("voteTypeDao")
public class VoteTypeDaoImpl  implements VoteTypeDao{

	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public VoteType get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteType) session.get(VoteType.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VoteType load(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteType) session.load(VoteType.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(VoteType entity) throws Exception {
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
	public boolean save(VoteType entity) throws Exception {
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
	public boolean saveOrUpdate(VoteType entity) throws Exception {
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
	public boolean delete(VoteType entity) throws Exception {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteType> getList() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		   Query q=session.createQuery("From VoteType v ");
		   return q.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
