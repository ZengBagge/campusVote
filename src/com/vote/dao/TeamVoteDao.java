package com.vote.dao;

import java.util.List;

import com.vote.dao.CommonDao;
import com.vote.po.TeamVote;

public interface TeamVoteDao extends CommonDao<TeamVote, Long> {

	/**
	 * 获取项目投票体列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<TeamVote> getTeamVoteListByProject(int id)throws Exception;

}
