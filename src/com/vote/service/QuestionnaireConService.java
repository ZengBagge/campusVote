package com.vote.service;

import com.vote.po.UserCommon;

public interface QuestionnaireConService {

	/**
	 * 添加
	 * @param result
	 * @throws Exception
	 */
	int addQuestionnaireCon(String[] result, String ip)throws Exception;

	/**
	 * 实名添加
	 * @param result
	 * @param userCommon
	 * @param ip 
	 * @return
	 * @throws Exception
	 */
	int addQuestionnaireCon(String[] result, UserCommon userCommon, String ip)throws Exception;

	/**
	 * 是否有权限
	 * @param userCommon
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	boolean getIsAccess(UserCommon userCommon, String ip,int projectId)throws Exception;

	/**
	 * 更新问卷选项的支持度
	 * @throws Exception
	 */
	void startUpdateQuestionnaireOption()throws Exception;

}
