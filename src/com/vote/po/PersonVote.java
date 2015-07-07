package com.vote.po;

import java.io.Serializable;
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
import javax.persistence.OneToOne;

@Entity
public class PersonVote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	@OneToOne(cascade=CascadeType.ALL)
	private Vote vote;
	@OneToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ImageUrl> picList;//照片地址
	
	private String college;//学院
	
	private String motto;//格言
	@OneToOne
	private Article article;//文章
	
	@OneToOne
   private UserCommon linkUser;
	
	@ManyToOne
	private VoteProject voteProject;
	
	private Date addDate;
	
	private int state;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List<ImageUrl> getPicList() {
		return picList;
	}

	public void setPicList(List<ImageUrl> picList) {
		this.picList = picList;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public UserCommon getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(UserCommon linkUser) {
		this.linkUser = linkUser;
	}

	public VoteProject getVoteProject() {
		return voteProject;
	}

	public void setVoteProject(VoteProject voteProject) {
		this.voteProject = voteProject;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
