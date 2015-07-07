package com.vote.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vote.beans.CircleArticleIndex;
import com.vote.beans.CircleIndex;
import com.vote.beans.CircleTypeIndex;
import com.vote.beans.DiscuzPortal;
import com.vote.common.DateUtil;
import com.vote.common.PageBean;
import com.vote.common.commonUtil;
import com.vote.dao.CircleArticleDao;
import com.vote.dao.CircleDao;
import com.vote.dao.CircleTypeDao;
import com.vote.po.Circle;
import com.vote.po.CircleArticle;
import com.vote.po.CircleType;
import com.vote.po.UserCommon;
import com.vote.service.DiscuzPortalService;
@Service("discuzPortalService")
public class DiscuzPortalServiceImpl implements DiscuzPortalService {

	@Resource
	private CircleDao circleDao;
	@Resource
	private CircleTypeDao circleTypeDao;
	@Resource
	private CircleArticleDao circleArticleDao;
	
	@Override
	public DiscuzPortal getDiscuzPortal(UserCommon userCommon) throws Exception {
		
		DiscuzPortal discuzPortal = new DiscuzPortal();
		List<CircleType> circleTypes = circleTypeDao.getAllTypes();
		discuzPortal.setCircleTypes(circleTypes);
		if (userCommon !=null) {
			List<Circle>circles = circleDao.getCircleByUser(userCommon.getId());
			discuzPortal.setCircles(circles);
			List<CircleArticle>circleArticles = circleArticleDao.getCircleNewArticleByUser(userCommon.getId());
			DateUtil.dateCha(circleArticles);
			discuzPortal.setCircleArticles(circleArticles);
		}else {
			List<Circle>circles = circleDao.getFirstTypeCircles();
			discuzPortal.setCircles(circles);
			discuzPortal.setCircleArticles(null);
		}
		List<CircleArticle>indexArticles = circleArticleDao.getIndexArticle();
		DateUtil.dateCha(indexArticles);
		discuzPortal.setHostArticles(indexArticles);
		return discuzPortal;
   }

	@Override
	public CircleTypeIndex getCircleTypeIndex(int typeId) throws Exception {
		
		try {
			CircleTypeIndex circleTypeIndex = new CircleTypeIndex();
			CircleType circleType = circleTypeDao.get(typeId);
			if (circleType !=null) {
				circleTypeIndex.setCircleTypeId(circleType.getId());
				List<CircleType>circleTypes = circleTypeDao.getAllTypes();
				circleTypeIndex.setCircleTypes(circleTypes);
				List<Circle>circles = circleDao.getCircleByType(circleType.getId());
				circleTypeIndex.setCircles(circles);
				commonUtil.p("本分类下有"+circles.size());
				return circleTypeIndex;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
  }

	@Override
	public CircleIndex getCircleIndex(int page, int pageSize, int circleId)
			throws Exception {
		try {
			Circle circle = circleDao.get(circleId);
			if(circle !=null){
				CircleIndex circleIndex =new CircleIndex();
				circleIndex.setCircle(circle);
				int allRow=circleArticleDao.getCircleArticleNumberByCircleId(circleId);  //获取总行数
				int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
				final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
	            final int length = pageSize;//每页记录数
	            final int currentPage = PageBean.countCurrentPage(page);
	            List<CircleArticle> circleArticles =circleArticleDao.getCircleArticleByCircleId(offset, length,circleId);
	            DateUtil.dateCha(circleArticles);
	            PageBean pageBean = new PageBean();
				pageBean.setPageSize(pageSize);
				pageBean.setCurrentPage(currentPage);
				pageBean.setAllRow(allRow);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(circleArticles);
				pageBean.init();
				circleIndex.setPageBean(pageBean);
				return circleIndex;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CircleTypeIndex getCircleTypeIndex() throws Exception {
		try {
			CircleTypeIndex circleTypeIndex = new CircleTypeIndex();
			CircleType circleType = circleTypeDao.getFirst();
			if (circleType !=null) {
				circleTypeIndex.setCircleTypeId(circleType.getId());
				List<CircleType>circleTypes = circleTypeDao.getAllTypes();
				circleTypeIndex.setCircleTypes(circleTypes);
				List<Circle>circles = circleDao.getFirstTypeCircles();
				circleTypeIndex.setCircles(circles);
				return circleTypeIndex;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CircleArticleIndex getCircleArticleIndex(long circleArticleId)
			throws Exception {
		CircleArticleIndex circleArticleIndex = null;
	    try {
			 circleArticleIndex = new CircleArticleIndex();
			 CircleArticle circleArticle = circleArticleDao.get(circleArticleId);
			 if (circleArticle !=null) {
				circleArticleIndex.setCircleArticle(circleArticle);
				circleArticleIndex.setCircle(circleArticle.getCircle());
				List<CircleArticle>response = circleArticleDao.getCircleArticleResponse(circleArticle.getId());
				DateUtil.dateCha(response);
				int i=1;
				for (CircleArticle c:response) {
				   c.setUid(i);
				   i++;
				}
				circleArticleIndex.setResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			circleArticleIndex = null;
		}
		return circleArticleIndex;
	}

	@SuppressWarnings("unused")
	@Override
	public List<CircleArticle> getCircles(int page, int pageSize, int circleId)
			throws Exception {
		
		try {
			int allRow=circleArticleDao.getCircleArticleNumberByCircleId(circleId);  //获取总行数
			int totalPage=PageBean.countTotalPage(pageSize, allRow);  //设置总页数
			final int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
			final int length = pageSize;//每页记录数
			final int currentPage = PageBean.countCurrentPage(page);
			List<CircleArticle> circleArticles =circleArticleDao.getCircleArticleByCircleId(offset, length,circleId);
			DateUtil.dateCha(circleArticles);
            return circleArticles;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}	