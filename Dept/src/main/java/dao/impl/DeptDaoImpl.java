package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.DeptDao;
import dto.Dept;

public class DeptDaoImpl implements DeptDao {

	@Override
	public List<Dept> selectAll(Connection conn) {
		//DB관련 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL 코드
		String sql ="";
		sql += "SELECT * FROM dept";
		sql += " ORDER BY deptno";
		
		//최종 조회 결과를 저장하기 위한 객체
		List<Dept> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Dept dept = new Dept();
				dept.setDeptno( rs.getInt("deptno") );
				dept.setDname( rs.getString("dname") );
				dept.setLoc( rs.getString("loc") );
				
				list.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		// 최종 조회 결과 반환
		return list;
	}

	@Override
	public int insertDept(Connection conn, Dept newDept) {
		
		// DB관련 객체
		PreparedStatement ps = null;
		
		String sql = "";
		sql += "INSERT INTO dept ( deptno, dname, loc )";
		sql += " VALUES ( ?, ?, ?)";
		
		//INSERT 수행 결과 변수
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int idx = 1;
			ps.setInt(idx++, newDept.getDeptno());
			ps.setString(idx++, newDept.getDname());
			ps.setString(idx++, newDept.getLoc());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			// 이때 connection까지 close하지 않도록 주의한다.
			// connection close -> DB연결 끊김!
		}
		return res;
	}

	@Override
	public Dept selectByDeptno(Connection conn, Dept deptno) {
		System.out.println("DeptDao] select(conn, deptno) 호출");

		//DB관련 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//SQL코드
		String sql = "";
		sql += "SELECT * FROM dept";
		sql += " WHERE deptno = ?";
		
		Dept info = null;
		
		try {
			ps = conn.prepareStatement(sql);

			int idx = 1;
			ps.setInt(idx++, deptno.getDeptno());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				info = new Dept();
				
				info.setDeptno( rs.getInt("deptno") );
				info.setDname( rs.getString("dname") );
				info.setLoc( rs.getString("loc") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close안하면 Exception이 계속 발생한다.
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환 - 프로젝트 할때 return null 로 되어 있지 않도록 주의한다!
		return info;
	}

}
