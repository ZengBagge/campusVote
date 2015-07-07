package com.vote.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionnaireCon implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	@ManyToOne
	private UserCommon userCommon;
	@ManyToOne
	private QuestionnaireProject questionnaireProject;
	
   private Date addDate;//添加时间
 
   @OneToMany(cascade = CascadeType.ALL)
   private List<QuestionnaireAnswer>answers = new ArrayList<QuestionnaireAnswer>();
 
   private String ip; //ip地址
   
   private int updateOver;
   
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}

	public QuestionnaireProject getQuestionnaireProject() {
		return questionnaireProject;
	}

	public void setQuestionnaireProject(QuestionnaireProject questionnaireProject) {
		this.questionnaireProject = questionnaireProject;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public List<QuestionnaireAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionnaireAnswer> answers) {
		this.answers = answers;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getUpdateOver() {
		return updateOver;
	}

	public void setUpdateOver(int updateOver) {
		this.updateOver = updateOver;
	}

	
	
}
