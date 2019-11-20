package com.model2.mvc.service.coupon.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.coupon.CouponDao;
import com.model2.mvc.service.domain.Coupon;

@Repository("couponDaoImpl")
public class CouponDaoImpl implements CouponDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public CouponDaoImpl() {
		System.out.println(getClass().getName() +"start...");
	}
	
	
	@Override
	public void createCoupon(Coupon coupon) {
		sqlsession.insert("CouponMapper.createCoupon", coupon);
	}

}
