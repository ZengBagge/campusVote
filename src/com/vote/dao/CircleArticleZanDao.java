package com.vote.dao;

import com.vote.po.CircleArticleZan;

public interface CircleArticleZanDao extends CommonDao<CircleArticleZan, Long>{
	/**
	 * 删除
	 * @param id
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public CircleArticleZan get(long id,long uid)throws Exception;

}
