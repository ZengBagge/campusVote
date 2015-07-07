package com.vote.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.dao.CircleDao;
import com.vote.po.Circle;
import com.vote.service.CircleService;
@Service("circleService")
public class CircleServiceImpl implements CircleService {

	@Resource
	private CircleDao circleDao;
	@Override
	public Circle getCircle(int circleId) throws Exception {
		
		return circleDao.get(circleId);
	}

}
