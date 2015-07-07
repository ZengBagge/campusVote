package com.vote.beans;

import java.util.List;

import com.vote.po.Circle;
import com.vote.po.CircleArticle;

public class CircleArticleIndex {

	private List<CircleArticle>response;
	
	private CircleArticle circleArticle;
	
	private Circle circle;

	public List<CircleArticle> getResponse() {
		return response;
	}

	public void setResponse(List<CircleArticle> response) {
		this.response = response;
	}

	public CircleArticle getCircleArticle() {
		return circleArticle;
	}

	public void setCircleArticle(CircleArticle circleArticle) {
		this.circleArticle = circleArticle;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
}
