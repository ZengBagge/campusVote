package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.QuestionnaireDao;
import com.vote.po.Questionnaire;
@Repository("questionnaireDao")
public class QuestionnaireDaoImpl implements QuestionnaireDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public Questionnaire get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Questionnaire) session.get(Questionnaire.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Questionnaire load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Questionnaire) session.load(Questionnaire.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Questionnaire entity) throws Exception {
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
	public boolean save(Questionnaire entity) throws Exception {
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
	public boolean saveOrUpdate(Questionnaire entity) throws Exception {
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
	public boolean delete(Questionnaire entity) throws Exception {
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
	public List<Questionnaire> getQuestionsByProject(int projectId)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From Questionnaire q where q.questionnaireProject.id=? order by uid asc");
		    query.setInteger(0, projectId);
           return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
