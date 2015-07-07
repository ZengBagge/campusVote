package com.vote.service;

import com.vote.po.UserCommon;

public interface CircleArticleService {

	/**
	 * 发表主题
	 * @param title
	 * @param context
	 * @param circleId
	 * @param ip
	 * @param userCommon
	 * @param pic 
	 * @return
	 * @throws Exception
	 */
	String[] addArticle(String title, String context, int circleId, String ip,
			UserCommon userCommon, String[] pic)throws Exception;

	/**
	 * 回复
	 * @param id
	 * @param content
	 * @param ip
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	String[] addResponse(long id, String content, String ip,
			UserCommon userCommon)throws Exception;

	/**
	 * 点赞
	 * @param id
	 * @param userCommon 
	 * @return
	 */
	boolean addZan(long id, UserCommon userCommon)throws Exception;

	/**
	 * 取消点赞
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delZan(long id, UserCommon userCommon)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(long id)throws Exception;

	/**
	 * 置顶
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean setTop(long id)throws Exception;
	/**
	 * 取消置顶
	 * @param id
	 * @return
	 * @throws Exception
	 */
   boolean delTop(long id) throws Exception;

}
