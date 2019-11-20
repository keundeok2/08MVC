package com.model2.mvc.service.point.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Point;
import com.model2.mvc.service.point.PointDao;

@Repository("pointDaoImpl")
public class PointDaoImpl implements PointDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PointDaoImpl() {
		System.out.println(getClass() + "start...");
	}
	
	@Override
	public void insertPoint(Point point) throws Exception {
		sqlSession.insert("PointMapper.insertPoint", point);
	}

	@Override
	public List<Point> getListPoint(String userId) throws Exception {
		return sqlSession.selectList("PointMapper.getListPoint", userId);
	}

}
