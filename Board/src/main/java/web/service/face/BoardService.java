package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;
import web.dto.BoardFile;
import web.util.Paging;

public interface BoardService {

	/**
	 * 컨트롤러에서 서비스에 전체 게시판 DB조회를 요청
	 * 서비스는 다시 DAO로 요청 전달
	 * @return 조회된 게시판 DB 내용
	 */
	public List<Board> getList();
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 게시글 페이징 목록 조회하기
	 * @param paging
	 * @return
	 */
	public List<Board> getList(Paging paging);

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

	/**
	 * 전달값을 DTO형태로 바꾼다
	 * @param req 웹페이지로부터 받은 전달값
	 * @return 입력할 제목과 내용을 가진 DTO객체
	 */
//	public Board getBoardContent(HttpServletRequest req);

	/** 
	 * 새 게시글 등록
	 * @param req 입력할 제목과 내용, 파일을 포함한 전달값
	 */
	public void write(HttpServletRequest req);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param param - 첨부파일과 연결된 게시글 번호 객체
	 * @return 첨부파일 정보 객체
	 */
	public BoardFile viewFile(Board param);

	/**
	 * 작성자 닉네임 조회
	 * @param param - 작성자 정보 포함된 게시글 내용 객체
	 * @return 작성자 닉네임
	 */
	public String getWriterNick(Board param);

	/**
	 * 게시글 업데이트
	 * @param req - 업데이트하고자 하는 제목과 내용, 파일을 포함한 전달값
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * @param req - 삭제하고자 하는 게시물을 포함한 전달값
	 */
	public void delete(HttpServletRequest req);

	/**
	 * 게시글 작성자와 로그인한 사용자와 아이디가 일치한지 확인
	 * 
	 * 로그인 정보(세션)와 작성자 정보(전달 파라미터) 를 이용하여 비교
	 * 
	 * @param req - 요청 정보 객체
	 * @return 작성자 판단 결과 (true: 작성자 일치, false: 작성자 불일치)
	 */
	public boolean checkUserid(HttpServletRequest req);



}
