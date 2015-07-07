package com.vote.dao;

import java.util.List;

import com.vote.po.CircleArticle;

public interface CircleArticleDao extends CommonDao<CircleArticle, Long>{

	/**
	 * 圈子动态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<CircleArticle> getCircleNewArticleByUser(long id)throws Exception;

	/**
	 * 首页热门
	 * @return
	 * @throws Exception
	 */
	List<CircleArticle> getIndexArticle()throws Exception;

	/**
	 * 圈子主题数量
	 * @param circleId
	 * @return
	 * @throws Exception
	 */
	int getCircleArticleNumberByCircleId(int circleId)throws Exception;

	/**
	 * 圈子主题
	 * @param offset
	 * @param length
	 * @param circleId
	 * @return
	 * @throws Exception
	 */
	List<CircleArticle> getCircleArticleByCircleId(int offset, int length,
			int circleId)throws Exception;

	/**
	 * 获取回复
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<CircleArticle> getCircleArticleResponse(long id)throws Exception;

}
