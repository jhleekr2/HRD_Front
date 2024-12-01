package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.UploadfileDao;
import dto.Uploadfile;

public class UploadfileDaoImpl implements UploadfileDao {

	//DB관련 객체
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public int selectNextFileno(Connection conn) {
		
		String sql = "";
		sql += "SELECT UPLOADFILE_SEQ.nextval FROM dual";
		
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
	public int insert(Connection conn, Uploadfile uploadfile) {
		String sql = "";
		sql += "INSERT INTO uploadfile ( fileno, origin_name, stored_name, datano )";
		sql += " VALUES ( ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int idx = 1;
			ps.setInt(idx++, uploadfile.getFileno());
			ps.setString(idx++, uploadfile.getOriginName());
			ps.setString(idx++, uploadfile.getStoredName());
			ps.setInt(idx++, uploadfile.getDatano());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


	@Override
	public List<Uploadfile> selectAll(Connection conn) {
		//DB는 _형태, Java는 Camal case 형태로 계속 쓸것이다
		String sql = "";
		sql += "SELECT";
		sql	+= " fileno, origin_name, stored_name, datano";
		sql += " FROM uploadfile";
		sql += " ORDER BY fileno desc";
		
		List<Uploadfile> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Uploadfile uf = new Uploadfile();
				
				uf.setFileno(rs.getInt("fileno"));
				uf.setOriginName(rs.getString("origin_name"));
				//setOriginName - 자바, origin_name - DB
				uf.setStoredName(rs.getString("stored_name"));
				uf.setDatano(rs.getInt("datano"));
				
				list.add(uf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

}

//개발할때 가급적 타이핑을 한번이라도 덜하도록 노력한다는 마음가짐을 갖자
//그러면 실력이 금방 늘 것이다
//실제 프로그래밍 API도 마찬가지 생각에서 발전한다
//실제로 mybatis API(Spring Framework의 API)를 통해 반복되는 JDBC코드를 좀더 덜 쓸 수 있다