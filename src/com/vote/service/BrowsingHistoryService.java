package com.vote.service;

import com.vote.po.UserCommon;

public interface BrowsingHistoryService {
	
	/**
	 * 添加
	 * @param ip
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	boolean addHistory(String ip,int pid)throws Exception;
  /**
   * 添加
   * @param ip
   * @param pid
   * @param userCommon
   * @return
   * @throws Exception
   */
	boolean addHistory(String ip,int pid,UserCommon userCommon)throws Exception;
}
