package com.vote.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.common.HtmlString;
import com.vote.common.commonUtil;
import com.vote.dao.CircleArticleDao;
import com.vote.dao.CircleArticleZanDao;
import com.vote.dao.CircleDao;
import com.vote.po.Circle;
import com.vote.po.CircleArticle;
import com.vote.po.CircleArticleZan;
import com.vote.po.ImageUrl;
import com.vote.po.UserCommon;
import com.vote.service.CircleArticleService;

@Service("circleArticleService")
public class CircleArticleServiceImpl implements CircleArticleService {

	@Resource
	private CircleDao circleDao;
	@Resource
	private CircleArticleDao circleArticleDao;
	
	@Resource
	private CircleArticleZanDao circleArticleZanDao;
	@Override
	public String[] addArticle(String title, String context, int circleId,
			String ip, UserCommon userCommon,String[] pic) throws Exception {
		
		String[] result =new String[2];
		CircleArticle circleArticle = new CircleArticle();
		Circle circle = circleDao.get(circleId);
		if (circle !=null) {
			circleArticle.setCircle(circle);
		}else {
			result[0]="0";
			result[1]="圈子参数错误";
			return result;
		}
		if (title != null && title.length()>2) {
			String tString= commonUtil.trimInnerSpaceStr(title);
			circleArticle.setTitle(tString);
		}else {
			result[0]="0";
			result[1]="标题长度不足";
			return result;
		}
		if (context !=null && context.length()>5) {
			String cString = commonUtil.trimInnerSpaceStr(HtmlString.filterHtml(context));
			circleArticle.setContent(cString);
		}else {
			result[0]="0";
			result[1]="正文长度不足";
			return result;
		}
		if (pic !=null && pic.length>0) {
			List<ImageUrl>imageUrls = new ArrayList<ImageUrl>();
		   for (int i = 0; i < pic.length; i++) {
			ImageUrl imageUrl = new ImageUrl();
			imageUrl.setUrl(pic[i]);
			imageUrl.setName(title);
			imageUrls.add(imageUrl);
		  }
		 circleArticle.setImages(imageUrls);  
		}
		circleArticle.setAddDate(new Date());
		circleArticle.setWriter(userCommon);
		circleArticle.setLevel(0);
		circleArticle.setIp(ip);
		circleArticle.setNewTime("2秒前");
		if (circleArticleDao.save(circleArticle)) {
			result[0]="1";;
		}else {
			result[0]="0";
			result[1]="保存错误";
		}
		return result;
	}

	@Override
	public String[] addResponse(long id, String context, String ip,
			UserCommon userCommon) throws Exception {
		String[] result =new String[2];
		CircleArticle circleArticle = new CircleArticle();
        CircleArticle circleArticle2 = circleArticleDao.get(id);
		if (circleArticle2 !=null) {
			circleArticle.setCircle(circleArticle2.getCircle());
			circleArticle.setFatherArticle(circleArticle2);
			circleArticle2.setResponse(circleArticle2.getResponse()+1);
			circleArticleDao.update(circleArticle2);
		}else {
			result[0]="0";
			result[1]="主题参数错误";
			return result;
		}
		if (context !=null && context.length()>2) {
			String cString = commonUtil.trimInnerSpaceStr(HtmlString.filterHtml(context));
			circleArticle.setContent(cString);
		}else {
			result[0]="0";
			result[1]="正文长度不足";
			return result;
		}
		circleArticle.setAddDate(new Date());
		circleArticle.setWriter(userCommon);
		circleArticle.setLevel(1);
		circleArticle.setIp(ip);
		circleArticle.setNewTime("2秒前");
		if (circleArticleDao.save(circleArticle)) {
			result[0]="1";;
		}else {
			result[0]="0";
			result[1]="保存错误";
		}
		return result;
	}

	@Override
	public boolean addZan(long id, UserCommon userCommon) throws Exception {
		
		CircleArticle circleArticle = circleArticleDao.get(id);
		if (circleArticle !=null) {
			if(circleArticleZanDao.get(id, userCommon.getId())!=null){
				return false;
			}
			circleArticle.setZan(circleArticle.getZan()+1);
			CircleArticleZan circleArticleZan = new CircleArticleZan();
			circleArticleZan.setCircleArticle(circleArticle);
			circleArticleZan.setUserCommon(userCommon);
			circleArticleZan.setAddDate(new Date());
			circleArticleZanDao.save(circleArticleZan);
			return circleArticleDao.update(circleArticle);
		}else
		return false;
	}

	@Override
	public boolean delZan(long id, UserCommon userCommon) throws Exception {
		CircleArticle circleArticle = circleArticleDao.get(id);
		if (circleArticle !=null) {
            CircleArticleZan circleArticleZan =circleArticleZanDao.get(id,userCommon.getId());
			if(circleArticleZan !=null)
			{
				circleArticleZanDao.delete(circleArticleZan);
            	circleArticle.setZan(circleArticle.getZan()-1);
      		    return circleArticleDao.update(circleArticle);
			}else {
				return false;
			}
	
		}else
		return false;
	}

	@Override
	public boolean delete(long id) throws Exception {
		CircleArticle circleArticle = circleArticleDao.get(id);
		if (circleArticle !=null) {
          return    circleArticleDao.delete(circleArticle);
		}else
		return false;
	}

	@Override
	public boolean setTop(long id) throws Exception {
		CircleArticle circleArticle = circleArticleDao.get(id);
		if (circleArticle !=null) {
                  circleArticle.setTop(1);     
              return    circleArticleDao.update(circleArticle);
		}else
		return false;
	}
	
	@Override
	public boolean delTop(long id) throws Exception {
		CircleArticle circleArticle = circleArticleDao.get(id);
		if (circleArticle !=null) {
                  circleArticle.setTop(0);     
              return    circleArticleDao.update(circleArticle);
		}else
		return false;
	}

}
