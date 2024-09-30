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
			break;
			
		case 3: //입력한 부서 번호로 조회
			break;
		
		default:
			System.out.println("1-3만 입력하세요");
		}
	}
	
}
