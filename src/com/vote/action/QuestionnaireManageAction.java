package com.vote.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.beans.QuestionnaireProjectResult;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.po.Questionnaire;
import com.vote.po.QuestionnaireProject;
import com.vote.po.UserCommon;
import com.vote.service.QuestionnaireProjectService;
import com.vote.service.QuestionnaireService;
import com.vote.service.UserService;

@Controller("questionnaireManageAction")
@Scope("prototype")
public class QuestionnaireManageAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Resource
		private UserService userService;
	   @Resource
	   private QuestionnaireProjectService questionnairProjectService;
	   @Resource
	   private QuestionnaireService questionnaireService;
	   private int projectId;
	   private QuestionnaireProject questionnaireProject;
	   private PageBean pageBean;
	   private int page;
	   private int projectNum;
	   private List<Questionnaire>allQuestionnaires;
	   private long questionnaireId;
	   private Questionnaire questionnaire;
	   private QuestionnaireProjectResult questionnaireProjectResult;
	   
	   
	   
		public String addQuestionnaireProject(){
		
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			if (userCommon.getRule().getSort()>2 || userCommon.getPoint()>100) {  //权限大于2或者积分大于100
				return "addQuestionnaireProject";
			}else {
				return ERROR;
			}
		}
		/**
		 * 添加投票项目
		 * @param infos
		 * @return
		 * @throws Exception
		 */
		public String[]  addProject(String[] infos,HttpSession session)throws Exception{
			String[] result =new String[10];
			try {
			UserCommon u=	(UserCommon)session.getAttribute(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
				result =questionnairProjectService.addProject(infos,u);	
			}else {
				result[8]="无权限，确认是否登录";
				result[9]="0";
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result[8]="系统错误";
				result[9]="0";
			}
		  return result;
		}
		
		
		/**
		 * 修改投票项目
		 * @param infos
		 * @return
		 * @throws Exception
		 */
		public String editQuestionnaire() throws Exception{
			if(projectId !=0)
			{
				questionnaireProject = questionnairProjectService.getProject(projectId);
				return "edit";
			}else {
				return ERROR;
			}
		}
		
		public String[]  updateProject(String[] infos,int id,HttpSession session)throws Exception{
			String[] result =new String[10];
			try {
				UserCommon u=	(UserCommon)session.getAttribute(UserAction.USER_SESSION);
				if (u !=null && (u.getRule().getSort()>2 || u.getPoint()>100) ) {
					result =questionnairProjectService.updateProject(infos,u,id);	
				}else {
					result[8]="无权限，确认是否登录";
					result[9]="0";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result[8]="系统错误";
				result[9]="0";
			}
		  return result;
		}	
		
		public boolean deleteProject(int id,HttpSession session){
			try {
			UserCommon u=	(UserCommon)session.getAttribute(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
				return questionnairProjectService.delete(id);
			}else {
			       return false;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	          return false;
			}
			
		}
		
		public String getMyProject() throws Exception{
			try {
				UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
				if (userCommon !=null) {
					this.pageBean = questionnairProjectService.getMyProjectList(page,5,userCommon.getId());
					return "myproject";
				}else {
					return LOGIN;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			}
		}
		public void getMyProjectNum() throws Exception{
			try {
				UserCommon userCommon =(UserCommon) session.get(UserAction.USER_SESSION);
				if (userCommon !=null) {
				projectNum = questionnairProjectService.getMyProjectNum(userCommon.getId());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				projectNum = 0;
			}
		}
		public String allQuestionnaire() throws Exception{
			if(projectId !=0){
			UserCommon u=	(UserCommon)session.get(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
				allQuestionnaires = questionnairProjectService.getAllQuestionnairesByProject(projectId);
				return "allQuestion";
			}
			else {
				return LOGIN;
			}
			}else {
				return ERROR;
			}
		}
		
		public String apply(){
			UserCommon u=	(UserCommon)session.get(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
				return "apply";
			}
			else {
				return LOGIN;
			}
		}
		
		public int addQuestionnaire(String[] info,String option[],HttpSession session) throws Exception{
			
			try {
				UserCommon userCommon = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
				if (userCommon != null) {
					return questionnaireService.addQuestionnaire(info,option);
				}else {
					return 0;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
           public int addQuestionnaireTwo(String[] info,HttpSession session) throws Exception{
			commonUtil.p("添加简答题");
			try {
				UserCommon userCommon = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
				if (userCommon != null) {
					return questionnaireService.addQuestionnaire(info);
				}else {
					return 0;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
           public int updateQuestionnaire(String[] info,String option[],HttpSession session) throws Exception{
   			
   			try {
   				UserCommon userCommon = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
   				if (userCommon != null) {
   					return questionnaireService.updateQuestionnaire(info,option);
   				}else {
   					return 0;
   				}
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   				return 0;
   			}
   		}
              public int updateQuestionnaireTwo(String[] info,HttpSession session) throws Exception{
   			commonUtil.p("添加简答题");
   			try {
   				UserCommon userCommon = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
   				if (userCommon != null) {
   					return questionnaireService.updateQuestionnaire(info);
   				}else {
   					return 0;
   				}
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   				return 0;
   			}
   		}
		public boolean publishTrue(int id) throws Exception{
			try {
				return 	questionnairProjectService.setBeginQuestionnaire(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		public boolean stop(int id)throws Exception{
			try {
				return 	questionnairProjectService.setStopQuestionnaire(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
		}
		public boolean move(long questionnaireID1,long questionnaireID2){
			
			try {
				return questionnaireService.setMoveUid(questionnaireID1,questionnaireID2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		public String edit(){
			
			try {
				if (questionnaireId !=0) {
				  questionnaire = questionnaireService.getQuestionnaire(questionnaireId);	
				  if(questionnaire!=null)
				  return "edit";
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
		public boolean del(long id){
		
			try {
				return questionnaireService.delete(id);
			} catch (Exception e) {
				return false;
			}
		}
		
		public String projectResult() throws Exception{
			if(projectId !=0){
				questionnaireProjectResult =questionnairProjectService.getQuestionnaireProjectResult(projectId);
				return "projectResult";
			}
			else {
				return ERROR;
			}
		}
		public UserService getUserService() {
			return userService;
		}
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		public QuestionnaireProjectService getQuestionnairProjectService() {
			return questionnairProjectService;
		}
		public void setQuestionnairProjectService(
				QuestionnaireProjectService questionnairProjectService) {
			this.questionnairProjectService = questionnairProjectService;
		}
		public int getProjectId() {
			return projectId;
		}
		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
		public QuestionnaireProject getQuestionnaireProject() {
			return questionnaireProject;
		}
		public void setQuestionnaireProject(QuestionnaireProject questionnaireProject) {
			this.questionnaireProject = questionnaireProject;
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
		public int getProjectNum() {
			return projectNum;
		}
		public void setProjectNum(int projectNum) {
			this.projectNum = projectNum;
		}
		public List<Questionnaire> getAllQuestionnaires() {
			return allQuestionnaires;
		}
		public void setAllQuestionnaires(List<Questionnaire> allQuestionnaires) {
			this.allQuestionnaires = allQuestionnaires;
		}
		public QuestionnaireService getQuestionnaireService() {
			return questionnaireService;
		}
		public void setQuestionnaireService(QuestionnaireService questionnaireService) {
			this.questionnaireService = questionnaireService;
		}
		public long getQuestionnaireId() {
			return questionnaireId;
		}
		public void setQuestionnaireId(long questionnaireId) {
			this.questionnaireId = questionnaireId;
		}
		public Questionnaire getQuestionnaire() {
			return questionnaire;
		}
		public void setQuestionnaire(Questionnaire questionnaire) {
			this.questionnaire = questionnaire;
		}
		public QuestionnaireProjectResult getQuestionnaireProjectResult() {
			return questionnaireProjectResult;
		}
		public void setQuestionnaireProjectResult(
				QuestionnaireProjectResult questionnaireProjectResult) {
			this.questionnaireProjectResult = questionnaireProjectResult;
		}
		
	
		
}
