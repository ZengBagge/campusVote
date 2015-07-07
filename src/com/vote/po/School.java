package com.vote.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
@Entity
public class School implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String adress;
	
	private String title;

	@OneToMany(mappedBy="school")
	@OrderBy("sort asc")
	private List<UserRule> rules = new ArrayList<UserRule>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserRule> getRules() {
		return rules;
	}

	public void setRules(List<UserRule> rules) {
		this.rules = rules;
	}

	
}
