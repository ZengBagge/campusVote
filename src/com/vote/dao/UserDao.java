package com.vote.dao;

import com.vote.po.UserCommon;

public interface UserDao extends CommonDao<UserCommon, Long>{

	/**
	 * 登录
	 * @param uid
	 * @param pwdString
	 * @return
	 * @throws Exception
	 */
	public UserCommon getUser(String uid, String pwdString)throws Exception;



}
