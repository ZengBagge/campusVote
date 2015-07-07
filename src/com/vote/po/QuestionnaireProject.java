package com.vote.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class QuestionnaireProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String topPicture;
	
	private int  weixin;//是否开通微信问卷
	
    private String explains; //说明(不能用explain,sql关键词)
    
    private String attention; //注意事项
    
    private String title;  //项目名称
    
    private String organization; //组织名称
    
    private String contact; //联系方式
     @OneToMany(mappedBy ="questionnaireProject")
    private List<Questionnaire> blocQuestionnaire = new ArrayList<Questionnaire>();//调查体

    private Date beginDate;
    
    private Date endDate;
    
    private Date addDate;
    
    private int  isOpen; //状态标志

    @ManyToOne
    private UserCommon writer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopPicture() {
		return topPicture;
	}

	public void setTopPicture(String topPicture) {
		this.topPicture = topPicture;
	}

	public int getWeixin() {
		return weixin;
	}

	public void setWeixin(int weixin) {
		this.weixin = weixin;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Questionnaire> getBlocQuestionnaire() {
		return blocQuestionnaire;
	}

	public void setBlocQuestionnaire(List<Questionnaire> blocQuestionnaire) {
		this.blocQuestionnaire = blocQuestionnaire;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public UserCommon getWriter() {
		return writer;
	}

	public void setWriter(UserCommon writer) {
		this.writer = writer;
	}

    
    
}
