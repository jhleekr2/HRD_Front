package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.face.UserDao;
import dto.User;

public class UserDaoImpl implements UserDao{

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
	
	public UserDaoImpl() {
		
		try {
			// 드라이버 로드
			Class.forName(DRIVER);
			
			// DB연결
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			// 자동 커밋(Auto Commit) 설정
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<User> selectAll() {
		
		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM userTest";
		sql += " ORDER BY IDX";
		
		// 조회 결과를 저장할 객체
		List<User> list = new ArrayList<>();
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();

			// 조회 결과 처리
			while( rs.next() ) {
				
				//조회 결과의 각 행 데이터를 저장할 객체
				User userTest = new User();
				userTest.setIdx( rs.getInt("idx") );
				userTest.setUserid( rs.getString("userid"));
				userTest.setName( rs.getString("name"));
				
				//전체 조회 결과로 저장하기
				list.add(userTest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return list;
	}

	@Override
	public int insertUser(User data) {
		// 사용자 삽입 SQL 구문
		String sql = "";
		sql += "INSERT INTO userTest ( idx, userid, name )";
		sql += " VALUES ( userTest_SQ.nextval, ?, ? )";
		
		// 사용자의 userid와 name 입력
		Scanner sc = new Scanner(System.in);
		System.out.print("userId : ");
		data.setUserid(sc.next()); // 엔터키 제외하고 입력받음
		// 여기서 sc.nextLine() 쓰면 엔터키를 한번더 입력해야하는 버그 발생!
		// https://hwangpenguin.tistory.com/44 참고
		
		System.out.print("name : ");
		sc.nextLine(); // 버퍼 비우기
		data.setName(sc.next());
		
		int res = 0;
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			ps.setString( 1, data.getUserid() );
			ps.setString( 2, data.getName() );
			// SQL 수행 및 결과 저장
			res = ps.executeUpdate();
			// 성공하면 커밋, 실패하면 롤백
			conn.commit(); //삽입 성공 후 커밋
			
		} catch (SQLException e) {
			e.printStackTrace();
			// 실패지점은 이 지점
			try {
				conn.rollback(); //삽입 실패(예외)후 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			//자원 해제
			try {
				if(ps!=null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
		
	}

	@Override
	public User selectByIdx(int idx) {

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM userTest";
		sql += " WHERE idx = ?";
		
		// 조회 결과 저장 객체
		User user = new User();
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);

			// 파라미터 채우기
			ps.setInt(1, idx);

			// SQL 수행 및 결과 저장
			rs = ps.executeQuery();

			// 조회 결과 처리
			while( rs.next() ) {
				
				//조회 결과의 각 행 데이터를 저장할 객체				
				user.setIdx( rs.getInt("idx") );
				user.setUserid( rs.getString("userid") );
				user.setName( rs.getString("name") );
				
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
		return user;
	}

	@Override
	public void deleteByIdx(int idx) {
		// SQL 작성
		String sql = "";
		sql += "DELETE FROM usertest";
		sql += " WHERE idx = ?";
		
		int res = 0;
		
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			// 파라미터 채우기
			ps.setInt(1, idx);
			
			// SQL 수행 및 결과 저장
			res = ps.executeUpdate();
			
			conn.commit(); //삽입 성공 후 커밋
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			// 실패지점은 이 지점
			try {
				conn.rollback(); //삽입 실패(예외)후 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			//자원 해제
			try {
				if(ps!=null && !ps.isClosed()) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
