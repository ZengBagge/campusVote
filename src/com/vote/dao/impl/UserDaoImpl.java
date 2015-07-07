package com.vote.dao.impl;



import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vote.common.commonUtil;
import com.vote.dao.UserDao;
import com.vote.po.UserCommon;

@Repository("userDao")
public class UserDaoImpl  implements UserDao{

    @Resource
    private SessionFactory sessionFactory;
    

	@Override
	public UserCommon getUser(String uid, String pwdString) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createQuery(" From UserCommon u where u.uid=? and u.password=?");
			 q.setString(0, uid);
			 q.setString(1, pwdString);
			 if (q.list().size()>0) {
				 UserCommon userCommon = (UserCommon) q.list().get(0);
				 return userCommon;	
			}else {
				return null;
			}
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
	public UserCommon get(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (UserCommon) session.get(UserCommon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserCommon load(Long id) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (UserCommon) session.load(UserCommon.class, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(UserCommon entity) throws Exception {
		try {
			Session session = sessionFactory.getCurrentSession();
			 session.update(entity);
			 commonUtil.p("用户信息更新成功");
			 return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean save(UserCommon entity) throws Exception {
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
	public boolean saveOrUpdate(UserCommon entity) throws Exception {
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
	public boolean delete(UserCommon entity) throws Exception {
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


	
}
