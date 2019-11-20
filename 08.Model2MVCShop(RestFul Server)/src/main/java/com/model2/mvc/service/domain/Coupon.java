package com.model2.mvc.service.domain;

import java.sql.Date;

public class Coupon {

	private int couponNo;
	private String couponName;
	private String discountRate;
	private String couponUserId;
	private int couponTranNo;
	private Date limitDate;
	private Date useDate;
	private String repetition;
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getCouponUserId() {
		return couponUserId;
	}
	public void setCouponUserId(String couponUserId) {
		this.couponUserId = couponUserId;
	}
	public int getCouponTranNo() {
		return couponTranNo;
	}
	public void setCouponTranNo(int couponTranNo) {
		this.couponTranNo = couponTranNo;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getRepetition() {
		return repetition;
	}
	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}
	@Override
	public String toString() {
		return "Coupon [couponNo=" + couponNo + ", couponName=" + couponName + ", discountRate=" + discountRate
				+ ", couponUserId=" + couponUserId + ", couponTranNo=" + couponTranNo + ", limitDate=" + limitDate
				+ ", useDate=" + useDate + ", repetition=" + repetition + "]";
	}
	
}
