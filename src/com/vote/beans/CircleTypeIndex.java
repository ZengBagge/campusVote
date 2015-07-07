package com.vote.beans;

import java.util.List;

import com.vote.po.Circle;
import com.vote.po.CircleType;

public class CircleTypeIndex {

	private List<CircleType> circleTypes;
	
	private int circleTypeId;
	
	private List<Circle>circles;

	public List<CircleType> getCircleTypes() {
		return circleTypes;
	}

	public void setCircleTypes(List<CircleType> circleTypes) {
		this.circleTypes = circleTypes;
	}

	public int getCircleTypeId() {
		return circleTypeId;
	}

	public void setCircleTypeId(int circleTypeId) {
		this.circleTypeId = circleTypeId;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}
	
	
}
