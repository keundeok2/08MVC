package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(getClass());
	}
	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

//	@Override
//	public Purchase getPurchase2(int prodNo) throws Exception {
//		// TODO Auto-generated method stub
//		return sqlSession.selectOne("PurchaseMapper.getPurchase2", prodNo);
//	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyerId",buyerId);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList",map); 
	}
		
	@Override
	public List<Purchase> getSaleList(Search search) throws Exception {
		return sqlSession.selectList("PurchaseMapper.getSaleList",search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.updateTranCode",purchase);
	}

	@Override
	public int getTotalCountPurchase(String buyerId) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountPurchase", buyerId);
	}

	@Override
	public int getTotalCountSale() throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountSale");
	}
	
	

}
