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

}
