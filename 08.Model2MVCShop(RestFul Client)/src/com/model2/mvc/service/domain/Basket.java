package com.model2.mvc.service.domain;

import java.sql.Date;

public class Basket {

	private String userId;
	private Product basketProd;
	private Date addDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Product getBasketProd() {
		return basketProd;
	}

	public void setBasketProd(Product basketProd) {
		this.basketProd = basketProd;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Override
	public String toString() {
		return "Basket [userId=" + userId + ", basketProd=" + basketProd + ", addDate=" + addDate + "]";
	}

}
