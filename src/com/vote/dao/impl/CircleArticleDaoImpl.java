package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.CircleArticleDao;
import com.vote.po.CircleArticle;
@Repository("circleArticleDao")
public class CircleArticleDaoImpl implements CircleArticleDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public CircleArticle get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (CircleArticle) session.get(CircleArticle.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CircleArticle load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (CircleArticle) session.load(CircleArticle.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(CircleArticle entity) throws Exception {
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
	public boolean save(CircleArticle entity) throws Exception {
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
	public boolean saveOrUpdate(CircleArticle entity) throws Exception {
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
	public boolean delete(CircleArticle entity) throws Exception {
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
	public List<CircleArticle> getCircleNewArticleByUser(long id)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("select * from CircleArticle where level=0 and Circle_id in (select Circle_id from Circle_UserCommon where collectUser_id= "+id+") order by addDate desc limit 0,9").addEntity(CircleArticle.class);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleArticle> getIndexArticle() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("select * from CircleArticle c where c.level=0  order by look,response desc limit 0,3 ").addEntity(CircleArticle.class);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCircleArticleNumberByCircleId(int circleId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(" From CircleArticle c where c.circle.id=? and c.level=0 order by top desc,addDate desc");
			query.setInteger(0, circleId);
			return query.list().size();
		} catch (HibernateException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleArticle> getCircleArticleByCircleId(int offset, int length,
			int circleId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(" From CircleArticle c where c.circle.id=?and c.level=0 order by top desc,addDate desc");
			query.setInteger(0, circleId);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleArticle> getCircleArticleResponse(long id)
			throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(" From CircleArticle c where c.fatherArticle.id=? order by addDate desc");
			query.setLong(0, id);
			 return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
