package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OjdbcEx_03 {
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	// 아까 테스트 했던 것을 다시 타이핑하지말고 복사 붙여넣기를 한다
		
	//OJDBC 관련 객체
	private static Connection conn = null; //DB연결 객체 ( 접속 객체 )
		
	private static Statement st = null; //SQL구문 저장 및 SQL구문 수행 객체
	private static ResultSet rs = null; //조회(SELECT) 결과 반환 객체	
	
	public static void main(String[] args) {
		
		//--- 드라이버 로드 ---
		// 첫 실행시에 창이 하나 뜨는데, 이때 "Java Application"을 선택한다.
		try {
			Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//--- 조회할 job 입력받기 ---
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 job 입력 : ");
		String job = sc.nextLine();
		
		//--- SQL 작성 ---
		String sql = "";
		sql += "SELECT * FROM emp";
		sql += " WHERE upper(job) LIKE '%' || upper('" + job + "') || '%'";
		sql += " ORDER BY empno";
		
		System.out.println( sql );
		//실제 SQL코드 나왔는지 확인
		
		try {
			//--- DB 접속 ---
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			//--- SQL 수행 객체 ---
			st = conn.createStatement();
			
			//--- SQL 수행 ---
			rs = st.executeQuery(sql);
			
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
