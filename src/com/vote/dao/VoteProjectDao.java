package com.vote.dao;

import java.util.List;

import com.vote.dao.CommonDao;
import com.vote.po.VoteProject;

public interface VoteProjectDao extends CommonDao<VoteProject, Integer> {

	/**
	 * 获取我的项目数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getMyProjectNumber(long id)throws Exception;

	/**
	 * 获取我的项目列表
	 * @param offset
	 * @param length
	 * @return
	 */
	List<VoteProject> getMyProjectList(int offset, int length,long id)throws Exception;

	/**
	 * 
	 * @param id
	 */
	boolean publish(int id)throws Exception;

	/**
	 * 报名中活动
	 * @return
	 * @throws Exception
	 */
	int getApplyProjectNumber()throws Exception;

	/**
	 * 报名中活动列表
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	List<VoteProject> getApplyProjectList(int offset, int length)throws Exception;
/**
 * 进行中活动
 * @return
 * @throws Exception
 */
	int getUnderwayProjectNumber()throws Exception;
/**
 * 进行中活动列表
 * @param offset
 * @param length
 * @return
 * @throws Exception
 */
List<VoteProject> getUnderwayProjectList(int offset, int length)throws Exception;

/**
 * 最新活动推荐
 * @param m
 * @return
 * @throws Exception
 */
List<VoteProject> getNewIndexList(int m)throws Exception;
	
	

}
