package com.vote.dao;

import java.util.List;

import com.vote.po.VoteType;

public interface VoteTypeDao extends CommonDao<VoteType, Integer> {

	List<VoteType> getList()throws Exception;
	
	

}
