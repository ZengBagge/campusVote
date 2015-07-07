package com.vote.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.vote.po.QuestionnaireProject;
import com.vote.po.VoteProject;
import com.vote.service.QuestionnaireProjectService;
import com.vote.service.UserService;
import com.vote.service.VoteProjectService;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	@Resource
	private VoteProjectService voteProjectService;
	@Resource
	private QuestionnaireProjectService questionnaireProjectService;

	private List<VoteProject>voteList;
   private List< QuestionnaireProject>questionnaireList;
	public VoteProjectService getVoteProjectService() {
		return voteProjectService;
	}

	public void setVoteProjectService(VoteProjectService voteProjectService) {
		this.voteProjectService = voteProjectService;
	}

	public QuestionnaireProjectService getQuestionnaireProjectService() {
		return questionnaireProjectService;
	}

	public void setQuestionnaireProjectService(
			QuestionnaireProjectService questionnaireProjectService) {
		this.questionnaireProjectService = questionnaireProjectService;
	}
	
		public List<VoteProject> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<VoteProject> voteList) {
		this.voteList = voteList;
	}

	public List<QuestionnaireProject> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<QuestionnaireProject> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

		/**
		 * 主页控制
		 */
	public  String execute() throws Exception {
		String ip = request.getRemoteAddr();
		voteList = voteProjectService.getIndexProjectList(ip);
        return "index";
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
