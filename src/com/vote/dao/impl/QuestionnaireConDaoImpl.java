package com.vote.dao.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.QuestionnaireConDao;
import com.vote.po.QuestionnaireCon;
@Repository("questionnaireConDao")
public class QuestionnaireConDaoImpl implements QuestionnaireConDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public QuestionnaireCon get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireCon) session.get(QuestionnaireCon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public QuestionnaireCon load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireCon) session.load(QuestionnaireCon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(QuestionnaireCon entity) throws Exception {
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
	public boolean save(QuestionnaireCon entity) throws Exception {
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
	public boolean saveOrUpdate(QuestionnaireCon entity) throws Exception {
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
	public boolean delete(QuestionnaireCon entity) throws Exception {
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
	public boolean getQuestionnaireCon(long id, int projectId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From QuestionnaireCon q where q.userCommon=? and q.questionnaireProject.id=?");
			query.setLong(0, id);
			query.setInteger(1, projectId);
			if(query.list() !=null && query.list().size()>0)
				return true;
			else {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean getQuestionnaireCon(String ip, int projectId)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From QuestionnaireCon q where q.ip=? and q.questionnaireProject.id=?");
			query.setString(0, ip);
			query.setInteger(1, projectId);
			if(query.list() !=null && query.list().size()>0)
				return true;
			else {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public BigInteger getpersonNumber(int projectId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SELECT COUNT( * ) FROM  `QuestionnaireCon` WHERE userCommon_id IS NULL and questionnaireProject_id="+projectId);
            return (BigInteger) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BigInteger getAnonymityPersonNumber(int projectId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SELECT COUNT( * ) FROM  `QuestionnaireCon` WHERE userCommon_id IS NOT NULL and questionnaireProject_id="+projectId);
		      return (BigInteger) query.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
   
}
