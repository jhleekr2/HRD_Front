package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Uploadfile;

public interface UploadfileDao {

	/**
	 * UPLOADFILE_SEQ의 nextval 을 조회한다
	 * uploadfile 테이블 PK로 사용된다
	 * 
	 * @param conn - DB 연결 객체 
	 * @return UPLOADFILE_SEQ.nextval
	 */
	public int selectNextFileno(Connection conn);
	//프로젝트 규모 커질수록, 비슷한 구조가 굉장히 많아진다. 특히 DAO에서 그런일이 많다.
	//바뀌는건 컬럼, 멤버필드 같은 것들과 SQL문만 바뀔 것이다.
	
	/**
	 * 파일 업로드 정보 삽입
	 * 
	 * @param conn - DB 연결 객체
	 * @param uploadfile - 업로드 파일 DTO 객체
	 * @return INSERT 수행 결과
	 */
	public int insert(Connection conn, Uploadfile uploadfile);

	/**
	 * 전체 목록 조회
	 * @param conn - DB 연결 객체
	 * @return 조회된 전체 파일 목록 객체
	 */
	public List<Uploadfile> selectAll(Connection conn);

}
