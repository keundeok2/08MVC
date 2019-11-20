package com.model2.mvc.service.point.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Point;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.point.PointDao;
import com.model2.mvc.service.point.PointService;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;

@Service("pointServiceImpl")
public class PointServiceImpl implements PointService {

	@Autowired
	@Qualifier("pointDaoImpl")
	private PointDao pointDao;

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;

	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;

	public void setPointDao(PointDao pointDao) {
		this.pointDao = pointDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public PointServiceImpl() {
		System.out.println(getClass().getName());
	}

	@Override
	public void increasePoint(int tranNo) throws Exception {
		Point point = new Point();
		Purchase purchase = purchaseDao.getPurchase(tranNo);
		int price = productDao.getProduct(purchase.getPurchaseProd().getProdNo()).getPrice();
		int purQuantity = purchase.getPurQuantity();
		int changePoint = (int) Math.round(price * purQuantity * 0.03);
		String userId = purchase.getBuyer().getUserId();
		
		int orgPoint = 0;
		if (!((List<Point>) pointDao.getListPoint(userId)).isEmpty()) {
			orgPoint = ((List<Point>) pointDao.getListPoint(userId)).get(0).getCurrPoint();
		}
		point.setUserId(userId);
		point.setCurrPoint(orgPoint + changePoint);
		point.setChangePoint(changePoint);
		point.setTranNo(tranNo);
		
		pointDao.insertPoint(point);
	}

	@Override
	public void decreasePoint(Point point) throws Exception {
		int orgPoint = 0;

		if (!((List<Point>) pointDao.getListPoint(point.getUserId())).isEmpty())
			orgPoint = ((List<Point>) pointDao.getListPoint(point.getUserId())).get(0).getCurrPoint();

		point.setCurrPoint(orgPoint + point.getChangePoint());
		
		pointDao.insertPoint(point);
	}

	@Override
	public List<Point> getListPoint(String userId) throws Exception {
		return pointDao.getListPoint(userId);
	}

}
