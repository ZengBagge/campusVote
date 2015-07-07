package com.vote.dao;

import java.util.List;

import com.vote.po.BrowsingHistory;

public interface BrowsingHistoryDao extends CommonDao<BrowsingHistory, Long>{

	/**
	 * 获取
	 * @param ip
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	BrowsingHistory getBrowsingHistory(String ip, int pid)throws Exception;

	/**
	 * 获取
	 * @param id
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	BrowsingHistory getBrowsingHistory(long id, int pid)throws Exception;

	/**
	 * 获取IP浏览过的项目
	 * @param ip
	 * @return 
	 * @throws Exception
	 */
	List<BrowsingHistory> getListByIp(String ip)throws Exception;

	
}
