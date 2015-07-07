package com.vote.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * 答卷题目答案
 * @author bagge
 *
 */
@Entity
public class QuestionnaireAnswer implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	private int uid;
	@ManyToOne
	private UserCommon userCommon;
	@ManyToOne
	private Questionnaire questionnaire;
	
	@ManyToOne
	private QuestionnaireCon questionnaireCon;
	
	private String value;
	
	private String message; //其他信息
	
	private int type;//答案类型
	
	private int isUpdate;
	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public QuestionnaireCon getQuestionnaireCon() {
		return questionnaireCon;
	}

	public void setQuestionnaireCon(QuestionnaireCon questionnaireCon) {
		this.questionnaireCon = questionnaireCon;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
