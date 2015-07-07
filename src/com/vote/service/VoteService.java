package com.vote.service;

import java.io.Serializable;
import java.util.List;
import com.vote.po.Vote;

public interface VoteService {

	/**
	 * 获取投票项目的分类投票体
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<Serializable> getClassifyVotesByProject(int projectId)throws Exception;
	
	/**
	 * 获取投票项目的分类投票体会员
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<Serializable> getClassifyVotesUserByProject(int projectId)throws Exception;

	/**
	 * 获取投票体
	 * @param voteId
	 * @return
	 * @throws Exception
	 */
	public Vote getVote(Long voteId)throws Exception;
	
}
