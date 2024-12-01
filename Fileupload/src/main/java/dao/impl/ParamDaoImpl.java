package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ParamdataDao;
import dto.Paramdata;
import dto.Uploadfile;

public class ParamDaoImpl implements ParamdataDao {
	
	//DB관련 객체
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public int selectNextDatano(Connection conn) {
		
		//SQL코드
//		String sql = "";
//		sql += "SELECT LAST_NUMBER";
//		sql += " FROM USER_SEQUENCES";
//		sql += " WHERE SEQUENCE_NAME = 'PARAMDATA_SEQ'";
		
		String sql = "";
		sql += "SELECT PARAMDATA_SEQ.nextval FROM dual";
		
		int nextval = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			nextval = rs.getInt("nextval");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		//최종 조회 결과 반환
		return nextval;
	}

	@Override
	public int insert(Connection conn, Paramdata paramdata) {
		
		String sql = "";
		sql += "INSERT INTO paramdata ( datano, title, data1, data2 )";
		sql += " VALUES ( ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int idx = 1;
			ps.setInt(idx++, paramdata.getDatano());
			ps.setString(idx++, paramdata.getTitle());
			ps.setString(idx++, paramdata.getData1());
			ps.setString(idx++, paramdata.getData2());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public Paramdata selectByDatano(Connection conn, Paramdata datano) {

		String sql = "";
		sql += "SELECT * FROM paramdata";
		sql += " WHERE datano = ? ";
		
		Paramdata data = new Paramdata();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, datano.getDatano());
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				data.setDatano(rs.getInt("datano"));
				data.setTitle(rs.getString("title"));
				data.setData1(rs.getString("data1"));
				data.setData2(rs.getString("data2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return data;
	}

	@Override
	public int update(Connection conn, Paramdata info) {
		
		String sql = "";
		sql += "UPDATE paramdata";
		sql += " SET title = ?, data1 = ?, data2 = ?";
		sql += " WHERE datano = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int idx = 1;
			ps.setString(idx++, info.getTitle());
			ps.setString(idx++, info.getData1());
			ps.setString(idx++, info.getData2());
			
			ps.setInt(idx++, info.getDatano());
			res = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}

}
