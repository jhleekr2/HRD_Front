package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OjdbcEx_01 {

	public static void main(String[] args) {
		System.out.println("Hi JDBC");
		
		//JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//--- OJDBC 사용에 필요한 변수들
		Connection conn = null; //DB연결 객체(접속 객체)
		
		Statement st = null; //SQL구문 저장 및 SQL구문 수행 객체
//		java.sql에 Connection, Statement, ResultSet 있음
		ResultSet rs = null; //조회(SELECT) 결과 반환 객체
		
		//여기서부터 DB접속 파트
//		conn = DriverManager.getConntection("URL", "USERNAME", "PASSWORD");
//		첫 실행시에 창이 뜰때 Java Application 으로 실행
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");
			
			//SQL쿼리 수행
			st = conn.createStatement();
			
//			rs = st.executeQuery("SQL(SELECT문)");
			rs = st.executeQuery("SELECT * FROM dept ORDER BY deptno");
			
			//결과 집합의 첫 행부터 마지막 행까지 반복하며 수행한다
			while ( rs.next() ) {
				System.out.print( rs.getString("deptno") + ",");
				System.out.print( rs.getString("dname") + ",");
				System.out.println( rs.getString("loc") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//연결 종료
			try {
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)	st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
