package com.vote.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.QuestionnaireAnswerDao;
import com.vote.po.QuestionnaireAnswer;
@Repository("questionnaireAnswerDao")
public class QuestionnaireAnswerDaoImpl implements QuestionnaireAnswerDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public QuestionnaireAnswer get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireAnswer) session.get(QuestionnaireAnswer.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public QuestionnaireAnswer load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (QuestionnaireAnswer) session.load(QuestionnaireAnswer.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(QuestionnaireAnswer entity) throws Exception {
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
	public boolean save(QuestionnaireAnswer entity) throws Exception {
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
	public boolean saveOrUpdate(QuestionnaireAnswer entity) throws Exception {
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
	public boolean delete(QuestionnaireAnswer entity) throws Exception {
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
	public List<QuestionnaireAnswer> getAnswersByCon(long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireAnswer q where q.questionnaireCon.id=? order by q.uid asc");
            return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionnaireAnswer> getNoUpdateAnswers() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireAnswer q where q.isUpdate=0");
            return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BigInteger getAnswerBumber(int projectId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createSQLQuery("select count(*) from QuestionnaireAnswer where questionnaire_id in(select id from Questionnaire where questionnaireProject_id="+projectId+") ");
             return (BigInteger) query.list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionnaireAnswer> getAnswersByQuestionnaire(
			long questionnaireId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
		    Query query =session.createQuery("From QuestionnaireAnswer q where q.questionnaire.id=?");
            query.setLong(0, questionnaireId);
		    return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


}
