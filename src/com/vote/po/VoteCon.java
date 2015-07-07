package com.vote.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VoteCon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	@OneToOne
	private UserCommon userCommon;
	@OneToOne
	private Vote vote;
	
	private int zong;
	
	private Date addDate;
	
	private String ip;

	private int state;//处理状态
	
	private String message;
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

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public int getZong() {
		return zong;
	}

	public void setZong(int zong) {
		this.zong = zong;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
