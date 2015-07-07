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
import javax.persistence.OneToOne;

@Entity
public class CircleArticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long  id;
	private String title;
	private int uid;
	private String content;
	
	private Date addDate;
	
	private String ip;
	private String newTime;//最新回复距离时间
	@ManyToOne
	private CircleArticle fatherArticle;
	@ManyToOne
	private CircleArticle responseArticle;
	
	private long response; //回复次数
	
	private long look; //浏览次数
	@OneToOne
	private UserCommon writer;
	
	private long zan; //赞

	@ManyToOne
	private Circle circle;
	
	private int level;
	
	private int top=0;
	@OneToMany(fetch=FetchType.EAGER,cascade =CascadeType.ALL)
	private List<ImageUrl>images = new ArrayList<ImageUrl>();
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getNewTime() {
		return newTime;
	}

	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}

	public CircleArticle getFatherArticle() {
		return fatherArticle;
	}

	public void setFatherArticle(CircleArticle fatherArticle) {
		this.fatherArticle = fatherArticle;
	}

	public long getResponse() {
		return response;
	}

	public void setResponse(long response) {
		this.response = response;
	}

	public long getLook() {
		return look;
	}

	public void setLook(long look) {
		this.look = look;
	}

	public UserCommon getWriter() {
		return writer;
	}

	public void setWriter(UserCommon writer) {
		this.writer = writer;
	}

	public long getZan() {
		return zan;
	}

	public void setZan(long zan) {
		this.zan = zan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CircleArticle getResponseArticle() {
		return responseArticle;
	}

	public void setResponseArticle(CircleArticle responseArticle) {
		this.responseArticle = responseArticle;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public List<ImageUrl> getImages() {
		return images;
	}

	public void setImages(List<ImageUrl> images) {
		this.images = images;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
	
}
