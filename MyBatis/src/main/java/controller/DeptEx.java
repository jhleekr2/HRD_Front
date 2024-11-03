package controller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.face.DeptDao;
import dto.Dept;
import mybatis.MyBatisConnectionFactory;

public class DeptEx {

	public static void main(String[] args) {
		
		//SqlSession 객체를 생성할 팩토리 객체
		SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
		
		//마이바티스 수행 객체
		SqlSession sqlSession = null;
		
		//DB접속 및 SQL 수행 객체 생성
		sqlSession = sqlSessionFactory.openSession(); //AutoCommit OFF
//		sqlSession = sqlSessionFactory.openSession(true); //AutoCommit ON
//		sqlSession = sqlSessionFactory.openSession(false); //AutoCommit OFF

		//수동 Commit/Rollback
//		sqlSession.commit();
//		sqlSession.rollback();
		
		//-------------------------------------------------------------------
		
		DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		System.out.println("마이바티스 로드 완료!");
		
		//-------------------------------------------------------------------

		List<Dept> list = deptDao.selectAll();
		
		System.out.println("--- 전체 부서 목록 ---");
		for(Dept d : list) System.out.println( d );
		
		//Mybatis를 사용함으로써 세팅은 오래걸리지만 개발은 상당히 편해졌다
		
		//-------------------------------------------------------------------
		
		//-------------------------------------------------------------------
		System.out.println("------------------");
		
		//20번 부서 조회하기
		
		Dept dept = deptDao.selectByDeptno( 20 );
		System.out.println("20번 부서 : " + dept);

		//-------------------------------------------------------------------
		System.out.println("------------------");
		
		System.out.println("\n--- 부서명(String)을 이용한 조회 ---");
		
		String str = "SALES";
		
		Dept dept2 = deptDao.selectByDname( str );
		System.out.println( dept2 );
		
		//-------------------------------------------------------------------
		System.out.println("------------------");
				
		System.out.println("\n--- 부서명(String)을 이용한 조회 ---");		
		
		Dept data = new Dept();
		data.setDname("OPERATIONS");

		Dept dept3 = deptDao.selectByDeptDname( data );
		System.out.println( dept3 );
		
		//-------------------------------------------------------------------
		System.out.println("------------------");
		
		Scanner sc = new Scanner(System.in);
		
		Dept input = new Dept();
		
		System.out.println("부서번호는?");
		input.setDeptno( sc.nextInt() );
		
		System.out.println("부서이름은?");
		sc.nextLine();
		input.setDname( sc.nextLine() );
		
		System.out.println("부서위치는?");
		input.setLoc( sc.nextLine() );
		
		//부서번호로 조회하기 - 중복 데이터 확인
		Dept result = deptDao.selectByDeptno( input.getDeptno() );
		
		if( result == null ) {
			//중복된 데이터 없음
			
			//부서 정보 삽입
			int res = deptDao.insertDept( input );
			
			//트랜잭션 관리
			if( res > 0 ) {
				sqlSession.commit();
				System.out.println("부서번호 등록 성공!");
			} else {
				sqlSession.rollback();
				System.out.println("부서번호 등록 실패!");
			}
		} else {
			//중복된 데이터 있음
			System.out.println("이미 존재하는 부서 번호입니다");
		}
	}
}
