package com.vote.dao;

import java.math.BigInteger;

import com.vote.po.QuestionnaireCon;

public interface QuestionnaireConDao extends CommonDao<QuestionnaireCon, Long>{

	/**
	 * 验证
	 * @param id
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	boolean getQuestionnaireCon(long id, int projectId)throws Exception;

	/**
	 * 验证
	 * @param ip
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	boolean getQuestionnaireCon(String ip, int projectId)throws Exception;


	/**
	 * 实名人数
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigInteger getpersonNumber(int projectId)throws Exception;

	/**
	 * 匿名人数
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigInteger getAnonymityPersonNumber(int projectId)throws Exception;

}
