package com.model2.mvc.service.domain;

import java.sql.Date;

public class Point {

	private String userId;
	private int currPoint;
	private int changePoint;
	private Date changeDate;
	private int tranNo;
	
	public Point() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCurrPoint() {
		return currPoint;
	}

	public void setCurrPoint(int currPoint) {
		this.currPoint = currPoint;
	}

	public int getChangePoint() {
		return changePoint;
	}

	public void setChangePoint(int changePoint) {
		this.changePoint = changePoint;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public int getTranNo() {
		return tranNo;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}

	@Override
	public String toString() {
		return "Point [userId=" + userId + ", currPoint=" + currPoint + ", changePoint=" + changePoint + ", changeDate="
				+ changeDate + ", tranNo=" + tranNo + "]";
	}

}
