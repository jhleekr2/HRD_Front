package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

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
		
		// Connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		// 전체 부서 목록 조회하고 List형식으로 받아온다
		List<Dept> list = deptDao.selectAll(conn);
		
		// 조회 결과 반환
		return list;
	}

	@Override
	public Dept inputDept() {
		Scanner sc = new Scanner(System.in);
		
		Dept newDept = new Dept();
		
		System.out.print("부서 번호는? ");
		newDept.setDeptno( sc.nextInt() );
		
		System.out.print("부서 이름은? ");
		sc.nextLine();
		newDept.setDname( sc.nextLine() );
		
		System.out.print("부서 위치는? ");
		newDept.setLoc( sc.nextLine() );
		
		return newDept;
	}

	@Override
	public boolean register(Dept newDept) {
		// Connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//DB에 신규 부서 정보 삽입하기
		//구조가 일반적이지 않아서 간과하기 쉬운데, 여기서 첫번째 변수가
		//Connection으로 넘어온 conn임에 주의한다!
		int res = deptDao.insertDept( conn, newDept );
		
		//트랜잭션 관리
		if( res > 0 ) { //삽입 성공
			JDBCTemplate.commit(conn);
			//컨트롤러에 성공 사실 알림
			return true;
		} else { //삽입 실패
			JDBCTemplate.rollback(conn);
			//컨트롤러에 실패 사실 알림
			return false;
			//return 추가하면 오류 -> boolean으로 바꾸는데 그러면 다시 오류
			//-> 인터페이스에서도 다시 boolean으로 바꿔야한다
		}
	}

}
