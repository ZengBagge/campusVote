package com.vote.dao;

import java.util.List;

import com.vote.po.Questionnaire;

public interface QuestionnaireDao extends CommonDao<Questionnaire, Long>{

	/**
	 * 获取某活动所有题目
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<Questionnaire> getQuestionsByProject(int projectId)throws Exception;

}
