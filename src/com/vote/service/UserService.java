package com.vote.service;

import com.vote.po.UserCommon;

public interface UserService {

	public boolean addUser(String name,String pwd)throws Exception;

	/**
	 * 登录操作
	 * @param uid
	 * @param pwd
	 * @return
	 */
	public UserCommon getUserByPwd(String uid, String pwd)throws Exception;

	/**
	 * 设置登录信息
	 * @param ip
	 * @param userCommon
	 */
	public void setLoginMessage(String ip, UserCommon userCommon)throws Exception;

	/**
	 * 修改密码
	 * @param pwd
	 * @param newPwd
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public Boolean setPass(String pwd, String newPwd, String uid)throws Exception;
	
	/**
	 * 获取用户对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserCommon getUserCommon(long id)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(long id)throws Exception;
	
	/**
	 * 设置权限
	 * @param ruleId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean setRule(Integer ruleId,Long userId)throws Exception;
	/**
	 * 注册
	 * @param uid
	 * @param pwd
	 * @param schoolId
	 * @return
	 * @throws Exception
	 */
	public boolean addUser(String uid,String pwd,int schoolId)throws Exception;
	
	
}
