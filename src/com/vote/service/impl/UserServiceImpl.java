package com.vote.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.commonUtil;
import com.vote.dao.SchoolDao;
import com.vote.dao.UserDao;
import com.vote.dao.UserRuleDao;
import com.vote.po.School;
import com.vote.po.UserCommon;
import com.vote.po.UserRule;
import com.vote.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private UserRuleDao ruleDao;
	@Resource
	private SchoolDao schoolDao;
	
	@Override
	public boolean addUser(String name, String pwd) throws Exception {
		
		UserCommon userCommon = new UserCommon();
		userCommon.setName(name);
		return false;
	}
	@Override
	public UserCommon getUserByPwd(String uid, String pwd) throws Exception {
		try {
			if (uid==null || pwd==null ||uid.equals(" ") || pwd.equals(" ")) {
				return null;
			}
			String pwdString=commonUtil.getMD5Str(commonUtil.getMD5Str(pwd)); //二级加密，算法如下。
			System.out.println("帐号ID为"+uid+"的会员尝试登录"+pwdString);
			UserCommon userCommon=userDao.getUser(uid, pwdString);
			   return userCommon;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void setLoginMessage(String ip, UserCommon userCommon)
			throws Exception {
		userCommon.setLoginIp(ip);
		userCommon.setLogintime(new Date());
		userDao.update(userCommon);
	}
	@Override
	public Boolean setPass(String pwd, String newPwd, String uid)
			throws Exception {
		System.out.print("修改密码");
		UserCommon userCommon = userDao.getUser(uid, commonUtil.getMD5Str(commonUtil.getMD5Str(pwd)));
		if(userCommon !=null)
		{
			 userCommon.setPassword(commonUtil.getMD5Str(commonUtil.getMD5Str(newPwd)));
			 return userDao.update(userCommon);
		}else {
			return false;
		}
		
	}

	@Override
	public UserCommon getUserCommon(long id) throws Exception {
		
	  return userDao.get(id);
	}

	@Override
	public boolean delete(long id) throws Exception {
		UserCommon user = userDao.get(id);
		if (user != null) {
			return userDao.delete(user);	
		}else {
			return false;
		}
		
	}

	@Override
	public boolean setRule(Integer ruleId, Long userId) throws Exception {
		
		UserRule rule = ruleDao.get(ruleId);
		UserCommon uerCommon =userDao.get(userId);
		if (rule != null && uerCommon != null) {
			uerCommon.setRule(rule);
		return 	userDao.update(uerCommon);
		}else {
			return false;
		}
		
	}

	@Override
	public boolean addUser(String uid, String pwd, int schoolId)
			throws Exception {
		String uidString  = commonUtil.trimInnerSpaceStr(uid);
		String password = commonUtil.getMD5Str( commonUtil.getMD5Str(pwd));//两次加密
		School school = schoolDao.get(schoolId);
		if (school !=null) {
			UserCommon userCommon = new UserCommon();
			userCommon.setUid(uidString);
			userCommon.setPassword(password);
			userCommon.setAddDate(new Date());
			userCommon.setSchool(school);
			//临时处理
			UserRule rule = ruleDao.get(1);
			userCommon.setRule(rule);//默认权限
			String pic =(int)(Math.random()*37)+"";//随机头像
			userCommon.setPic(pic);
			return userDao.save(userCommon);	
		}else {
			return false;
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserRuleDao getRuleDao() {
		return ruleDao;
	}
	public void setRuleDao(UserRuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	public SchoolDao getSchoolDao() {
		return schoolDao;
	}
	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}
	

  
	
}
