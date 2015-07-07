package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.VoteConDao;
import com.vote.po.VoteCon;
import com.vote.po.VoteProject;

@Repository("voteConDao")
public class VoteConDaoImpl implements VoteConDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public VoteCon get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteCon) session.get(VoteCon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VoteCon load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteCon) session.load(VoteCon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(VoteCon entity) throws Exception {
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
	public boolean save(VoteCon entity) throws Exception {
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
	public boolean saveOrUpdate(VoteCon entity) throws Exception {
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
	public boolean delete(VoteCon entity) throws Exception {
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
	public int getListNumberByUser(long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
	         Query query=session.createQuery("From VoteCon v where v.userCommon.id=? order by v.addDate desc");         
	         query.setLong(0, id);
	         return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteProject> getListByUser(int offset, int length, long id)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
	         Query query=session.createQuery("From VoteCon v where v.userCommon.id=? order by v.addDate desc");         
	         query.setLong(0, id);
	         return query.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteCon> getListByUserAndVote(long uid, long vid)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
	         Query query=session.createQuery("From VoteCon v where v.userCommon.id=? and v.vote.id=? order by v.addDate desc");         
	         query.setLong(0, uid);
	         query.setLong(1, vid);
	         return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getNumberByUserAndProject(long uid, int id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
	         Query query=session.createQuery("From VoteCon v where v.userCommon.id=? and v.vote.voteProject.id=? order by v.addDate desc");         
	         query.setLong(0, uid);
	         query.setInteger(1, id);
	         if(query.list()!=null)
	         return query.list().size();
	         else {
				return 0;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
