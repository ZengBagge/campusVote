package com.vote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.dao.VoteTypeDao;
import com.vote.po.VoteType;
import com.vote.service.VoteTypeService;

@Service("voteTypeService")
public class VoteTypeServiceImpl implements VoteTypeService {

	@Resource
	private VoteTypeDao voteTypeDao;
	@Override
	public List<VoteType> getList() throws Exception {
		return voteTypeDao.getList();
	}

}
