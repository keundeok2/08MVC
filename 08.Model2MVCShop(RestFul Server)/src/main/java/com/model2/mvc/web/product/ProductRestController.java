package com.model2.mvc.web.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	public ProductRestController() {
		System.out.println(getClass().getName() + "call");
	}
	
	@RequestMapping(value = "json/addProduct", method = RequestMethod.POST)
	public void addProduct(@RequestBody Product product) throws Exception{
		
		productService.addProduct(product);
	}
	
	@RequestMapping(value = "json/getProduct/{prodNo}", method = RequestMethod.GET )
	public Product getProduct(@PathVariable int prodNo) throws Exception{
		
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value = "json/listProduct", method = RequestMethod.GET)
	public Map listProduct() throws Exception{
		Search search = new Search();
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		return map;
	}
	
	
	@RequestMapping(value = "json/listProduct", method = RequestMethod.POST)
	public Map listProduct(@RequestBody Search search) throws Exception{
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		return map;
	}
	
	@RequestMapping(value = "json/updateProduct", method = RequestMethod.POST)
	public void updateProduct(@RequestBody Product product) throws Exception{
		productService.updateProduct(product);
	}
	
	
	
	
	
}
