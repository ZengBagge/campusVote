package com.vote.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.beans.VoteResult;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.po.PersonVote;
import com.vote.po.UserCommon;
import com.vote.po.VoteProject;
import com.vote.po.WordVote;
import com.vote.service.PersonVoteService;
import com.vote.service.UserService;
import com.vote.service.VoteProjectService;
import com.vote.service.VoteService;
import com.vote.service.WordVoteService;

@Controller("voteManageAction")
@Scope("prototype")
public class VoteManageAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   @Resource
	private UserService userService;
   @Resource
   private VoteProjectService voteProjectService;
   @Resource
   private PersonVoteService personVoteService;
   @Resource
   private VoteService voteService;
   @Resource
   private WordVoteService wordVoteService;
   private Map<String, Object> session;
   private PageBean pageBean;
   private int  page = 1;  
   private int projectNum;
   private int projectId;
   private VoteProject voteProject;
   private List<VoteResult> voteResults;
   /**
    * 某活动报名会员
    */
   private List<Serializable>allUser;
   private WordVote wordVote;
   private PersonVote personVote;
  private long vid;
   
   
	public String addVoteProject(){
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if (userCommon.getRule().getSort()>2 || userCommon.getPoint()>100) {  //权限大于2或者积分大于100
			return "addVoteProject";
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
	public String[]  addProject(String[] infos,String mo,String max,HttpSession session)throws Exception{
		String[] result =new String[10];
		try {
			int model = Integer.parseInt(mo);
			int maxNumber = Integer.parseInt(max);
		commonUtil.p("控票模式"+model+"最大投票量"+maxNumber);
		UserCommon u=	(UserCommon)session.getAttribute(UserAction.USER_SESSION);
		if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
			result =voteProjectService.addProject(infos, model, maxNumber,u);	
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
	public String editVote() throws Exception{
		if(projectId !=0)
		{
			voteProject = voteProjectService.getProject(projectId);
			return "edit";
		}else {
			return ERROR;
		}
	}
	
	public String[]  updateProject(String[] infos,int id,int model,int maxNumber,HttpSession session)throws Exception{
		String[] result =new String[10];
		try {
			UserCommon u=	(UserCommon)session.getAttribute(UserAction.USER_SESSION);
			if (u !=null && (u.getRule().getSort()>2 || u.getPoint()>100) ) {
				result =voteProjectService.updateProject(infos,model, maxNumber,u,id);	
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
			return voteProjectService.delete(id);
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
				this.pageBean = voteProjectService.getMyProjectList(page,5,userCommon.getId());
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
			projectNum = voteProjectService.getMyProjectNum(userCommon.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			projectNum = 0;
		}
	}
	
	/**
	 * 报名允许设置
	 * @param id
	 * @return
	 */
	public boolean  publish(int id)throws Exception{
		
		try {
			return 	voteProjectService.publish(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean publishTrue(int id) throws Exception{
		try {
			return 	voteProjectService.setbeginVote(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 报名页面
	 * @return
	 * @throws Exception 
	 */
	public String apply() throws Exception{
		
		VoteProject voteProject=voteProjectService.getProject(projectId);
		if(voteProject !=null){
			if(voteProject.getVoteType().getTypeEntity().equals("PersonVote"))
		        return "apply";
			else if (voteProject.getVoteType().getTypeEntity().equals("PictureVote")) {
			   return "pictureApply";
			}
			else if (voteProject.getVoteType().getTypeEntity().equals("TeamVote")) {
				  return "teamApply";
			}
			else if(voteProject.getVoteType().getTypeEntity().equals("WordVote")) {
				  return "wordApply";
			}else {
				return ERROR;
			}
		}else {
			return ERROR;
		}
	}
	
	/**
	 * 报名页面
	 * @return
	 * @throws Exception 
	 */
	public String applyEdit() throws Exception{
		
		VoteProject voteProject=voteProjectService.getProject(projectId);
		if(voteProject !=null){
			if(voteProject.getVoteType().getTypeEntity().equals("PersonVote"))
			{
		        return "apply";
			}    
			else if (voteProject.getVoteType().getTypeEntity().equals("PictureVote")) {
				{
				return "pictureApply";
			
				}
			}	
			else if (voteProject.getVoteType().getTypeEntity().equals("TeamVote")) {
				{
				  return "teamApply";
				} 
			}
			else if(voteProject.getVoteType().getTypeEntity().equals("WordVote")) {
				{  
				this.wordVote= wordVoteService.getWordVote(vid);	
				return "wordApply";
				}
			}else {
				return ERROR;
			}
		}else {
			return ERROR;
		}
	}
	public int addPersonVote(String[] info,String pic[],HttpSession session) throws Exception{
		
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon != null) {
				return personVoteService.addPersonVote(info,pic,userCommon);
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addWordVote(String[] info,HttpSession session) throws Exception{
		
		try {
			UserCommon u = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100)) {
				return wordVoteService.addWordVote(info);
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
public int updateWordVote(String[] info,Long id,HttpSession session) throws Exception{
		
		try {
			UserCommon u = (UserCommon) session . getAttribute(UserAction.USER_SESSION);
			if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100)) {
				return wordVoteService.updateWordVote(info, id);
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 查看报名人员
	 * @return
	 * @throws Exception 
	 */
	public  String allUser() throws Exception{
		UserCommon u=	(UserCommon)session.get(UserAction.USER_SESSION);
		if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
		if (projectId !=0) {
			this.allUser = voteService.getClassifyVotesUserByProject(projectId);
			this.voteProject = voteProjectService.getProject(projectId);
			return "allUser";	
		}else {
			return ERROR;
		}
		}else {
			return LOGIN;
		}	
	}
	
	/**
	 * 数据分析
	 * @return
	 * @throws Exception 
	 */
	public String projectResult() throws Exception{
		UserCommon u=	(UserCommon)session.get(UserAction.USER_SESSION);
		if (u !=null &&( u.getRule().getSort()>2 || u.getPoint()>100) ) {
		
			try {
				if (projectId !=0) {
				 this.voteResults = voteProjectService.getResultByProject(projectId);
				 return "result";
                    }  else {
				return ERROR;
                  }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			}
		
		}else {
			return LOGIN;
		}
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
				
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public VoteProjectService getVoteProjectService() {
		return voteProjectService;
	}

	public void setVoteProjectService(VoteProjectService voteProjectService) {
		this.voteProjectService = voteProjectService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Map<String, Object> getSession() {
		return session;
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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public VoteProject getVoteProject() {
		return voteProject;
	}

	public void setVoteProject(VoteProject voteProject) {
		this.voteProject = voteProject;
	}

	public PersonVoteService getPersonVoteService() {
		return personVoteService;
	}

	public void setPersonVoteService(PersonVoteService personVoteService) {
		this.personVoteService = personVoteService;
	}

	public List<Serializable> getAllUser() {
		return allUser;
	}

	public void setAllUser(List<Serializable> allUser) {
		this.allUser = allUser;
	}

	public VoteService getVoteService() {
		return voteService;
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	public WordVoteService getWordVoteService() {
		return wordVoteService;
	}

	public void setWordVoteService(WordVoteService wordVoteService) {
		this.wordVoteService = wordVoteService;
	}

	public WordVote getWordVote() {
		return wordVote;
	}

	public void setWordVote(WordVote wordVote) {
		this.wordVote = wordVote;
	}

	public PersonVote getPersonVote() {
		return personVote;
	}

	public void setPersonVote(PersonVote personVote) {
		this.personVote = personVote;
	}

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public List<VoteResult> getVoteResults() {
		return voteResults;
	}

	public void setVoteResults(List<VoteResult> voteResults) {
		this.voteResults = voteResults;
	}


	
}
