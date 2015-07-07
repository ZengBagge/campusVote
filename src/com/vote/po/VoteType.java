package com.vote.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VoteType  implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String typeEntity;//类型实现类

	private String typeDao;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeEntity() {
		return typeEntity;
	}

	public void setTypeEntity(String typeEntity) {
		this.typeEntity = typeEntity;
	}

	public String getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(String typeDao) {
		this.typeDao = typeDao;
	}


	
	
}
