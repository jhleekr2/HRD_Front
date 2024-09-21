package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OjdebEx_04 {

	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//OJDBC 관련 객체
	private static Connection conn = null; //DB연결 객체 ( 접속 객체 )
	
//	private static Statement st = null; //SQL구문 저장 및 SQL구문 수행 객체
	private static ResultSet rs = null; //조회(SELECT) 결과 반환 객체

	//Statement 객체 - SQL Injection 공격때문에 최근에는 쓰지 않는다
	//대신 PreparedStatement 객체 사용
	//사용법이 Statement 객체와는 다름
	
	private static PreparedStatement ps = null;
	
	public static void main(String[] args) {
		
		//드라이버 로드
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//조회할 job 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.println("조회할 job 입력 : ");
		String job = sc.nextLine();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM emp";
		sql += " WHERE upper(job) LIKE '%' || upper(?) || '%'";
		sql += " ORDER BY empno";
		//기존 방법과는 달리 변수를 ?로 처리한다
		
		System.out.println( sql );
		// 실제 SQL코드 나왔는지 확인
		
		try {
			//DB접속
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//SQL 수행 객체
//			st = conn.createStatement(); 대체 코드
			ps = conn.prepareStatement(sql);
			
			//? 파라미터 채우기
			ps.setString(1, job); //첫번째 ?를 job변수로 채운다
			
			//SQL 수행
//			rs = st.executeQuery(sql); 대체 코드
			rs = ps.executeQuery();
			
			//--- SQL 조회 결과 처리 ---
			while( rs.next() ) {
				System.out.print( rs.getInt("empno") + "\t");
				System.out.print( rs.getString("ename") + "\t");
				System.out.println( rs.getString("job") );
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
//			try {
//				if(st!=null)	st.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			try {
				if(ps!=null)	ps.close();
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
