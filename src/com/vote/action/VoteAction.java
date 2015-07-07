package com.vote.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.po.UserCommon;
import com.vote.po.VoteProject;
import com.vote.service.BrowsingHistoryService;
import com.vote.service.VoteProjectService;
import com.vote.service.VoteService;

@Controller("voteAction")
@Scope("prototype")
public class VoteAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Resource
    private VoteService voteService;
    @Resource
    private VoteProjectService voteProjectService;
    @Resource
    private BrowsingHistoryService browsingHistoryService;
	private List<Serializable>users;
	private String projectId;
	private VoteProject project;
	private PageBean pageBeanUnderway;
	private int page = 1;
	private PageBean pageBeanApply;
	private int applyPage=1;
	private List<Serializable>allUser;
	
	/**
	 * 主页
	 * @return
	 * @throws Exception
	 */
	public String index()throws Exception{
		try {
			pageBeanApply = voteProjectService.getApplyProjectList(5,applyPage);
			pageBeanUnderway =voteProjectService.getUnderwayProjectList(5, page);
			return "index";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void getProjectVoteUser(){
		try {
			projectId = (String) request.getAttribute("projectId");
			commonUtil.p("获取的变量"+projectId);
			if(projectId !=null && !"".equals(projectId))
			 {
				int id = Integer.parseInt(projectId);
			    users =voteService.getClassifyVotesUserByProject(id);
			 }else {
				 commonUtil.p("获取参与会员失败");
				users = null;
			}
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}

	/**
	 * 查看报名人员
	 * @return
	 * @throws Exception 
	 */
	public  String allUser() throws Exception{
		try {
			if (projectId !=null && !"".equals(projectId)) {
				int pid = Integer.parseInt(projectId);
				this.allUser = voteService.getClassifyVotesUserByProject(pid);
				return "allUser";	
			}else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String project() throws Exception{
		try {
			if (projectId !=null && !"".equals(projectId)) {
				int pid = Integer.parseInt(projectId);
				project = voteProjectService.getProject(pid);
				String ip = request.getRemoteAddr();
				UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
				if(userCommon !=null)
				   browsingHistoryService.addHistory(ip, pid, userCommon);
				else
				  browsingHistoryService.addHistory(ip, pid);
				if(project.getVoteType().getTypeEntity().equals("PersonVote"))
				  return "project";
				else if(project.getVoteType().getTypeEntity().equals("WordVote"))
				 return "wordProject";
				else {
					return ERROR;
				}
			}else {
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
	}
	public VoteService getVoteService() {
		return voteService;
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	public List<Serializable> getUsers() {
		return users;
	}

	public void setUsers(List<Serializable> users) {
		this.users = users;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public VoteProjectService getVoteProjectService() {
		return voteProjectService;
	}

	public void setVoteProjectService(VoteProjectService voteProjectService) {
		this.voteProjectService = voteProjectService;
	}

	public PageBean getPageBeanUnderway() {
		return pageBeanUnderway;
	}

	public void setPageBeanUnderway(PageBean pageBeanUnderway) {
		this.pageBeanUnderway = pageBeanUnderway;
	}

	public PageBean getPageBeanApply() {
		return pageBeanApply;
	}

	public void setPageBeanApply(PageBean pageBeanApply) {
		this.pageBeanApply = pageBeanApply;
	}

	public int getApplyPage() {
		return applyPage;
	}

	public void setApplyPage(int applyPage) {
		this.applyPage = applyPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Serializable> getAllUser() {
		return allUser;
	}

	public void setAllUser(List<Serializable> allUser) {
		this.allUser = allUser;
	}

	public VoteProject getProject() {
		return project;
	}

	public void setProject(VoteProject project) {
		this.project = project;
	}

	public BrowsingHistoryService getBrowsingHistoryService() {
		return browsingHistoryService;
	}

	public void setBrowsingHistoryService(
			BrowsingHistoryService browsingHistoryService) {
		this.browsingHistoryService = browsingHistoryService;
	}
	
	
	
	
	
}
