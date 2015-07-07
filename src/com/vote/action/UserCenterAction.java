package com.vote.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.PageBean;
import com.vote.po.UserCommon;
import com.vote.service.UserService;
import com.vote.service.VoteConService;


@Controller("userCenterAction")
@Scope("prototype")
public class UserCenterAction extends CommonAction implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private UserCommon userCommon;	
  @Resource
  private UserService userService;
  @Resource
  private VoteConService voteConService;
  private PageBean pageBean;
  private int page;
   /**
    * 个人中心
    * @return
 * @throws Exception 
    */
   public String myCenter() throws Exception{
	 try {
		userCommon= (UserCommon) session.get(UserAction.USER_SESSION);
		if (userCommon !=null) {
			userCommon = userService.getUserCommon(userCommon.getId());
			return "mycenter";
		}else {
			return "login";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ERROR;
	}
   }
   
   @Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
   /**
    * 投票记录
    * @return
 * @throws Exception 
    */
	public String voteInfo() throws Exception{
		try {
			UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
			if(userCommon == null )
				return LOGIN;
			pageBean = voteConService.getVoteConListByUser(page, 15, userCommon.getId());
			return "voteInfo";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
public UserCommon getUserCommon() {
	return userCommon;
}

public void setUserCommon(UserCommon userCommon) {
	this.userCommon = userCommon;
}

public UserService getUserService() {
	return userService;
}

public void setUserService(UserService userService) {
	this.userService = userService;
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

public Map<String, Object> getSession() {
	return session;
}

public VoteConService getVoteConService() {
	return voteConService;
}

public void setVoteConService(VoteConService voteConService) {
	this.voteConService = voteConService;
}
    
}
