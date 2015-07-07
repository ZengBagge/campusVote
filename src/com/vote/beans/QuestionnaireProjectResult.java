package com.vote.beans;

import java.util.List;

import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireProject;

/**
 * 问卷数据总体对象
 * @author bagge
 *
 */
public class QuestionnaireProjectResult {

	private QuestionnaireProject questionnaireProject;
	
	private List<Questionnaire>questionnaires;
	
	private int personNumber;//实名参与人数
	
	private int annoymityPersonNumber;//匿名人数
	
	private int questionnaireNumber;//题目数量
	
	private int beforeAnswerNumber;
	
	private int afterAnswerNumber;

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public int getAnnoymityPersonNumber() {
		return annoymityPersonNumber;
	}

	public void setAnnoymityPersonNumber(int annoymityPersonNumber) {
		this.annoymityPersonNumber = annoymityPersonNumber;
	}

	public int getQuestionnaireNumber() {
		return questionnaireNumber;
	}

	public void setQuestionnaireNumber(int questionnaireNumber) {
		this.questionnaireNumber = questionnaireNumber;
	}

	public int getBeforeAnswerNumber() {
		return beforeAnswerNumber;
	}

	public void setBeforeAnswerNumber(int beforeAnswerNumber) {
		this.beforeAnswerNumber = beforeAnswerNumber;
	}

	public int getAfterAnswerNumber() {
		return afterAnswerNumber;
	}

	public void setAfterAnswerNumber(int afterAnswerNumber) {
		this.afterAnswerNumber = afterAnswerNumber;
	}

	public QuestionnaireProject getQuestionnaireProject() {
		return questionnaireProject;
	}

	public void setQuestionnaireProject(QuestionnaireProject questionnaireProject) {
		this.questionnaireProject = questionnaireProject;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}
	
	
}
