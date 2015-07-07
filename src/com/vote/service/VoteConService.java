package com.vote.service;

import com.vote.common.PageBean;
import com.vote.po.UserCommon;
import com.vote.po.Vote;


/**
 * 投票队列
 * @author bagge
 *
 */
public interface VoteConService {

	/**
	 * 添加记录
	 * @param voteId
	 * @param userCommon
	 * @param projectId
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public boolean addVoteCon(Vote vote,UserCommon userCommon,String ip)throws Exception;
	
	/**
	 * 用户删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean del(long id)throws Exception;
	
	/**
	 * 真正删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delTrue(long id) throws Exception;

	/**
	 * 获取记录
	 * @param id
	 * @param l 
	 * @param pageSize 
	 * @return
	 */
	public PageBean getVoteConListByUser(int page, int pageSize, long id)throws Exception;
	
	/**
	 * 能否投票
	 * @param uid
	 * @param vid
	 * @return
	 * @throws Exception
	 */
	public boolean getAccess(long uid,Vote vote)throws Exception;

	/**
	 * 时间
	 * @param vote
	 * @return
	 * @throws Exception
	 */
	public int getTimeAccess(Vote vote)throws Exception;
	
}
