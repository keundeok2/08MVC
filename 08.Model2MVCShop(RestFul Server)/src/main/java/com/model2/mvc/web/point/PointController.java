package com.model2.mvc.web.point;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.service.domain.Point;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.point.PointService;

@Controller
@RequestMapping("/point/*")
public class PointController {
	
	@Autowired
	@Qualifier("pointServiceImpl")
	PointService pointService;
	
	@RequestMapping("increasePoint")
	public ModelAndView increasePoint(@RequestParam("tranNo") int tranNo) throws Exception{
		pointService.increasePoint(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/listPurchase");
		return modelAndView;
	}
	
	@RequestMapping("decreasePoint")
	public ModelAndView decreasePoint(HttpServletRequest request) throws Exception{
		System.out.println("\t decreasePoint() start...");
		Point point = new Point();
		point.setUserId(request.getParameter("buyerId"));
		point.setChangePoint(-Integer.parseInt(request.getParameter("usePoint")));
		point.setTranNo(((Purchase)request.getAttribute("purchase")).getTranNo());
		pointService.decreasePoint(point);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		return modelAndView;
	}
	
	@RequestMapping("getListPoint")
	public ModelAndView getListPoint(@RequestParam("userId") String userId) throws Exception{
		List<Point> list = pointService.getListPoint(userId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("/point/listPointView.jsp");
		
		return modelAndView;
	}
	
	
	
}
