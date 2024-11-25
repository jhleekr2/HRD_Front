package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.face.EmpDao;
import dto.Emp;
import mybatis.MyBatisConnectionFactory;
import service.face.EmpService;

public class EmpServiceImpl implements EmpService{

	//마이바티스 연결 객체
	//JDBC, DAO대신 Mybatis를 싱글톤으로 만들었음
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	//DAO객체
	private EmpDao empDao;

	@Override
	public List<Emp> getList() {
		System.out.println("EmpService getList() 호출");
		//----------------------------------------------------------------------
		//여기까지 호출이 되는 것을 수행해야 다음 개발의 준비가 되어 있다고 판단할수 있다.
		
		//마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		//----------------------------------------------------------------------
		
		//DAO객체 연결
		empDao = sqlSession.getMapper(EmpDao.class);
		//마이바티스 연결과 Mapper 연결까지 확인 -> 여기서 에러나면 마이바티스 연결이 안되어 있음을 의미
		
		//전체 리스트 조회 - DAO이용
		List<Emp> list = empDao.selectAll();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		//연결했던 DAO같은 경우는 그때그때 해제, 그러나 마이바티스가 싱글톤으로 재활용되는 구조
		
		//조회 결과 반환
		return list;
	}
	
	@Override
	public Emp getParamEmpno(HttpServletRequest req) {
		// 나의 풀이와는 달리 강사는 서비스로 넘어간 후에 req로부터 파싱을 하고 있다
		//문제는 req에서 파싱되는 empno=7654 ->전부 String타입 -> 따라서 다음과 같이 코드가 적혀야 한다
		//전달파라미터 empno 추출하기
		String param = req.getParameter("empno");
		System.out.println("EmpService getParamEmpno() - param : " + param ); //String타입

		//문자열로 받은 empno를 숫자로 형변환(이부분 앞으로 엄청나게 많이 프로그래밍 하게 될것이다)
		//String -> int 변환 변수
		int empno = 0;
		
		//URL에서 비정상적인 문자열이 올때(빈문자열, null일때) 예외 처리 구문 추가
		//만일 if문이 없다면, 비정상적 상황에서 예외가 발생하면서 프로그램이 멈출 것이다
		//전달파라미터가 정상적으로 전달되었을 경우 변환하기
		if( param != null && !"".equals(param) ) {
			//String타입의 param을 int형으로 형변환해준다
			empno = Integer.parseInt(param);
		}
		System.out.println("EmpService getParamEmpno() - empno : " + empno ); //Int타입

		//전달파라미터를 DTO에 저장하기
		Emp emp = new Emp();
		emp.setEmpno(empno);
		
		//DTO객체 반환하기	
		return emp;
	}

	@Override
	public Emp getEmpDetail(Emp param) {
		System.out.println("EmpService getEmpDetail() 호출");

		//마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		empDao = sqlSession.getMapper(EmpDao.class);
		//마이바티스로 DAO와 연결하는 과정이다.
		
		//----------------------------------------------------------------------
		
		//SqlSession 객체의 DML 수행 메소드
//		sqlSession.insert("SQL Mapper"); - 반환타입은 int
//		sqlSession.update("SQL Mapper");
//		sqlSession.delete("SQL Mapper");
		//Mybatis 구버전에서 SQL구문 호출방법 -> 가독성 문제때문에 신버전에서 xml파일 형태로 변경
		
//		sqlSession.selectOne("SQL"); - 반환타입 DTO
//		sqlSession.selectList("SQL"); - 반환타입 List<>
//		sqlSession.selectMap("SQL", null); - 반환타입 HashMap(Spring Framewowrk에서 자주 사용)
		//Mybatis 구버전은 조회결과 조회된 행의 개수가 1개인지 여러개인지 구별해서 썼어야 했다
		//1개이면 selectOne, 여러개면 selectList 사용했었음.
		
		//Mybatis의 경우는 연습에 도움되려면 parameter를 int 같은 기호써서 하나만 보내는 것은 도움이 안된다
		//Mybatis의 기본객체인 selectOne()같은 것이 하나만 받을 수 있는 형태로 되어 있다
		//-> 따라서, Mybatis를 DTO로 묶어서 하나 보내는 개념으로 생각해야 한다
		//** DTO의 약점 - 너무 경직되어서 조인 상황에서 취약
		//HashMap을 통해서 유연하게 처리할 수 있다
		//DTO의 약점 보완 기능은 스프링 프레임워크에서 제공
		
		//----------------------------------------------------------------------
		//사원 정보 상세 조회
		Emp result = empDao.selectByEmpno(param);
		System.out.println("EmpDao getEmpDetail() - result : " + result);
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		return result;
	}


}
