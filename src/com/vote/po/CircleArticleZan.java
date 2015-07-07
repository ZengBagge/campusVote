package com.vote.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CircleArticleZan implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	@OneToOne
	private CircleArticle circleArticle;
	@OneToOne
	private UserCommon userCommon;
	
	private Date addDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CircleArticle getCircleArticle() {
		return circleArticle;
	}

	public void setCircleArticle(CircleArticle circleArticle) {
		this.circleArticle = circleArticle;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	
	
}
