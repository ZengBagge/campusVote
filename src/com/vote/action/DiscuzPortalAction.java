package com.vote.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.vote.beans.CircleArticleIndex;
import com.vote.beans.CircleIndex;
import com.vote.beans.CircleTypeIndex;
import com.vote.beans.DiscuzPortal;
import com.vote.common.commonUtil;
import com.vote.po.CircleArticle;
import com.vote.po.UserCommon;
import com.vote.service.DiscuzPortalService;

@Controller("discuzPortalAction")
@Scope("prototype")
public class DiscuzPortalAction extends CommonAction{

 
	private static final long serialVersionUID = 1L;

	private DiscuzPortal discuzPortal;
	private CircleTypeIndex circleTypeIndex;
	private CircleIndex circleIndex;
	private int page=1;
	@Resource
	private DiscuzPortalService discuzPortalService;
	
	private int typeId;

	private int circleId;
	
	private int circleArticleId;
	
	private List<CircleArticle> moreCircleArticle;
	
	private CircleArticleIndex circleArticleIndex;
	@Override
	public String execute() throws Exception {
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		discuzPortal = discuzPortalService.getDiscuzPortal(userCommon);
		return "index";
	}
	
	/**
	 * 分类页面
	 * @return
	 */
	public String typeIndex()throws Exception{
	    if(typeId !=0){
          circleTypeIndex = discuzPortalService.getCircleTypeIndex(typeId);
		  return "type";
	    }else {
	    	circleTypeIndex = discuzPortalService.getCircleTypeIndex();
			  return "type";
		}
	}
	
	public String circleIndex()throws Exception{
		UserCommon userCommon = (UserCommon) session.get(UserAction.USER_SESSION);
		if(circleId !=0){
			circleIndex = discuzPortalService.getCircleIndex(page,10,circleId);
			if (userCommon !=null) {
				circleIndex.setUserCommon(userCommon);
			}
			return "circle";
		}else {
			return ERROR;
		}
		
	}
	
	public String[][]  getMoreCircleArticles(int page,int circleId) throws Exception{
		
		try {
			moreCircleArticle	= discuzPortalService.getCircles(page,10,circleId);
			if (moreCircleArticle !=null) {
				commonUtil.p("获取到更多主题数"+moreCircleArticle.size());
				String[][] result = new String[moreCircleArticle.size()][8];
				int i=0;
				for (CircleArticle circleArticle:moreCircleArticle) {
					result[i][0] = circleArticle.getWriter().getId()+"";
					result[i][1] = circleArticle.getWriter().getPic()+"";
					result[i][2] = circleArticle.getWriter().getName()+"";
					result[i][3] = circleArticle.getId()+"";
					result[i][4] = circleArticle.getTitle()+"";
					result[i][5] = circleArticle.getNewTime()+"";
					result[i][6] = circleArticle.getResponse()+"";
					result[i][7] = circleArticle.getTop()+"";
					i++;
				}
				return result;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String circleArticle()throws Exception{
		if(circleArticleId !=0){
			circleArticleIndex = discuzPortalService.getCircleArticleIndex(circleArticleId);
			return "circleArticle";
		}else {
			return ERROR;
		}
		
	}
	
	public DiscuzPortal getDiscuzPortal() {
		return discuzPortal;
	}
	public void setDiscuzPortal(DiscuzPortal discuzPortal) {
		this.discuzPortal = discuzPortal;
	}
	public DiscuzPortalService getDiscuzPortalService() {
		return discuzPortalService;
	}
	public void setDiscuzPortalService(DiscuzPortalService discuzPortalService) {
		this.discuzPortalService = discuzPortalService;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public CircleTypeIndex getCircleTypeIndex() {
		return circleTypeIndex;
	}

	public void setCircleTypeIndex(CircleTypeIndex circleTypeIndex) {
		this.circleTypeIndex = circleTypeIndex;
	}

	public CircleIndex getCircleIndex() {
		return circleIndex;
	}

	public void setCircleIndex(CircleIndex circleIndex) {
		this.circleIndex = circleIndex;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getCircleArticleId() {
		return circleArticleId;
	}

	public void setCircleArticleId(int circleArticleId) {
		this.circleArticleId = circleArticleId;
	}

	public CircleArticleIndex getCircleArticleIndex() {
		return circleArticleIndex;
	}

	public void setCircleArticleIndex(CircleArticleIndex circleArticleIndex) {
		this.circleArticleIndex = circleArticleIndex;
	}
	
	
}
