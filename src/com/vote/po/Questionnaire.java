package com.vote.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Questionnaire implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	
	private int uid;
	
	private String title;
	
	private int type;//0 单选题  1多选题  2填空题  3多选填空题
	
	@OneToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	private List<QuestionnaireOption> questionnaireOptions = new ArrayList<QuestionnaireOption>();
	
	@ManyToOne
	private QuestionnaireProject questionnaireProject;
	
	private int model;//必填，选填
	
	private Date addDate;
	
	private long number;
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<QuestionnaireOption> getQuestionnaireOptions() {
		return questionnaireOptions;
	}
	public void setQuestionnaireOptions(
			List<QuestionnaireOption> questionnaireOptions) {
		this.questionnaireOptions = questionnaireOptions;
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
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}

	
	
}
