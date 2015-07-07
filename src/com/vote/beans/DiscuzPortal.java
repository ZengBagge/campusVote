package com.vote.beans;

import java.util.List;

import com.vote.po.Circle;
import com.vote.po.CircleArticle;
import com.vote.po.CircleType;

public class DiscuzPortal {

	private List<CircleType>circleTypes;
	
	private List<Circle>circles;
	
	private List<CircleArticle>hostArticles;
	
	private List<CircleArticle>circleArticles;
	

	public List<CircleType> getCircleTypes() {
		return circleTypes;
	}

	public void setCircleTypes(List<CircleType> circleTypes) {
		this.circleTypes = circleTypes;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}

	public List<CircleArticle> getHostArticles() {
		return hostArticles;
	}

	public void setHostArticles(List<CircleArticle> hostArticles) {
		this.hostArticles = hostArticles;
	}

	public List<CircleArticle> getCircleArticles() {
		return circleArticles;
	}

	public void setCircleArticles(List<CircleArticle> circleArticles) {
		this.circleArticles = circleArticles;
	}
	
	
}
