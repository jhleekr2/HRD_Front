package controller;

import java.util.List;

import dao.face.EmpDao;
import dao.impl.EmpDaoImpl;
import dto.Emp;

public class EmpEx {

	//EmpDao 객체 생성
	private static EmpDao empDao = new EmpDaoImpl();//null
	
	public static void main(String[] args) {
		
		//DAO 객체를 이용하여 EMP테이블의 전체 행 조회하기
		List<Emp> list = empDao.selectAll();
		
		System.out.println("전체 조회결과");
		for( Emp e : list)
			System.out.println( e );
		
		System.out.println("--------------------------");
	}
}
