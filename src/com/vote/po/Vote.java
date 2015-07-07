package com.vote.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Vote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	
	private Long uid;
	
	private long poll;
	
	private long weiPoll;
	
	@ManyToOne
	private VoteProject voteProject;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public long getPoll() {
		return poll;
	}

	public void setPoll(long poll) {
		this.poll = poll;
	}

	public long getWeiPoll() {
		return weiPoll;
	}

	public void setWeiPoll(long weiPoll) {
		this.weiPoll = weiPoll;
	}

	public VoteProject getVoteProject() {
		return voteProject;
	}

	public void setVoteProject(VoteProject voteProject) {
		this.voteProject = voteProject;
	}
	
	
}
