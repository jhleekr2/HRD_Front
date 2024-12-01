package dao.face;

import java.sql.Connection;

import dto.Paramdata;

public interface ParamdataDao {

	/**
	 * PARAMDATA_SEQ의 nextval 을 조회한다
	 * paramdata 테이블 PK로 사용된다
	 * 
	 * @param conn - DB 연결 객체 
	 * @return PARAMDATA_SEQ.nextval
	 */
	public int selectNextDatano(Connection conn);
	//자동 완성때 public 넣어주는 것을 잊지 말자! 안그러면 서버에서 작동 안한다.
	
	/**
	 * 전달 파라미터 정보 삽입
	 * 
	 * @param conn - DB 연결 객체
	 * @param paramdata - 전달 파라미터 DTO 객체
	 * @return INSERT 수행 결과
	 */
	public int insert(Connection conn, Paramdata paramdata);

	/**
	 * 
	 * @param conn - DB 연결 객체
	 * @param datano - 전달 파라미터 DTO객체
	 * @return - SELECT 수행 결과
	 */
	public Paramdata selectByDatano(Connection conn, Paramdata datano);
	
	/**
	 * 
	 * @param conn - DB 연결 객체
	 * @param info - 전달 파라미터 DTO객체
	 * @return - UPDATE 수행 결과
	 */
	
	public int update(Connection conn, Paramdata info);

}
