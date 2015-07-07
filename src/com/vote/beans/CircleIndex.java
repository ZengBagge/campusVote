package com.vote.beans;

import com.vote.common.PageBean;
import com.vote.po.Circle;
import com.vote.po.UserCommon;


public class CircleIndex {
	
	private Circle circle;

	private PageBean pageBean;

	private UserCommon userCommon;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public UserCommon getUserCommon() {
		return userCommon;
	}

	public void setUserCommon(UserCommon userCommon) {
		this.userCommon = userCommon;
	}
	
	
}
