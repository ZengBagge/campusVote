package com.vote.dao;

import java.util.List;

import com.vote.po.Circle;

public interface CircleDao extends CommonDao<Circle, Integer> {

	/**
	 * 获取分类圈子
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public List<Circle> getCircleByType(int typeId)throws Exception;
	 
	/**
	 * 获取用户收藏的圈子
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public List<Circle> getCircleByUser(long uid)throws Exception;

	/**
	 * 获取第一个分类下属圈子
	 * @return
	 * @throws Exception
	 */
	public List<Circle> getFirstTypeCircles()throws Exception;
	
	
	 
}
