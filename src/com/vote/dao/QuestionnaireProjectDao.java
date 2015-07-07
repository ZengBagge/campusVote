package com.vote.dao;

import java.util.List;

import com.vote.po.QuestionnaireProject;
import com.vote.po.VoteProject;

public interface QuestionnaireProjectDao extends CommonDao<QuestionnaireProject, Integer>{

	/**
	 * 我的项目总数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getMyProjectNumber(long id)throws Exception;

	/**
	 * 我的项目
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<VoteProject> getMyProjectList(int offset, int length, long id)throws Exception;

	/**
	 * 全部活动中数量
	 * @return
	 * @throws Exception
	 */
	int getAllNumber()throws Exception;

	/**
	 * 分页列表
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	List<VoteProject> getAllList(int offset, int length)throws Exception;

}
