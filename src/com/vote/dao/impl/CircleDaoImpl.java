package com.vote.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.dao.CircleDao;
import com.vote.po.Circle;
@Repository("circleDao")
public class CircleDaoImpl implements CircleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public Circle get(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Circle) session.get(Circle.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Circle load(Integer id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Circle) session.load(Circle.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Circle entity) throws Exception {
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
	public boolean save(Circle entity) throws Exception {
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
	public boolean saveOrUpdate(Circle entity) throws Exception {
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
	public boolean delete(Circle entity) throws Exception {
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
	public List<Circle> getCircleByType(int typeId) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From Circle c where c.type.id=? and c.state=1 order by c.host,c.addDate desc");
			query.setInteger(0, typeId);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circle> getCircleByUser(long uid) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
            Query query=session.createSQLQuery("select * from Circle where id in (select Circle_id from Circle_UserCommon where collectUser_id= "+uid+")  order by host desc limit 0,5")
			.addEntity(Circle.class);
             return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circle> getFirstTypeCircles() throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
            Query query=session.createSQLQuery("select * from Circle where type_id in (select min(id) from CircleType order by sortId asc  )  order by host desc limit 0,9")
			.addEntity(Circle.class);
             return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
