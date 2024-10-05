package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {

	//JDBC 드라이버 로딩부분을 싱글톤 패턴으로 구현
	
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//DB연결 객체 (접속 객체)
	private static Connection conn = null;
	
	//private 생성자
	private JDBCTemplate() {}
	
	public static Connection getConnection() {
		
		//데이터베이스에 연결된 적이 없다면
		if( conn == null ) {
			//DB연결을 시도한다
			try {
				//드라이버 로드
				Class.forName(DRIVER);
				
				//DB연결
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					
				//AutoCommit 설정 끄기
				conn.setAutoCommit(false);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//Connection 객체 해제
	public static void close( Connection conn ) {
		try {
			if(conn!=null && !conn.isClosed())	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement 객체 해제
	public static void close( PreparedStatement ps ) {
		try {
			if(ps!=null && !ps.isClosed())	ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet 객체 해제
	public static void close( ResultSet rs ) {
		try {
			if(rs!=null && !rs.isClosed())	rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//COMMIT 수행 - 이때도 try-catch 해야 하기때문에 귀찮아서 모듈화
	public static void commit( Connection conn ) {
		try {
			if(conn!=null && !conn.isClosed())	conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ROLLBACK 수행 - 이때도 try-catch 해야 하기때문에 귀찮아서 모듈화
	public static void rollback( Connection conn ) {
		try {
			if(conn!=null && !conn.isClosed())	conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
