package com.vote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.dao.BrowsingHistoryDao;
import com.vote.po.BrowsingHistory;
import com.vote.po.UserCommon;
import com.vote.service.BrowsingHistoryService;
@Service("broswingHistoryService")
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {

	@Resource
	private BrowsingHistoryDao browsingHistoryDao;
	
	@Override
	public boolean addHistory(String ip, int pid) throws Exception {
		BrowsingHistory b=browsingHistoryDao.getBrowsingHistory(ip,pid);
		if(b != null)
			{
				b.setNumber(b.getNumber()+1);
				 return browsingHistoryDao.update(b);
			}
		BrowsingHistory browsingHistory = new BrowsingHistory();
		browsingHistory.setIp(ip);
		browsingHistory.setProjectId(pid);
		return browsingHistoryDao.save(browsingHistory);
	}
	
	
	public BrowsingHistoryDao getBrowsingHistoryDao() {
		return browsingHistoryDao;
	}
	public void setBrowsingHistoryDao(BrowsingHistoryDao browsingHistoryDao) {
		this.browsingHistoryDao = browsingHistoryDao;
	}


	@Override
	public boolean addHistory(String ip, int pid, UserCommon userCommon)
			throws Exception {
		BrowsingHistory b=browsingHistoryDao.getBrowsingHistory(userCommon.getId(),pid);
		if(b != null)
			{
				b.setNumber(b.getNumber()+1);
				 return browsingHistoryDao.update(b);
			}
		BrowsingHistory browsingHistory = new BrowsingHistory();
		browsingHistory.setIp(ip);
		browsingHistory.setProjectId(pid);
		browsingHistory.setUserCommon(userCommon);
		return browsingHistoryDao.save(browsingHistory);
	}

	
	
}
