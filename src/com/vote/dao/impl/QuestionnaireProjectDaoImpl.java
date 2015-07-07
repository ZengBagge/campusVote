package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.QuestionnaireProjectDao;
import com.vote.po.QuestionnaireProject;
import com.vote.po.VoteProject;
@Repository("questionnaireProjectDao")
public class QuestionnaireProjectDaoImpl implements QuestionnaireProjectDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public QuestionnaireProject get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireProject) session.get(QuestionnaireProject.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public QuestionnaireProject load(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireProject) session.load(QuestionnaireProject.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(QuestionnaireProject entity) throws Exception {
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
	public boolean save(QuestionnaireProject entity) throws Exception {
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
	public boolean saveOrUpdate(QuestionnaireProject entity) throws Exception {
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
	public boolean delete(QuestionnaireProject entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			entity.setBlocQuestionnaire(null);
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
		    Query query =session.createQuery("From QuestionnaireProject v where v.writer.id =? order by addDate desc");
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
	public List<VoteProject> getMyProjectList(int offset, int length, long id)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireProject v where v.writer.id =? order by addDate desc");
		    query.setLong(0, id);
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getAllNumber() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireProject v where v.isOpen=2  order by addDate desc");
		    return query.list().size();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteProject> getAllList(int offset, int length)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireProject v where v.isOpen=2  order by addDate desc");
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
