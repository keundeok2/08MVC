package com.model2.mvc.web.basket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/basket/*")
public class BasketController {

	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("addBasket")
	public ModelAndView addBasket(@RequestParam("prodNo") int prodNo ,HttpSession session) throws Exception{
		String userId = ((User)session.getAttribute("user")).getUserId();
		
		Basket basket = new Basket();
		basket.setUserId(userId);
		basket.setBasketProd(productService.getProduct(prodNo));
		
		basketService.addBakset(basket);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/product/listProduct?menu=search");
		return modelAndView;
	}
	
	@RequestMapping("listBasket")
	public ModelAndView listBasket(@ModelAttribute("search") Search search, HttpSession session) throws Exception{
		String userId = ((User)session.getAttribute("user")).getUserId();
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = basketService.getBasketList(search, userId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		modelAndView.setViewName("/basket/listBasket.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("deleteBasket")
	public ModelAndView deleteBasket(@RequestParam("userId") String userId, @RequestParam("prodNo") int prodNo) throws Exception{
		Basket basket = new Basket();
		Product basketProd = new Product();
		basketProd.setProdNo(prodNo);
		basket.setUserId(userId);
		basket.setBasketProd(basketProd);
		basketService.deleteBasket(basket);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/basket/listBasket");
		
		return modelAndView;
	}
	
}
