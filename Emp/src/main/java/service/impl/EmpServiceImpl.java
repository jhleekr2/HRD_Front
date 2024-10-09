package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.EmpDao;
import dao.impl.EmpDaoImpl;
import dto.Emp;
import service.face.EmpService;

public class EmpServiceImpl implements EmpService {

	//DAO 객체
	private EmpDao empDao = new EmpDaoImpl();

	@Override
	public List<Emp> listAll() {
		System.out.println("EmpService listAll() - 호출");
		//----------------------------------------------------------
		//테스트 코드를 남겨놓는 습관을 들이자. 나중에 오류났을때 추적이 용이해진다
		// DB연결 객체 - JDBCTemplate 객체 이용
		Connection conn = JDBCTemplate.getConnection();

		// Emp테이블 전체 조회 - EmpDao 객체 이용
		List<Emp> list = empDao.selectAll(conn);

		// 조회 결과 반환
		return list;
	}

	@Override
	public Emp getParamEmpno(HttpServletRequest req) {
		//Emp테이블에서 전달받은 목록을 조회한다
		String param = req.getParameter("empno");
		System.out.println("EmpService - param : " + param);
		//출력을 통한 확인코드
		//이때 주소에서 http://localhost:8085/emp/detail 하면 param = null 반환
		//이렇게 되었을 때 parseInt과정에서 예외(NumberFormatException)이 발생
		
		//따라서 에러 처리해야 한다
		//int empno = Integer.parseInt(param); 코드를 다음과 같이 바꾼다
		
		// !"".equals(param) -> 빈 문자열인지 여부를 체크하여 빈 문자열일떄 발생하는 예외처리
		int empno = 0;
		if( param != null && !"".equals(param) ) {
			empno = Integer.parseInt(param);
		}
		
		//서블릿이 요청파라미터 꺼내서 DTO객체에 저장하고
		//이때 String 타입과 Emp타입의 충돌이 발생해서 오류가 난다
		//-> 문자에서 숫자로 형변환
		System.out.println("EmpService - empno : " + empno);
		
		//개발자는 HTTP 500 오류를 내서는 안된다!
		//만일 HTTP 500 오류가 발생하면 서버 콘솔에 Exception 메시지가 발생하는데
		//이때 발생한 Exception(예외) 메시지를 분석해서 맨 밑에서부터 위로 올라와서
		//내가 작성한 코드 부분을 찾는다. 이때 가장 밑에 있는 내가 작성한 코드 부분이
		//오류가 발생한 위치
		
		Emp paramEmp = new Emp();
		paramEmp.setEmpno(empno);
		System.out.println("EmpService - paramEmp : " + paramEmp);
		
		//DTO 객체를 서비스의 detail 메소드에 전달
		return paramEmp;
	}

	@Override
	public Emp detail(Emp paramEmp) {
		return empDao.selectByEmpno(JDBCTemplate.getConnection(), paramEmp);
		//(conn, empno) 입력값 -> 반환 Emp
	}

}
