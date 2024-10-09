package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.EmpDao;
import dto.Emp;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Emp> selectAll(Connection conn) {
		System.out.println("EmpDao selectAll() - 호출");
		//----------------------------------------------------------
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM emp";
		sql += " ORDER BY empno";
		
		List<Emp> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Emp emp = new Emp();
				
				emp.setEmpno( rs.getInt("empno") );
				emp.setEname( rs.getString("ename") );
				emp.setJob( rs.getString("job") );
				emp.setMgr( rs.getInt("mgr") );
				
				emp.setHiredate( rs.getDate("hiredate") );
				emp.setSal( rs.getDouble("sal") );
				emp.setComm( rs.getDouble("comm") );
				emp.setDeptno( rs.getInt("deptno") );
				
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public Emp selectByEmpno(Connection conn, Emp paramEmp) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT * FROM emp";
		sql += " WHERE empno = ?";
		
		//아까와 달리 한명만 조회하기 위해 한명만 넣는 emp 객체를 만든다
		Emp emp = null;
		
		try {
			ps = conn.prepareStatement(sql);
			//SQL 구문의 물음표를 execute하기 전에 채워줘야 한다.
			ps.setInt(1,  paramEmp.getEmpno());
			
			rs = ps.executeQuery();
			
			while (rs.next() ) {
				emp = new Emp();
				
				emp.setEmpno( rs.getInt("empno") );
				emp.setEname( rs.getString("ename") );
				emp.setJob( rs.getString("job") );
				emp.setMgr( rs.getInt("mgr") );
				
				emp.setHiredate( rs.getDate("hiredate") );
				emp.setSal( rs.getDouble("sal") );
				emp.setComm( rs.getDouble("comm") );
				emp.setDeptno( rs.getInt("deptno") );
				//코드 구조 파악 후 복붙같은 것을 적극적으로 활용하자
				//그러면 야근을 줄일수 있으며, CRUD를 공장식으로 양산할 수 있게 된다
				//아마도 공통부분이 적은 서비스 개발에 엄청난 투자를 해야 할 것이다
				//로직은 서비스에 들어간다
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//반환값을 미리 적어놓는 습관을 기르자
		return emp;
	}

}
