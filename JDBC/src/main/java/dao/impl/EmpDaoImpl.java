package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.EmpDao;
import dto.Emp;

public class EmpDaoImpl implements EmpDao {

	//(이 클래스에 JDBC관련 정보 세팅해놓을 예정)
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//OJDBC 관련 객체
	private static ResultSet rs = null; //조회(SELECT) 결과 반환 객체
	private static Connection conn = null; //DB연결 객체 ( 접속 객체 )
	private static PreparedStatement ps = null;
	
	//생성자
	public EmpDaoImpl() {
		
		try {
			//드라이버 로드
			Class.forName(DRIVER);
			
			//DB연결 - Add Catch clause to surrounding try 한다
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//자동 커밋 설정 - 기본값 true이지만 일반적으로 false 처리한다
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Emp> selectAll() {
		// SQL작성
		String sql = "";
		sql += "SELECT * FROM emp";
		sql += " ORDER BY empno";
		
		// 조회 결과를 저장할 객체
		List<Emp> list = new ArrayList<>();
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();
			
			// 조회 결과 처리
			while ( rs.next() ) {
				
				//조회 결과의 각 행 데이터를 저장할 객체
				Emp emp = new Emp();
				
				emp.setEmpno( rs.getInt("empno") );
				emp.setEname( rs.getString("ename") );
				emp.setJob( rs.getString("job") );
				emp.setMgr( rs.getInt("mgr") );
				
				emp.setHiredate( rs.getDate("hiredate") );
				emp.setSal( rs.getDouble("sal") );
				emp.setComm( rs.getDouble("comm") );
				emp.setDeptno( rs.getInt("deptno") );
				
				//전체 조회 결과로 저장하기
				list.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			
			try {
				if(rs!= null && !rs.isClosed() )	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(ps!= null && !ps.isClosed() )	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 최종 결과 반환
		return list;
	}
	
	
}
