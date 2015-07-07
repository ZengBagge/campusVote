package com.vote.service;

import java.util.List;

import com.vote.beans.CircleArticleIndex;
import com.vote.beans.CircleIndex;
import com.vote.beans.CircleTypeIndex;
import com.vote.beans.DiscuzPortal;
import com.vote.po.CircleArticle;
import com.vote.po.UserCommon;

public interface DiscuzPortalService {

	/**
	 * 圈子主页
	 * @param userCommon
	 * @return
	 * @throws Exception
	 */
	DiscuzPortal getDiscuzPortal(UserCommon userCommon)throws Exception;

	/**
	 * 圈子分类页面
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	 CircleTypeIndex getCircleTypeIndex(int typeId)throws Exception;

	/**
	 * 圈子首页
	 * @param page
	 * @param i
	 * @param circleId
	 * @return
	 * @throws Exception
	 */
	CircleIndex getCircleIndex(int page, int i, int circleId)throws Exception;

	/**
	 * 默认分类主页
	 * @return
	 * @throws Exception
	 */
	CircleTypeIndex getCircleTypeIndex()throws Exception;

	/**
	 * 话题主页
	 * @param circleArticleId
	 * @return
	 * @throws Exception
	 */
	abstract CircleArticleIndex getCircleArticleIndex(long circleArticleId)throws Exception;

	/**
	 * 分页
	 * @param page
	 * @param i
	 * @param circleId
	 * @return
	 * @throws Exception
	 */
	List<CircleArticle> getCircles(int page, int i, int circleId)throws Exception;

}
