package com.vote.action;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.vote.po.QuestionnaireAnswer;

@Controller("questionnaireAnswerAction")
public class QuestionnaireAnswerAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<QuestionnaireAnswer>answers;
	
	public void getType2QuestionnaireAnswer(){
		
	}
	
	public void getType3QuestionnaireAnswer() {
		
	}

	public List<QuestionnaireAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionnaireAnswer> answers) {
		this.answers = answers;
	}
	
	
}
