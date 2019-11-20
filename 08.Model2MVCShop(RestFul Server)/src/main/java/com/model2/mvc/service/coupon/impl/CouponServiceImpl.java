package com.model2.mvc.service.coupon.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.coupon.CouponDao;
import com.model2.mvc.service.coupon.CouponService;
import com.model2.mvc.service.domain.Coupon;

@Service("couponServiceImpl")
public class CouponServiceImpl implements CouponService {

	@Autowired
	@Qualifier("couponDaoImpl")
	private CouponDao couponDao;
	
	public void setCouponDao(CouponDao couponDao) {
		this.couponDao = couponDao;
	}
	
	public CouponServiceImpl() {
		System.out.println(getClass().getName() +"start...");
	}
	
	@Override
	public void createCoupon(Coupon coupon) {
		couponDao.createCoupon(coupon);
	}

}
