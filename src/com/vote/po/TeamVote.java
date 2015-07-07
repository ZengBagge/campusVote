package com.vote.po;

import java.io.Serializable;
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
public class TeamVote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<UserCommon> linkUser;//团队成员

	@ManyToOne
	private VoteProject voteProject;
	
	private String name;
	@OneToOne
	private Vote vote;
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<ImageUrl> picList;//照片地址
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<UserCommon> getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(List<UserCommon> linkUser) {
		this.linkUser = linkUser;
	}

	public VoteProject getVoteProject() {
		return voteProject;
	}

	public void setVoteProject(VoteProject voteProject) {
		this.voteProject = voteProject;
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

	
	
	
}
