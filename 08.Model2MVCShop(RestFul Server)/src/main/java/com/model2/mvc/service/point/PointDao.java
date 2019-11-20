package com.model2.mvc.service.point;

import java.util.List;

import com.model2.mvc.service.domain.Point;

public interface PointDao {

	public void insertPoint(Point point) throws Exception;
	
	public List<Point> getListPoint(String userId) throws Exception;
	
	
}
