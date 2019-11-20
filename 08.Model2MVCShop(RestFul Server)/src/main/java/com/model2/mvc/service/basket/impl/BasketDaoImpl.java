package com.model2.mvc.service.basket.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.domain.Basket;

@Repository("basketDaoImpl")
public class BasketDaoImpl implements BasketDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public BasketDaoImpl() {
		System.out.println(getClass().getName() + "start...");
	}
	
	@Override
	public void addBasket(Basket basket) throws Exception {
		sqlSession.insert("BasketMapper.addBasket", basket);
	}

	@Override
	public List<Basket> getBasketList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		
		return sqlSession.selectList("BasketMapper.getBasketList", map);
	}

	@Override
	public int deleteBasket(Basket basket) throws Exception {
		
		return sqlSession.delete("BasketMapper.deleteBasket", basket);
	}

	@Override
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("BasketMapper.getTotalCount", userId);
	}

}
