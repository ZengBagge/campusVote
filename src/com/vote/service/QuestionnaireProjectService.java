package com.vote.service;

import java.util.List;

import com.vote.beans.QuestionnaireProjectResult;
import com.vote.common.PageBean;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireProject;
import com.vote.po.UserCommon;

public interface QuestionnaireProjectService {

	/**
	 * 添加对象
	 * @param infos
	 * @param u
	 * @return
	 * @throws Exception
	 */
	String[] addProject(String[] infos, UserCommon u)throws Exception;

	/**
	 * 获取对象
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	QuestionnaireProject getProject(int projectId)throws Exception;

	/**
	 * 修改
	 * @param infos
	 * @param u
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String[] updateProject(String[] infos, UserCommon u, int id)throws Exception;

	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(int id)throws Exception;

	/**
	 * 获取我的项目
	 * @param page
	 * @param i
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PageBean getMyProjectList(int page, int i, long id)throws Exception;

	/**
	 * 我的项目总数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int getMyProjectNum(long id)throws Exception;

	/**
	 * 开启活动
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean setBeginQuestionnaire(int id)throws Exception;

	/**
	 * 获取题目列表
	 * @param projectId
	 * @return
	 */
	List<Questionnaire> getAllQuestionnairesByProject(int projectId)throws Exception;

	/**
	 * 停止活动
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean setStopQuestionnaire(int id)throws Exception;

	/**
	 * 获取问卷主页活动
	 * @param page 
	 * @param pageSize 
	 * @return
	 * @throws Exception
	 */
	PageBean getIndexProjects(int pageSize, int page)throws Exception;

	/**
	 * 获取活动结果数据
	 * @return
	 * @throws Exception
	 */
	QuestionnaireProjectResult getQuestionnaireProjectResult(int projectId)throws Exception;

}
