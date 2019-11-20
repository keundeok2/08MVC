package com.model2.mvc.service.basket;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Basket;

public interface BasketDao {
	
	public void addBasket(Basket basket) throws Exception;
	
	public List<Basket> getBasketList(Search search, String userId) throws Exception;
	
	public int deleteBasket(Basket basket) throws Exception;
	
	public int getTotalCount(String userId) throws Exception;
}
