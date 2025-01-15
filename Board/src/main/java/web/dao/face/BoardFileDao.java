package web.dao.face;

import java.util.List;

import web.dto.Board;
import web.dto.BoardFile;

public interface BoardFileDao {

	/**
	 * 첨부 파일 정보 삽입
	 * 
	 * @param boardFile
	 * @return
	 */
	public int insert(BoardFile boardFile);

	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param param - 조회할 게시글 번호를 가진 객체
	 * @return 첨부파일 정보
	 */
	public BoardFile selectFileByBoardno(Board param);
	
	/**
	 * 첨부파일 정보 삭제하기
	 * 
	 * @param board - 삭제할 첨부파일의 게시글 DTO 객체 (boardno 이용)
	 * @return DELETE 수행 결과
	 */
	public int delete(Board board);
	
	/**
	 * 게시글에 맞게 첨부된 모든 파일 정보 조회하기
	 * 
	 * @param board - 게시글 번호를 가진 DTO 객체
	 * @return 첨부 파일 목록
	 */
	public List<BoardFile> selectAllByBoardno(Board board);
}
