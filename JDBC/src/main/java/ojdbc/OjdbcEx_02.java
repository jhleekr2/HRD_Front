package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OjdbcEx_02 {

	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//OJDBC 관련 객체
	private static Connection conn = null; //DB연결 객체 ( 접속 객체 )
		
	private static Statement st = null; //SQL구문 저장 및 SQL구문 수행 객체
	private static ResultSet rs = null; //조회(SELECT) 결과 반환 객체
	
	public static void main(String[] args) {
		
		//드라이버 로드
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//--- SQL 작성 ---
		String sql1 = "";
		sql1 += "CREATE TABLE usertest (";
		sql1 += "	idx NUMBER CONSTRAINT pk_user_test PRIMARY KEY";
		sql1 += "	, name VARCHAR2(500) NOT NULL";
		sql1 += "	, phone VARCHAR2(500) NOT NULL";
		sql1 += ")";
		
		String sql2 = "";
		sql2 += "CREATE SEQUENCE seq_usertest";
		sql2 += "	INCREMENT BY 1";
		sql2 += "	START WITH 1";
		
		//이때 SQL구문에는 ; 안적는다 적으면 오히려 오류남!
		
		try {
			//DB접속
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//SQL 수행 객체
			st = conn.createStatement();
			
			//user_tables 자료사전 조회하기
			rs = st.executeQuery("SELECT count(*) FROM user_tables\r\n"
					+ "WHERE table_name = upper('usertest')");
			
			//조회결과의 첫행 참조하기
			if( rs.next() ) {
				
				
				//기존 usertest 테이블이 존재할 경우
				if( rs.getInt(1) > 0) {
					
					//usertest 테이블 삭제하기
					st.execute("DROP TABLE usertest");
				}
				
				//usertest테이블 생성하기
				st.execute(sql1);
			}
			
			//seq_usertest 시퀀스 생성하기
			
			//seq_usertest 시퀀스가 이미 존재하는지 확인하기
			// -> user_sequences
			rs = st.executeQuery(
					"SELECT count(*) FROM user_sequences"
					+ " WHERE sequence_name = upper('seq_usertest')");
			// +로 연장할때 WHEHE 앞에 띄어쓰기 필수
			//이미 존재하면 시퀀스 삭제
			if( rs.next() ) {
				if( rs.getInt(1) > 0 ) {
					st.execute("DROP SEQUENCE seq_usertest");
				}
			}
			//없으면 생성
			st.execute(sql2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
