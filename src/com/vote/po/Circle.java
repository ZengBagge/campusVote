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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * 圈子
 * @author bagge
 *
 */
@Entity
public class Circle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int  id;
	
	@ManyToMany
	private List<UserCommon>collectUser = new ArrayList<UserCommon>();
	
    private String address;
    @OneToOne
    private UserCommon manager;  //创建者
    
    private String shortMessage;
    
    private String pic;
    
    @ManyToOne(cascade =CascadeType.ALL)
    private CircleType type;
    
    private int common;//是否公开
    
    private int state;
    
    private int host;
    
    private Date addDate;
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserCommon getManager() {
		return manager;
	}

	public void setManager(UserCommon manager) {
		this.manager = manager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public CircleType getType() {
		return type;
	}

	public void setType(CircleType type) {
		this.type = type;
	}

	public List<UserCommon> getCollectUser() {
		return collectUser;
	}

	public void setCollectUser(List<UserCommon> collectUser) {
		this.collectUser = collectUser;
	}

	public int getCommon() {
		return common;
	}

	public void setCommon(int common) {
		this.common = common;
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

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}
    
    
}
