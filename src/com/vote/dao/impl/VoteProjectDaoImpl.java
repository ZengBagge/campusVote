package com.vote.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.VoteProjectDao;
import com.vote.po.VoteProject;

@Repository("voteProjectDao")
public class VoteProjectDaoImpl implements VoteProjectDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public VoteProject get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteProject) session.get(VoteProject.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VoteProject load(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (VoteProject) session.load(VoteProject.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(VoteProject entity) throws Exception {
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
	public boolean save(VoteProject entity) throws Exception {
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
	public boolean saveOrUpdate(VoteProject entity) throws Exception {
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
	public boolean delete(VoteProject entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			entity.setBlocVotes(null);
		     session.delete(entity);
		     return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getMyProjectNumber(long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.writer.id =? order by addDate desc");
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
	public List<VoteProject> getMyProjectList(int offset, int length,long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.writer.id =? order by addDate desc");
		    query.setLong(0, id);
		    query.setFirstResult(offset);
		    query.setMaxResults(length);
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

	@Override
	public boolean publish(int id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			VoteProject voteProject =(VoteProject) session.get(VoteProject.class, id);
			voteProject.setIsOpen(1);
			session.update(voteProject);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public int getApplyProjectNumber() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.isOpen=1 order by addDate desc");
		    return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteProject> getApplyProjectList(int offset, int length)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.isOpen=1 order by addDate desc");
		    query.setFirstResult(offset);
		    query.setMaxResults(length);
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getUnderwayProjectNumber() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.isOpen =2 and v.endDate< ? order by addDate desc");
		    query.setDate(0, new Date());
		    return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteProject> getUnderwayProjectList(int offset, int length)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.isOpen =2  order by addDate desc");
		    query.setFirstResult(offset);
		    query.setMaxResults(length);
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteProject> getNewIndexList(int m) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From VoteProject v where v.isOpen =2  order by addDate desc");
		    query.setFirstResult(0);
		    query.setMaxResults(m-1);
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
