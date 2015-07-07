package com.vote.dao;

import java.util.List;

import com.vote.po.CircleType;

public interface CircleTypeDao extends CommonDao<CircleType, Integer>{

	/**
	 * 获取全部分类
	 * @return
	 * @throws Exception
	 */
	List<CircleType> getAllTypes()throws Exception;

	/**
	 * 获取第一个分类
	 * @return
	 * @throws Exception
	 */
	CircleType getFirst()throws Exception;
}
