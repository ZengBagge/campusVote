package com.vote.service;


import java.util.List;

import com.vote.beans.VoteResult;
import com.vote.common.PageBean;
import com.vote.po.UserCommon;
import com.vote.po.VoteProject;

public interface VoteProjectService {

	/**
	 * 添加
	 * @param infos
	 * @param u
	 * @return
	 * @throws Exception
	 */
	String[] addProject(String[] infos,int model,int maxNumber, UserCommon u)throws Exception;
	 /**
	  * 修改
	  * @param infos
	  * @param u
	  * @return
	  * @throws Exception
	  */
	String[] updateProject(String[] infos,int model,int maxNumber, UserCommon u,int id) throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean delete(int id)throws Exception;
	/**
	 * 获取我的项目
	 * @param page
	 * @param i
	 * @param id
	 * @return
	 */
	PageBean getMyProjectList(int page, int i, long id)throws Exception;
	/**
	 * 我的投票项目数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getMyProjectNum(long id)throws Exception;
	/**
	 * 获取项目对象
	 * @param projectId
	 * @return
	 */
	VoteProject getProject(int projectId) throws Exception;
	/**
	 * 开始报名
	 * @param id
	 * @return
	 */
	boolean publish(int id)throws Exception;
	/**
	 * 开始投票
	 * @param id
	 * @return
	 */
	boolean setbeginVote(int id)throws Exception;
	/**
	 * 报名中的投票
	 * @param i
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageBean getApplyProjectList(int i, int page)throws Exception;
	
	/**
	 * 报名中的投票
	 * @param i
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageBean getUnderwayProjectList(int i, int page)throws Exception;
	
	/**
	 * 主页推荐
	 * @return
	 * @throws Exception
	 */
	List<VoteProject> getIndexProjectList(String ip)throws Exception;
	/**
	 * 获取结果
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<VoteResult> getResultByProject(int projectId)throws Exception;
   
}
