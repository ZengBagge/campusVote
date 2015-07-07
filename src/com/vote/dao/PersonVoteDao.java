package com.vote.dao;

import java.util.List;

import com.vote.po.PersonVote;

public interface PersonVoteDao extends CommonDao<PersonVote, Long>{

	/**
	 * 获取投票体
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<PersonVote> getPersonVoteListByProject(int id)throws Exception;

	boolean isApplyed(long id,int projectId)throws Exception;

}
