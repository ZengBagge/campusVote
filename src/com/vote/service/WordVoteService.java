package com.vote.service;

import java.util.List;

import com.vote.po.WordVote;

public interface WordVoteService {

	/**
	 * 添加
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int addWordVote(String[] info)throws Exception;

	/**
	 * 删除
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	boolean delWordVote(long pid)throws Exception;
	
	/**
	 * 添加投票体，成功返回1
	 * @param info
	 * @param pic
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	int updateWordVote(String[] info,long id)throws Exception;
	
	/**
	 * 获取投票对象
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<WordVote> getWordVoteByProject(int projectId)throws Exception;

	/**
	 * 获取对象
	 * @param vid
	 * @return
	 * @throws Exception
	 */
	WordVote getWordVote(long vid)throws Exception;
}
