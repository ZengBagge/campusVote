package com.vote.dao;

import java.util.List;

import com.vote.dao.CommonDao;
import com.vote.po.VoteCon;
import com.vote.po.VoteProject;

public interface VoteConDao extends CommonDao<VoteCon, Long> {

	/**
	 * 个人记录数量
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getListNumberByUser(long id)throws Exception;

	/**
	 * 个人记录列表
	 * @param offset
	 * @param length
	 * @param id
	 * @return
	 */
	List<VoteProject> getListByUser(int offset, int length, long id)throws Exception;

	/**
	 * 某活动投票体个人记录
	 * @param uid
	 * @param vid
	 * @return
	 */
	List<VoteCon> getListByUserAndVote(long uid, long vid)throws Exception;

	/**
	 * 某活动个人投票记录
	 * @param uid
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getNumberByUserAndProject(long uid, int id)throws Exception;

}
