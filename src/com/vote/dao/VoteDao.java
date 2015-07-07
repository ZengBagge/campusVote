package com.vote.dao;

import java.util.List;
import com.vote.po.Vote;

public interface VoteDao extends CommonDao<Vote, Long>{

	/**
	 * 获取项目所有Vote
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Vote> getVotesByProject(int id)throws Exception;

	
}
