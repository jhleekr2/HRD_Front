package controller;

import java.util.List;
import java.util.Scanner;

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
		//부서 번호를 입력받아서 해당 부서의 사원들 조회하기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("조회할 부서 번호는? ");
		int deptno = sc.nextInt();
		
		//DAO를 이용한 부서직원 조회하기
		List<Emp> empList = empDao.selectByDeptno(deptno);
		
		System.out.println("조회 결과");
		for( Emp e : empList)
			System.out.println( e );
		
		System.out.println("--------------------------");
		
		//INSERT를 수행하기 위해 데이터를 전달한 DTO 객체
		Emp data = new Emp();
		
		System.out.print("사원 번호는? ");
		data.setEmpno( sc.nextInt() );
		
		System.out.print("사원 이름은? ");
		sc.nextLine(); //버퍼 비우기
		data.setEname( sc.nextLine() );
		
		System.out.print("부서 번호는? ");
		data.setDeptno( sc.nextInt() );
		
//		System.out.println("TEST] data : " + data);
		
		//데이터 삽입
		int res = empDao.insertEmp(data);
		
		//트랜잭션 관리
		if( res > 0 ) { //삽입 성공
			System.out.println("데이터 삽입 완료");
		} else { //삽입 실패
			System.out.println("데이터 삽입 실패");
		}
		
//		System.out.println();
		System.out.println("--- 전체 조회 결과 ---");
//		
		for( Emp e : empDao.selectAll() )
			System.out.println( e );
			
	}
}
