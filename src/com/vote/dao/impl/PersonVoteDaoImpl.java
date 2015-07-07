package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.PersonVoteDao;
import com.vote.po.PersonVote;

@Repository("personVoteDao")
public class PersonVoteDaoImpl implements PersonVoteDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public PersonVote get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (PersonVote) session.get(PersonVote.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PersonVote load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (PersonVote) session.load(PersonVote.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(PersonVote entity) throws Exception {
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
	public boolean save(PersonVote entity) throws Exception {
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
	public boolean saveOrUpdate(PersonVote entity) throws Exception {
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
	public boolean delete(PersonVote entity) throws Exception {
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
	public List<PersonVote> getPersonVoteListByProject(int id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From PersonVote p where p.voteProject.id=? order by p.addDate desc");
			query.setInteger(0, id);
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
	public boolean isApplyed(long id,int projectId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From PersonVote p where p.linkUser.id=? and p.voteProject.id=? order by p.addDate desc");
			query.setLong(0, id);
			query.setInteger(1, projectId);
			if( query.list().size()>0)
				return true;
			else {
				return false;
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
