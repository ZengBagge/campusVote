package com.vote.dao;

import java.math.BigInteger;
import java.util.List;

import com.vote.po.QuestionnaireAnswer;

public interface QuestionnaireAnswerDao extends CommonDao<QuestionnaireAnswer, Long> {

	/**
	 * 获取某问卷答卷答案组
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireAnswer> getAnswersByCon(long id)throws Exception;

	/**
	 * 
	 */
	List<QuestionnaireAnswer> getNoUpdateAnswers()throws Exception;

	/**
	 * 答案数量
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigInteger getAnswerBumber(int projectId)throws Exception;

	/**
	 * 获取某题答案
	 * @param questionnaireId
	 * @return
	 * @throws Exception
	 */
	List<QuestionnaireAnswer> getAnswersByQuestionnaire(long questionnaireId)throws Exception;

}
