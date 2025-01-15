package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.util.Paging;

public interface BoardDao {

	/**
	 * 서비스에서 전체 DB조회를 요청한다
	 * @return 조회된 전체 DB를 반환한다
	 */
	public List<Board> selectAll();
	
	/**
	 * 글번호로 게시글을 조회한다
	 * @param req 특정값의 DTO형태
	 * @return 조회된 DB를 반환한다.
	 */
	public Board selectBoardByBoardno(Board param);

	/**
	 * 글번호에 해당하는 조회수를 1 증가 시킨다
	 * @param param 조회수가 증가될 게시글 번호 DTO 객체
	 * @return UPDATE 수행 결과
	 */
	public void updateHit(Board param);

	/**
	 * 총 게시글 수 조회하기
	 * 
	 * @return 테이블의 전체 행 수
	 */
	public int selectCntAll();

	/**
	 * 테이블의 페이징이 적용된 리스트 조회하기
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 조회된 페이징 리스트
	 */
	public List<Board> selectPaging(Paging paging);
	
	/**
	 * 새로운 게시글 등록
	 * @param param 등록할 게시글의 내용이 들어있는 DTO 객체
	 * @return INSERT 수행 결과
	 */
	public int insert(Board param);

	/**
	 * 게시글 수정하기
	 * @param param - 수정할 내용을 담은 DTO 객체
	 * @return UPDATE 수행 결과
	 */
	public int update(Board param);

	/**
	 * 게시글 삭제하기
	 * @param param - 삭제할 내용을 담은 DTO 객체
	 */
	public void delete(Board param);



}
