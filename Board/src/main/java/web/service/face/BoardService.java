package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;

public interface BoardService {

	/**
	 * 컨트롤러에서 서비스에 전체 게시판 DB조회를 요청
	 * 서비스는 다시 DAO로 요청 전달
	 * @return 조회된 게시판 DB 내용
	 */
	public List<Board> getList();

	/**
	 * 전달값을 DTO형태로 바꾼다
	 * @param req 웹페이지로부터 받은 전달값
	 * @return DTO형태로 바꾼 전달값
	 */
	public Board getBoardno(HttpServletRequest req);

	/**
	 * 개시글 글조회하기
	 * 
	 * 1.hit+1 작업 추가
	 * 2.글번호로 게시글 조회
	 * 
	 * @param param 글번호를 가진 DTO객체
	 * @return 조회된 게시글 정보 DTO객체
	 */
	public Board view(Board param);

}
