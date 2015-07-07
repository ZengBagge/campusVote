package com.vote.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vote.common.commonUtil;
import com.vote.po.Circle;
import com.vote.po.UserCommon;
import com.vote.service.CircleArticleService;
import com.vote.service.CircleService;

@Controller("discuzForumAction")
@Scope("prototype")
public class DiscuzForumAction extends CommonAction{


	private static final long serialVersionUID = 1L;
	private int circleId;
	private Circle circle;
	@Resource
	private CircleService circleService;
	@Resource 
	private CircleArticleService circleArticleService;
	
	
	public String editArticle() throws Exception{
		if(circleId !=0)
		{
	    circle =circleService.getCircle(circleId);
	    if(circle !=null)
		return "editArticle";
	    else {
			return ERROR;
		}
		}
		else {
			return ERROR;
		}
	}

	public String[] addArticle(String title,String context,int circleId,String[] pic,HttpSession session,HttpServletRequest request) throws Exception{
		String[] result = new String[2];
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null) {
				String ip = request.getRemoteAddr();
				result =circleArticleService.addArticle(title,context,circleId,ip,userCommon,pic);
			}else {
				result[0]="0";
				result[1]="没有登录，权限不能发布主题";
			}
		} catch (Exception e) {
			result[0]="0";
			result[1]="系统错误，请稍后重试";
		}
		return result;
	}
	
	public String[] response(long id,String content,HttpSession session,HttpServletRequest request){
		String[] result = new String[2];
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null) {
				String ip = request.getRemoteAddr();
				result =circleArticleService.addResponse(id,content,ip,userCommon);
			}else {
				result[0]="0";
				result[1]="没有登录，权限不能发布主题";
			}
		} catch (Exception e) {
			result[0]="0";
			result[1]="系统错误，请稍后重试";
		}
		return result;
	}
	
	public boolean zan(long id,HttpSession session) throws Exception{
		
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null) {
				commonUtil.p("进入点赞");
			   return	circleArticleService.addZan(id,userCommon);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean cancleZan(long id,HttpSession session) throws Exception{
		
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null && userCommon.getRule().getSort()>2) {
			   return	circleArticleService.delZan(id,userCommon);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean del(long id,HttpSession session){
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null && userCommon.getRule().getSort()>2) {
			   return	circleArticleService.delete(id);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean top(long id,HttpSession session){
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null && userCommon.getRule().getSort()>2) {
			   return	circleArticleService.setTop(id);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean deltop(long id,HttpSession session){
		try {
			UserCommon userCommon = (UserCommon) session.getAttribute(UserAction.USER_SESSION);
			if (userCommon !=null && userCommon.getRule().getSort()>2) {
			   return	circleArticleService.delTop(id);
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public CircleService getCircleService() {
		return circleService;
	}

	public void setCircleService(CircleService circleService) {
		this.circleService = circleService;
	}

	
}
