package controller;

import java.util.List;
import java.util.Scanner;

import dto.Dept;
import service.face.DeptService;
import service.impl.DeptServiceImpl;

public class DeptController {

	//서비스 객체
//	private static DeptService deptService = new DeptServiceImpl();
	private DeptService deptService = new DeptServiceImpl();
	//static 제거함으로서 메인함수에서 deptService를 사용할 수 있게 되었다.
	//그래서 deptService를 호출하기 위해서 menu()를 통해 호출하는 구조를
	//콘솔 프로그램에서 많이 사용한다
	
	private Scanner sc = new Scanner(System.in);
	
	public void menu() {
		
		System.out.println("----- 메뉴 -----");
		System.out.println("수행할 기능을 입력하시오(1-3)");
		System.out.println();
		System.out.println("1. 부서 정보 전체 조회");
		System.out.println("2. 부서 정보 입력");
		System.out.println("3. 부서 번호로 부서 정보 조회");
		
		//메뉴 선택
		int sel = 0;
		System.out.print("\n\t>> ");
		sel = sc.nextInt();
		
		System.out.println();
		System.out.println("-------------------");
		
		switch( sel ) {
		case 1: //전체 조회
			System.out.println("\n 전체 부서 출력");
			
			List<Dept> list = deptService.listAll();
			for( Dept d : list ) {
				System.out.println( d );
			}
			break;
			
		case 2: //신규 입력
			System.out.println("\n 새로운 부서 정보 입력");
			
			//DB에 등록할 Dept 정보 입력받기
			Dept newDept = deptService.inputDept();
			
			//DB에 newDept 정보 삽입하기
//			deptService.register( newDept );
			
			//이후 부서 정보 삽입에 성공했는지 실패했는지 대입
			boolean result = deptService.register( newDept );
			
			System.out.print("\n\t>> ");
			if( result )	System.out.println("부서 정보 삽입 성공");
			else	System.out.println("부서 정보 삽입 실패");
			
			break;
			
		case 3: //입력한 부서 번호로 조회
			System.out.println("\n 부서 정보 조회");
			
			//조회할 부서 번호 입력받기 - DeptService 이용
			// -> .inputDeptno()
			// 이때 int형으로 입력받아도 되지만, Dept형으로 입력받는 것이
			//개발에 더 편리하다고 함(DTO로 처리).
			//(사실 int형으로 처리해도 되긴함)-유지보수에 불리
			Dept deptno = deptService.inputDeptno();
			
			//입력된 Dept정보 입력값 확인
//			System.out.println("DeptController] " + deptno);
			
			//부서번호를 이용하여 조회하기 - DeptService 이용
			// -> .deptInfo()
			Dept info = deptService.deptInfo(deptno);
			
			//부서정보 출력하기 - System.out.println() 이용
			if( info != null ) System.out.println(info);
			else System.out.println("부서는 존재하지 않습니다");
			break;
		
		default:
			System.out.println("1-3만 입력하세요");
		}
	}
	
	public static void main(String[] args) {
		new DeptController().menu();
	}
	
}
