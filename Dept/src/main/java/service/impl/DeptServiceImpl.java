package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.DeptDao;
import dao.impl.DeptDaoImpl;
import dto.Dept;
import service.face.DeptService;

public class DeptServiceImpl implements DeptService {

	//DAO 객체
	private DeptDao deptDao = new DeptDaoImpl();
	
	@Override
	public List<Dept> listAll() {
		// 기능 구현을 위한 구현체 추가해줌
		// 이후에는 기능 구현 및 트랜잭션 관리
		
		//Connection 객체
		Connection conn = JDBCTemplate
		
		
		return null;
	}

}
