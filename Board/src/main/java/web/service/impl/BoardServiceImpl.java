package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import web.dao.face.BoardDao;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;

public class BoardServiceImpl implements BoardService {
	
	//마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	//DAO연결
	private BoardDao boardDao;
	
	//파일 정보를 저장할 DTO객체
	BoardFile boardfile = new BoardFile();

	@Override
	public List<Board> getList() {
		
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO Mapper 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		//DAO를 통해 DB 호출(전체조회)
		List<Board> list = boardDao.selectAll();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//DB호출 결과값 반환
		return list;
		
		//Spring Framework는 이 부분을 자동화한다
	}

	@Override
	public Board getBoardno(HttpServletRequest req) {
		
		//전달파라미터 boardno 추출하기
		String param = req.getParameter("boardno");
		
		System.out.println("BoardService getBoardno() - param : " + param ); //String 타입
		
		//문자열로 받은 boardno를 숫자로 형변환(이부분은 매우 중요!)
		//String -> int 변환 변수
		int boardno = 0;
		
		//URL에서 비정상적인 문자열이 올때(빈문자열, null일때) 예외 처리 구문 추가
		//만일 if문이 없다면, 비정상적 상황에서 예외가 발생하면서 프로그램이 멈출 것이다
		//전달파라미터가 정상적으로 전달되었을 경우 변환하기
		if( param != null && !"".equals(param) ) {
			//String 타입의 param을 int형으로 형변환해준다.
			boardno = Integer.parseInt(param);
		}
		System.out.println("BoardService getBoardno() - param : " + boardno ); //int 타입
		
		//전달파라미터를 DTO에 저장하기
		Board board = new Board();
		board.setBoardno(boardno);
		
		//DTO 객체 반환
		return board;
	}

	@Override
	public Board view(Board param) {
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO Mapper 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		//DAO를 통해 DB의 hit+1 작업 추가
		boardDao.updateHit( param );
		
		//DB업데이트 수행
		sqlSession.commit();
		
		//DAO를 통해 DB 호출(조회)
		Board result = boardDao.selectBoardByBoardno( param );
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		return result;
	}

}
