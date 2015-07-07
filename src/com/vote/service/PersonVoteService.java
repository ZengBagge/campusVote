package com.vote.service;


import java.util.List;

import com.vote.po.PersonVote;
import com.vote.po.UserCommon;

public interface PersonVoteService {

	/**
	 * 添加投票体，成功返回1
	 * @param info
	 * @param pic
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	int addPersonVote(String[] info, String pic[], UserCommon userCommon)throws Exception;

	/**
	 * 删除
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	boolean delPersonVote(long pid)throws Exception;
	
	/**
	 * 添加投票体，成功返回1
	 * @param info
	 * @param pic
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	int updatePersonVote(String[] info, String pic[], UserCommon userCommon,long id)throws Exception;
	
	/**
	 * 获取投票对象
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<PersonVote> getPersonVoteByProject(int projectId)throws Exception;

	
	
}
