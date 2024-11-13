package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.Member;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps; //SQL 수행 객체
	private ResultSet rs; //SELECT 결과 집합 객체
	
	@Override
	public int selectNextUserno(Connection conn) {
		String sql ="";
		sql += "SELECT member_seq.nextval FROM dual";
		
		int nextval = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				nextval = rs.getInt("nextval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return nextval;
	}

	@Override
	public int insertMember(Connection conn, Member param) {
		String sql = "";
		sql += "INSERT INTO member ( userno, userid, nick, email)";
		sql += " VALUES ( ?, ?, ?, ?)";
		
		//insert 수행 결과
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			int ParamIdx = 1;
			ps.setInt(ParamIdx++, param.getUserno());
			ps.setString(ParamIdx++, param.getUserid());
			ps.setString(ParamIdx++, param.getNick());
			ps.setString(ParamIdx++, param.getEmail());
			
			res = ps.executeUpdate(); //INSERT, UPDATE, DETETE문
//			ps.executeQuery() - SELECT문
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public Member selectByUserno(Connection conn, int userno) {
		String sql = "";
		sql += "SELECT";
		sql += " userno, userid, nick, email";
		sql += " FROM member";
		sql += " WHERE userno = ?";
			
		Member member = null;
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userno);
			rs = ps.executeQuery();
				
			while( rs.next() ) {
				member = new Member();
					
				member.setUserno(rs.getInt("userno"));
				member.setUserid(rs.getString("userid"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
			
		return member;
	}

}
