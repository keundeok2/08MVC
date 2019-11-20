package com.model2.mvc.web.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.service.coupon.CouponService;
import com.model2.mvc.service.domain.Coupon;

@Controller
@RequestMapping("/coupon/*")
public class CouponController {

	@Autowired
	@Qualifier("couponServiceImpl")
	private CouponService couponService;
	
	public CouponController() {
		System.out.println(getClass().getName() +"call..");
	}
	
	@RequestMapping("createCoupon")
	public ModelAndView createCoupon(@ModelAttribute("coupon") Coupon coupon) {
		
		return null;
	}
}
