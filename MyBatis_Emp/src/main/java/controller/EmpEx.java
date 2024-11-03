package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.EmpDao;
import dto.Emp;
import mybatis.MyBatisConnectionFactory;

public class EmpEx {

	public static void main(String[] args) {

		//SqlSession 객체를 생성할 팩토리 객체
		SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	
		//마이바티스 수행 객체
		SqlSession sqlSession = null;
		
		//DB접속 및 SQL 수행 객체 생성
		sqlSession = sqlSessionFactory.openSession(); //AutoCommit OFF
	
		//-------------------------------------------------------------------
		
		EmpDao empdao = sqlSession.getMapper(EmpDao.class);
		System.out.println("마이바티스 로드 완료!");
		
		//-------------------------------------------------------------------
		
		List<Emp> list = empdao.selectAll();
		System.out.println("--- 전체 부서 목록 ---");
		
		for( Emp e : list ) {
			System.out.println( e );
		}
		
		//-------------------------------------------------------------------
		
		Scanner sc = new Scanner(System.in);
		
		Emp emp = new Emp();
		
		System.out.print("직원번호는?");
		emp.setEmpno( sc.nextInt() );
		
		System.out.print("직원이름은?");
		sc.nextLine(); //입력버퍼 비우기
		emp.setEname( sc.nextLine() );
		
		System.out.println("입사날짜는(yyyy-MM-dd)?");
//		emp.setHiredate(null);
		// 현재 날짜 및 시간 가져오기
//		Date date = new Date();
		new Date();
		// 날짜형식 포멧
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println( new Date() );
		
		String da = sc.nextLine();
//		emp.setHiredate(new Date());
		try {
			//입력받은 날짜를 String형식에서 Date형식으로 변환한다
			emp.setHiredate(sd.parse(da));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//사원 번호로 조회하기 - 중복 데이터 확인
		Emp result = empdao.selectByEmpno( emp );
		
		//중복 데이터가 없다면, 사원 번호 추가하고 다시 삭제
		if( result == null ) {
			int res = empdao.insertEmp( emp );
			
			//트랜잭션 관리
			if( res > 0 ) {
				sqlSession.commit();
				System.out.println("사원번호 등록 성공!");
			} else {
				sqlSession.rollback();
				System.out.println("사원번호 등록 실패!");
			}
			
			//추가한 사원 번호 조회하기
			System.out.println("--- 추가한 직원 정보 ---");
			System.out.println(empdao.selectByEmpno( emp ));
			
			//사원 번호 다시 삭제
			res = empdao.deleteByEmpNo(emp);
			//트랜잭션 관리
			if( res > 0 ) {
				sqlSession.commit();
				System.out.println("사원번호 삭제 성공!");
			} else {
				sqlSession.rollback();
				System.out.println("사원번호 삭제 실패!");
			}
		} else {
			System.out.println("이미 중복된 직원 번호입니다");
		}
		
		list = empdao.selectAll();
		System.out.println("--- 전체 부서 목록 ---");
		
		for( Emp e : list ) {
			System.out.println( e );
		}
		
		sc.close();
	}

}
