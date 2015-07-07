package com.vote.service;

import java.util.List;

import com.vote.po.Questionnaire;

public interface QuestionnaireService {

	/**
	 * 添加题目
	 * @param info
	 * @param option
	 * @return
	 * @throws Exception
	 */
	int addQuestionnaire(String[] info, String[] option)throws Exception;

	/**
	 * 添加简答题
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int addQuestionnaire(String[] info)throws Exception;

	/**
	 * 获取题目
	 * @param questionnaireId
	 * @return
	 * @throws Exception
	 */
	Questionnaire getQuestionnaire(long questionnaireId)throws Exception;

	/**
	 * 交换顺序
	 * @param questionnaireID1
	 * @param questionnaireID2
	 * @return
	 * @throws Exception
	 */
	boolean setMoveUid(long questionnaireID1, long questionnaireID2)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(long id)throws Exception;

	/**
	 * 更新
	 * @param info
	 * @param option
	 * @return
	 * @throws Exception
	 */
	int updateQuestionnaire(String[] info, String[] option)throws Exception;

	/**
	 * 更新
	 * @param info
	 * @return
	 * @throws Exception
	 */
	int updateQuestionnaire(String[] info)throws Exception;
	/**
	 * 获取某活动题目
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Questionnaire> getQuestionsByProject(int id)throws Exception;

}
