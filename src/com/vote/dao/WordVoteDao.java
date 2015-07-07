package com.vote.dao;

import java.io.Serializable;
import java.util.List;

import com.vote.po.WordVote;

public interface WordVoteDao extends CommonDao<WordVote, Long>{

	/**
	 * 获取投票体列表
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<Serializable> getWordVoteByProject(int projectId)throws Exception;

}
