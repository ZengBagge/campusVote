package com.vote.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.po.UserCommon;
import com.vote.po.Vote;
import com.vote.service.VoteConService;
import com.vote.service.VoteService;

@Controller("voteConAction")
@Scope("prototype")
public class VoteConAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private VoteConService voteConService;
	@Resource
	private VoteService voteService;
	private PageBean pageBean;
	private int page=1;
	public int toupiao(Long voteId,HttpSession session,HttpServletRequest request) throws Exception{
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			String ip = request.getRemoteAddr();
			commonUtil.p("投票IP"+ip);
			if (userCommon !=null) {
				Vote vote = voteService.getVote(voteId);
				if (vote == null) {
					return -3; //参数错误
				}
				int re=voteConService.getTimeAccess(vote);	
				if(re<0){
					return re; //时间错误
				}
				if (voteConService.getAccess(userCommon.getId(), vote)) {
					if(voteConService.addVoteCon(vote, userCommon, ip))
						return 3;//成功
					else {
						return 0;//系统错误
					}
				}else {
					return 2;//已经投过票了
				}
			
			}
			else {
				return 1;//没有登录
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
  public String getVoteConList()throws Exception{
	  
	  try {
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if (userCommon !=null) {
			  pageBean = voteConService.getVoteConListByUser(page,10,userCommon.getId());
			  return "list";	
		}
		else {
			return LOGIN;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ERROR;
	}
  }
	
	public VoteConService getVoteConService() {
		return voteConService;
	}
	public void setVoteConService(VoteConService voteConService) {
		this.voteConService = voteConService;
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


	
}
