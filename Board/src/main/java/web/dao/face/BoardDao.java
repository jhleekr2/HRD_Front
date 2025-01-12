package web.dao.face;

import java.util.List;

import web.dto.Board;

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

}
