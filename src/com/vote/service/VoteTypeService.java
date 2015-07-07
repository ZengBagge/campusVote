package com.vote.service;

import java.util.List;

import com.vote.po.VoteType;

public interface VoteTypeService {

	List<VoteType> getList()throws Exception;

}
