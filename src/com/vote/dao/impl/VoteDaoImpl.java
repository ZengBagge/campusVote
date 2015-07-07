package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.vote.dao.VoteDao;
import com.vote.po.Vote;

@Repository("voteDao")
public class VoteDaoImpl implements VoteDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public Vote get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Vote) session.get(Vote.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Vote load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Vote) session.load(Vote.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Vote entity) throws Exception {
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
	public boolean save(Vote entity) throws Exception {
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
	public boolean saveOrUpdate(Vote entity) throws Exception {
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
	public boolean delete(Vote entity) throws Exception {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Vote> getVotesByProject(int id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From Vote v where v.voteProject.id=?");
			query.setInteger(0, id);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
