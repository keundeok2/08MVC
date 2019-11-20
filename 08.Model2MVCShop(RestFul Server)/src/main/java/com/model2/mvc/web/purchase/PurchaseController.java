package com.model2.mvc.web.purchase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Point;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.point.PointService;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;
	
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;

	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	public PurchaseController() {
		System.out.println(getClass() + "start...");
	}

//	@RequestMapping(value = "addPurchase", method = RequestMethod.GET)
//	public String addPurchase(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
//
//		Product product = productService.getProduct(prodNo);
//		model.addAttribute("product", product);
//
//		return "forward:/purchase/addPurchaseView.jsp";
//	}

	@RequestMapping(value = "addPurchase", method = RequestMethod.GET)
	public ModelAndView addPurchase(@RequestParam("prodNo") int prodNo, HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		Product product = productService.getProduct(prodNo);
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);
		
		User user = (User)session.getAttribute("user");
		if (user==null) {
			user = new User();
		}
		
		List<Point> list = pointService.getListPoint(user.getUserId());
		Point point = new Point();
		
		if (!list.isEmpty()) {
			point = list.get(0);
		}
		
		modelAndView.addObject("point", point);

		return modelAndView;
	}

//	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
//	public String addPurchase(@ModelAttribute("purchase") Purchase purchase, @RequestParam("buyerId") String buyerId,
//			@RequestParam("prodNo") int prodNo) throws Exception {
//
//		User user = new User();
//		Product product = new Product();
//		user.setUserId(buyerId);
//		product.setProdNo(prodNo);
//
//		purchase.setBuyer(user);
//		purchase.setPurchaseProd(product);
//		purchase.setTranCode("1");
//
//		purchaseService.addPurchase(purchase);
//
//		return "forward:/point/decreasePoint";
//	}

	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase,
			@RequestParam("buyerId") String buyerId, @RequestParam("prodNo") int prodNo) throws Exception {
		
		User user = new User();
		Product product = new Product();
		user.setUserId(buyerId);
		product.setProdNo(prodNo);

		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setTranCode("1");
		purchaseService.addPurchase(purchase);
		
//		장바구니에서 삭제
		Basket basket = new Basket();
		basket.setUserId(buyerId);
		basket.setBasketProd(product);
		basketService.deleteBasket(basket);
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		List<Purchase> list = (List<Purchase>)purchaseService.getPurchaseList(search, user.getUserId()).get("list");
		purchase.setTranNo(list.get(0).getTranNo());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/point/decreasePoint");

		return modelAndView;
	}

//	@RequestMapping("listPurchase")
//	public String listPurchase(@ModelAttribute("search") Search search, HttpSession session, Model model)
//			throws Exception {
//		String buyerId = ((User) session.getAttribute("user")).getUserId();
//
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//
//		return "forward:/purchase/listPurchase.jsp";
//	}

	@RequestMapping("listPurchase")
	public ModelAndView listPurchase(@ModelAttribute("search") Search search, HttpSession session) throws Exception {
		String buyerId = ((User) session.getAttribute("user")).getUserId();

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.setViewName("/purchase/listPurchase.jsp");

		return modelAndView;
	}

//	@RequestMapping("getPurchase")
//	public String getPurchase(@RequestParam("tranNo") int tranNo, Model model) throws Exception {
//		Purchase purchase = purchaseService.getPurchase(tranNo);
//		model.addAttribute("purchase", purchase);
//
//		return "forward:/purchase/getPurchase.jsp";
//	}

	@RequestMapping("getPurchase")
	public ModelAndView getPurchase(@RequestParam("tranNo") int tranNo) throws Exception {
		Purchase purchase = purchaseService.getPurchase(tranNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/getPurchase.jsp");

		return modelAndView;
	}

//	@RequestMapping(value = "updatePurchase", method = RequestMethod.GET)
//	public String updatePurchase(@RequestParam("tranNo") int tranNo, Model model) throws Exception {
//		Purchase purchase = purchaseService.getPurchase(tranNo);
//		model.addAttribute("purchase", purchase);
//
//		return "forward:/purchase/updatePurchaseView.jsp";
//	}

	@RequestMapping(value = "updatePurchase", method = RequestMethod.GET)
	public ModelAndView updatePurchase(@RequestParam("tranNo") int tranNo) throws Exception {
		Purchase purchase = purchaseService.getPurchase(tranNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");

		return modelAndView;
	}

//	@RequestMapping(value = "updatePurchase", method = RequestMethod.POST)
//	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase, Model model) throws Exception {
//
//		purchaseService.updatePurchase(purchase);
//		Purchase newPurchase = purchaseService.getPurchase(purchase.getTranNo());
//		model.addAttribute("purchase", newPurchase);
//
//		return "forward:/purchase/getPurchase.jsp";
//	}

	@RequestMapping(value = "updatePurchase", method = RequestMethod.POST)
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase) throws Exception {

		purchaseService.updatePurchase(purchase);
		Purchase newPurchase = purchaseService.getPurchase(purchase.getTranNo());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", newPurchase);
		modelAndView.setViewName("/purchase/getPurchase.jsp");

		return modelAndView;
	}

//	@RequestMapping("listSale")
//	public String listSale(@ModelAttribute("search") Search search, Model model) throws Exception {
//		if (search.getCurrentPage() == 0) {
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		Map<String, Object> map = purchaseService.getSaleList(search);
//		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
//				pageSize);
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//
//		return "forward:/purchase/listSale.jsp";
//	}

	@RequestMapping("listSale")
	public ModelAndView listSale(@ModelAttribute("search") Search search) throws Exception {
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = purchaseService.getSaleList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.setViewName("/purchase/listSale.jsp");

		return modelAndView;
	}

//	@RequestMapping("updateTranCode")
//	public String updateTranCodeAction(@ModelAttribute("search") Search search, @RequestParam("tranNo") int tranNo,
//			@RequestParam("tranCode") String tranCode, Model model) throws Exception {
//
//		Purchase purchase = purchaseService.getPurchase(tranNo);
//		purchase.setTranCode(tranCode);
//		purchaseService.updateTranCode(purchase);
//
//		if (tranCode.equals("3")) {
////			return "forward:/point/increasePoint?tranNo="+tranNo;  추가기능
//			return "forward:/purchase/listPurchase";
//		} else
//			return "forward:/purchase/listSale";
//	}

	@RequestMapping("updateTranCode")
	public ModelAndView updateTranCodeAction(@ModelAttribute("search") Search search, @RequestParam("tranNo") int tranNo,
			@RequestParam("tranCode") String tranCode) throws Exception {

		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		if (tranCode.equals("3")) {
			modelAndView.setViewName("/point/increasePoint");
//			modelAndView.setViewName("/purchase/listPurchase");
		} else {
			modelAndView.setViewName("/purchase/listSale?currentPage="+search.getCurrentPage());
		}
		
		return modelAndView;
	}
}
