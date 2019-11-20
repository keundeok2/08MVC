package com.model2.mvc.service.basket.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;

@Service("basketServiceImpl")
public class BasketServiceImpl implements BasketService{

	@Autowired
	@Qualifier("basketDaoImpl")
	private BasketDao basketDao;
	
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
	
	public BasketServiceImpl() {
		System.out.println(getClass().getName() +"start...");
	}
	
	@Override
	public void addBakset(Basket basket) throws Exception {
		basketDao.addBasket(basket);
	}

	@Override
	public Map<String, Object> getBasketList(Search search, String userId) throws Exception {
		List<Basket> list = basketDao.getBasketList(search, userId);
		System.out.println("baksetService basketList.size : " + list.size());
		int totalCount = basketDao.getTotalCount(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}

	@Override
	public void deleteBasket(Basket basket) throws Exception {
		basketDao.deleteBasket(basket);
	}

}
