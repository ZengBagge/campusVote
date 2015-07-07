package com.vote.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireProject;
import com.vote.po.UserCommon;
import com.vote.service.QuestionnaireConService;
import com.vote.service.QuestionnaireProjectService;
import com.vote.service.QuestionnaireService;

@Component("questionnaireAction")
@Scope("prototype")
public class QuestionnaireAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<QuestionnaireProject>list;
    private PageBean pageBean;
    private int page;
	@Resource
	private QuestionnaireProjectService questionnaireProjectService;
	@Resource
	private QuestionnaireService questionnaireService;
	@Resource
	private QuestionnaireConService questionnaireConService;
	private int projectId;
	private QuestionnaireProject project;
	private List<Questionnaire>questionnaires;
	
	public String index(){
		return "index";
	}

	public void getIndexProject() throws Exception{
		this.pageBean = questionnaireProjectService.getIndexProjects(10,page);;
	}

	public String project() throws Exception{
		if(projectId !=0){
			 project = questionnaireProjectService.getProject(projectId);	
			 if(project !=null)
			 {
				 questionnaires = questionnaireService.getQuestionsByProject(project.getId());
				 return "project";
			 }
		   else {
				return ERROR;
			}
		}
			 else {
				return ERROR;
			}
		
	}
	
	public int resultUpload(String[] result,int projectId,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		if(isAccess(projectId, session, request)){
	    try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			String ip = request.getRemoteAddr();
			if (userCommon !=null) {
			  int re= questionnaireConService.addQuestionnaireCon(result,userCommon,ip);
			  if (re==2) { //cookie存入客户客户端
				  String name = "questionnaireHistory"+projectId;
				Cookie cookie = new Cookie(name, projectId+"");
				cookie.setMaxAge(2592000);//保存一个月
				response.addCookie(cookie);
			 }
			  return re;
			}
			else {
			  return  questionnaireConService.addQuestionnaireCon(result,ip);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	  }else {
		return -1;//无权限
	 } 
	}
	
	public boolean isAccess(int projectId,HttpSession session,HttpServletRequest request) throws Exception{
		UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
		String ip = request.getRemoteAddr(); 
		Cookie[] cookies = request.getCookies();
		  String name = "questionnaireHistory"+projectId;
		if (cookies !=null) {
			for (Cookie c:cookies) {
				 if (name.equals(c.getName())) {
					 commonUtil.p("cookie检查不通过");
					 return false;
				 }
			}
		}
	return questionnaireConService.getIsAccess(userCommon,ip,projectId);
	}
	public List<QuestionnaireProject> getList() {
		return list;
	}

	public void setList(List<QuestionnaireProject> list) {
		this.list = list;
	}

	public QuestionnaireProjectService getQuestionnaireProjectService() {
		return questionnaireProjectService;
	}

	public void setQuestionnaireProjectService(
			QuestionnaireProjectService questionnaireProjectService) {
		this.questionnaireProjectService = questionnaireProjectService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public QuestionnaireProject getProject() {
		return project;
	}

	public void setProject(QuestionnaireProject project) {
		this.project = project;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public QuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	public QuestionnaireConService getQuestionnaireConService() {
		return questionnaireConService;
	}

	public void setQuestionnaireConService(
			QuestionnaireConService questionnaireConService) {
		this.questionnaireConService = questionnaireConService;
	}
	
	
}
