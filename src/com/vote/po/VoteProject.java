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
import javax.persistence.OneToOne;

/**
 * 投票项目
 * @author bagge
 *
 */
@Entity
public class VoteProject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String toppicture;
	
	private int  weixin;//是否开通微信投票
	
	private String explains; //说明(不能用explain,sql关键词)
    
    private String attention; //注意事项
    
    private String title;  //项目名称
    
    private String organization; //组织名称
    
    private String contact; //联系方式
     @OneToMany(mappedBy="voteProject")
    private List<Vote> blocVotes = new ArrayList<Vote>();//投票体
    @OneToOne
    private VoteType voteType;
    
    private Date beginDate;
    
    private Date endDate;
    
    private Date addDate;
    
    private int  isOpen; //是否允许投票 初始0 开始报名1 开始投票 2
    @ManyToOne
    private UserCommon writer;
    
    private int model; // 0会员只能投一次票，1会员每天投一票，2 全网投票（控制IP，mac）
    
    private int maxNumber; //最大投票总人数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToppicture() {
		return toppicture;
	}
	public void setToppicture(String toppicture) {
		this.toppicture = toppicture;
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
	public List<Vote> getBlocVotes() {
		return blocVotes;
	}
	public void setBlocVotes(List<Vote> blocVotes) {
		this.blocVotes = blocVotes;
	}
	public VoteType getVoteType() {
		return voteType;
	}
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
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
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
    
    
}
