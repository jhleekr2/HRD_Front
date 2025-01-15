package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import web.dao.face.BoardDao;
import web.dao.face.BoardFileDao;
import web.dao.face.MemberDao;
import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService {
	
	//마이바티스 연결 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	//DAO연결
	private BoardDao boardDao;
	private BoardFileDao boardFileDao;
	private MemberDao memberDao;
	
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
	
	//getList 메소드는 생성자 오버로딩을 통해서 매개변수가 없으면 전체 조회
	//매개변수가 있으면 선택된 페이지만 조회한다
	@Override
	public List<Board> getList(Paging paging) {
		
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO Mapper 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		//DAO를 통해 DB 호출(선택된 페이지만 조회)
		List<Board> list = boardDao.selectPaging(paging);
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//DB호출 결과값 반환
		return list;
		
		//Spring Framework는 이 부분을 자동화한다
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] BoardService - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = boardDao.selectCntAll();
		
		//페이징 계산하기
		Paging paging = new Paging(curPage, totalCount);
//		Paging paging = new Paging(curPage, totalCount, 30, 5);
		System.out.println("BoardService - " + paging);
		sqlSession.close();
		
		return paging;
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

//	@Override
//	public Board getBoardContent(HttpServletRequest req) {
//		
//		//DTO 객체 정의
//		Board param = new Board();
//		
//		//전달파라미터를 DTO에 저장하기 - multipart/form-data 방식일때는 먹통
//		param.setTitle(req.getParameter("title"));
//		param.setContent(req.getParameter("content"));
//		//multipart/form-data 방식의 전달파라미터를 파싱해서 DTO에 저장해야만 한다
//		return null;
//	}

	@Override
	public void write(HttpServletRequest req) {
		//multipart/form-data 방식의 전달파라미터를 파싱해서 DTO에 저장해야만 한다
		
		//전달파라미터 파싱하기 위해 파일 처리
		//1. 파일업로드 형식의 인코딩이 맞는 지 검사한다
		// -> 요청 메시지의 content-type이 multipart.form-data인지 확인
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart 데이터가 아닐 경우
		if(!isMultipart) {
			System.out.println("파일 업로드(multipart) 데이터 형식이 아닙니다");
			
			//파일 업로드 처리 종료 (실패)
			return;
		}
		
		//2. 업로드된 데이터를 처리하는 방법을 설정한다
		//반드시 org.apache.commons 를 import해야 한다
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//3. FileItem을 메모리에서 처리할 용량 크기
		int maxMem = 1 * 1024 * 1024; // 1 MB (1048576 bytes)
		
		factory.setSizeThreshold( maxMem );
		
		//4. 메모리 처리 사이즈보다 크면 임시 파일을 만들어서 처리한다
		//임시 파일을 저장할 폴더를 설정할 수 있다.
		
		//서블릿 컨텍스트 객체
		// -> 요청받은 정보를 처리하고 있는 서블릿의 컨텍스트 환경을 확인/처리할 수 있다
		ServletContext context = req.getServletContext();
		
		//서버에 배포된 서블릿의 실제 서버 경로를 이용하여 tmp 경로를 지정한다
		String path = context.getRealPath("tmp");
		System.out.println("FileService fileupload() - tmp경로 : " + path );

		//실제 서버 경로는 리눅스 서버에 직접 배포할때 알고 있어야만 한다!

		//빈 폴더는 이클립스 톰캣에서 자동 배포해주지 않는다
		//그리고, 이클립스 톰캣 자동배포 서버 경로는 개발자가 직접 건들지 말자
		//직접 건들면 오류가 발생할 수 있다
		
		//** 인텔리제이의 경우 배포 설정이 이클립스에 비해 복잡하다.
		
		//임시 파일을 저장할 폴더의 File객체
		File tmpRepository = new File(path);
		
		//임시 저장소 폴더 생성하기
		// -> .mkdir() 메소드는 대상 폴더가 있으면 생성하지 않고
		// 폴더가 없을 경우 생성한다
		tmpRepository.mkdir();

		//임시 파일을 저장할 폴더를 팩토리 객체에 설정하기
		factory.setRepository(tmpRepository);
		
		//5. 파일 업로드를 수행하는 객체를 설정하며 생성하기
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//최대 업로드 허용 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10 MB
		upload.setFileSizeMax( maxFile );
		
		//--- 파일 업로드 준비 완료 ---
		
		//6. 파일 업로드 처리
		// -> 전달 파라미터 추출하기
		
		List<FileItem> items = null;
		try {
			
			//요청 정보 객체에 담겨있는 전달 파라미터를 추출한다
			// -> 폼필드, 파일 모두 파싱한다
			items = upload.parseRequest(req);
			//전달 파라미터 3개를 파싱해서 꺼내줄 것이다
			//반환형은 List<FileItem>
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 확인
		// List니까 for-each 구문 사용!
		// 이때 1-10 MB 사이의 파일로 테스트해본다
		for( FileItem item : items ) {
			System.out.println("FileService fileupload() - item : " + item);
		}
		
		//7. 파싱된 전달 파라미터 데이터 분류 처리하기
		
		//DTO객체 정의
		// -> List<FileItem> 객체에 "파일" 과 "폼필드" 데이터가 들어있다
		
		//폼필드 전달 파라미터를 저장할 DTO객체 - 작성글
		Board param = new Board();

		//파일 정보를 저장할 DTO객체 - 첨부파일
		BoardFile boardFile = null;
				
		//7-1. 폼필드
		//전달된 데이터를 DB에 INSERT한다( Board TB )
		
		//파일 아이템 리스트의 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			
			//전달 파라미터를 저장한 FileItem 객체
			FileItem item = iter.next(); // 아이템이 3개이므로 3번 반복
			//전달 파라미터 title, content, upfile(폼태그 파일인 write.jsp에 있음)

			//빈 파일에 대한 처리 (용량이 0 Byte)
			if ( item.getSize() <= 0 ) {
			//버그를 줄이기 위해 부정조건으로 보통 적는다 -> 0Byte보다 작은 경우까지 포함
				
				continue; //break하면 중단되어 버리므로 continue문을 통해 빈 파일만 무시하고
			//다음 FileItem 처리로 넘어간다
			}
			
			//폼필드에 대한 처리
			if ( item.isFormField() ) {
				//req.getParameter()를 대체하는 코드

				//폼 필드 데이터는 key=value 쌍으로 전달된다
				//name=value (둘다 String타입)
				
				// name은 item.getFieldName(); 메소드로 추출한다
				// value는 item.getString(); 메소드로 추출한다
				
				// **item.getString("UTF-8"); 을 이용하여 한글 인코딩을 설정한다
				
				//name 추출하기
				String name = item.getFieldName();
				
				//value 추출하기(한글 인코딩 설정 포함)
				String value = null;
				
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			
				//전달파라미터의 name에 맞춰 DTO 객체에 value 저장하기
				if( "title".equals(name) ) {
					param.setTitle(value);
				} else if( "content".equals(name) ) {
					param.setContent(value);
				}
			} // ( if !item.isFormField() ) end	
				
			// 7-2. 파일
			// 파일은 디스크에 저장한다
			// 파일의 정보를 DB에 INSERT 한다 ( uploadfile TB )
			
			//파일에 대한 처리
			if( !item.isFormField() ) {
				
				boardFile = new BoardFile();
				
				//업로드된 파일을 서버의 로컬 디스크의 폴더에 저장한다
				//파일의 이름을 원본과 다르게 바꿔서 저장하는데
				//이때 DB에 원본 파일의 이름을 기억하고 바꾼 파일 이름과 같이 저장한다
				//DB 테이블에 원본 이름, 바꿔서 저장한 이름 모두 INSERT한다
				
				//파일 이름을 바꾸는 방법에는 여러 가지가 있으나, 여기서는 날짜/시간 이용한다
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				
				//** SimpleDateFormat의 메소드
//				String Date포맷 문자열 = sdf.format( Date 타입 );
				
//				Date 변환된 Date = sdf.parse( "Date포맷 문자열" );
				
				//저장될 파일 이름 생성하기
				String rename = sdf.format( new Date() );
				
				boardFile.setOriginName(item.getName());
				boardFile.setStoredName(rename);
				
				boardFile.setFilesize((int)item.getSize());
				
				//fileno - sequence방식으로 처리
				//originname, storedname은 이미 처리
				//나머지는 Foreign Key로 쓸 예정
				
				//** 업로드된 임시 파일을 실제 저장소 폴더로 옮긴다

				//실제 저장하는 파일 저장소(폴더) 생성
				File uploadFolder = new File ( context.getRealPath("upload") );
				uploadFolder.mkdir();
				
				//실제 저장할 파일 객체
				File up = new File(uploadFolder, rename);
				
				//여기서 item은 임시파일을 지칭
				try {
					//임시파일(item객체)을 실제 업로드 파일(up)으로 출력한다
					//Stream이어서 try-catch가 있음
					item.write(up);
					
					//임시 파일을 삭제한다
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} // if( !item.isFormField() ) end
			
		} //while( iter.hasNest() ) end
		
		//8. DB에 최종 데이터 삽입하기
		// -> factory 객체는 멤버필드인 SqlSessionFactory 객체
		SqlSession sqlSession = this.factory.openSession();

		//DAO객체 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		int res = 0;
		
		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//세션의 로그인 정보로부터 게시글의 작성자 아이디 정보 삽입
		param.setUserid( String.valueOf( httpSession.getAttribute( "userid" ) ) );
		
		//게시글 데이터 삽입하기
		res += boardDao.insert( param );
		
		//첨부파일이 존재할 때
		if( boardFile != null ) {
			//DAO객체 연결
			boardFileDao = sqlSession.getMapper(BoardFileDao.class);

			//FK 값 연결하기
			boardFile.setBoardno( param.getBoardno() );

			//첨부파일 데이터 삽입하기
			res += boardFileDao.insert( boardFile );
		}
		
		//트랜잭션 관리
		if( boardFile != null && res < 2 ) {
			sqlSession.rollback();
		} else if( boardFile == null && res < 1 ) {
			sqlSession.rollback();
		} else {
			sqlSession.commit();
		}
		sqlSession.commit();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
	}

	@Override
	public BoardFile viewFile(Board param) {
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		boardFileDao = sqlSession.getMapper(BoardFileDao.class);
		
		//첨부파일 정보 조회
		BoardFile boardFile = boardFileDao.selectFileByBoardno( param );
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//조회된 첨부파일 정보 리턴
		return boardFile;
	}

	@Override
	public String getWriterNick(Board param) {
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		//** 연결된 DAO 객체와 조회하려는 DAO 객체가 서로 다르면
		//NullPointerException이 발생하며 작동하지 않는다
		
		//작성자 회원정보 조회
		String usernick = memberDao.selectUsernickByUserid( param );
		
		System.out.println(usernick);
		
//		String usernick = member.getUsernick();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//조회된 닉네임 리턴
		return usernick;
	}

	@Override
	public void update(HttpServletRequest req) {
		//multipart/form-data 방식의 전달파라미터를 파싱해서 DTO에 저장해야만 한다
		
		//전달파라미터 파싱하기 위해 파일 처리
		//1. 파일업로드 형식의 인코딩이 맞는 지 검사한다
		// -> 요청 메시지의 content-type이 multipart.form-data인지 확인
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart 데이터가 아닐 경우
		if(!isMultipart) {
			System.out.println("파일 업로드(multipart) 데이터 형식이 아닙니다");
			
			//파일 업로드 처리 종료 (실패)
			return;
		}
		
		//2. 업로드된 데이터를 처리하는 방법을 설정한다
		//반드시 org.apache.commons 를 import해야 한다
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//3. FileItem을 메모리에서 처리할 용량 크기
		int maxMem = 1 * 1024 * 1024; // 1 MB (1048576 bytes)
		
		factory.setSizeThreshold( maxMem );
		
		//4. 메모리 처리 사이즈보다 크면 임시 파일을 만들어서 처리한다
		//임시 파일을 저장할 폴더를 설정할 수 있다.
		
		//서블릿 컨텍스트 객체
		// -> 요청받은 정보를 처리하고 있는 서블릿의 컨텍스트 환경을 확인/처리할 수 있다
		ServletContext context = req.getServletContext();
		
		//서버에 배포된 서블릿의 실제 서버 경로를 이용하여 tmp 경로를 지정한다
		String path = context.getRealPath("tmp");
		System.out.println("FileService fileupload() - tmp경로 : " + path );

		//실제 서버 경로는 리눅스 서버에 직접 배포할때 알고 있어야만 한다!

		//빈 폴더는 이클립스 톰캣에서 자동 배포해주지 않는다
		//그리고, 이클립스 톰캣 자동배포 서버 경로는 개발자가 직접 건들지 말자
		//직접 건들면 오류가 발생할 수 있다
		
		//** 인텔리제이의 경우 배포 설정이 이클립스에 비해 복잡하다.
		
		//임시 파일을 저장할 폴더의 File객체
		File tmpRepository = new File(path);
		
		//임시 저장소 폴더 생성하기
		// -> .mkdir() 메소드는 대상 폴더가 있으면 생성하지 않고
		// 폴더가 없을 경우 생성한다
		tmpRepository.mkdir();

		//임시 파일을 저장할 폴더를 팩토리 객체에 설정하기
		factory.setRepository(tmpRepository);
		
		//5. 파일 업로드를 수행하는 객체를 설정하며 생성하기
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//최대 업로드 허용 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10 MB
		upload.setFileSizeMax( maxFile );
		
		//--- 파일 업로드 준비 완료 ---
		
		//6. 파일 업로드 처리
		// -> 전달 파라미터 추출하기
		
		List<FileItem> items = null;
		try {
			
			//요청 정보 객체에 담겨있는 전달 파라미터를 추출한다
			// -> 폼필드, 파일 모두 파싱한다
			items = upload.parseRequest(req);
			//전달 파라미터 3개를 파싱해서 꺼내줄 것이다
			//반환형은 List<FileItem>
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 확인
		// List니까 for-each 구문 사용!
		// 이때 1-10 MB 사이의 파일로 테스트해본다
		for( FileItem item : items ) {
			System.out.println("FileService fileupload() - item : " + item);
		}
		
		//7. 파싱된 전달 파라미터 데이터 분류 처리하기
		
		//DTO객체 정의
		// -> List<FileItem> 객체에 "파일" 과 "폼필드" 데이터가 들어있다
		
		//폼필드 전달 파라미터를 저장할 DTO객체 - 작성글
		Board param = new Board();

		//파일 정보를 저장할 DTO객체 - 첨부파일
		BoardFile boardFile = null;
		
		//첨부 파일 삭제 여부
		boolean isDel = false;
		
		//7-1. 폼필드
		//전달된 데이터를 DB에 INSERT한다( Board TB )
		
		//파일 아이템 리스트의 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			
			//전달 파라미터를 저장한 FileItem 객체
			FileItem item = iter.next(); // 아이템이 3개이므로 3번 반복
			//전달 파라미터 title, content, upfile(폼태그 파일인 write.jsp에 있음)

			//빈 파일에 대한 처리 (용량이 0 Byte)
			if ( item.getSize() <= 0 ) {
			//버그를 줄이기 위해 부정조건으로 보통 적는다 -> 0Byte보다 작은 경우까지 포함
				
				continue; //break하면 중단되어 버리므로 continue문을 통해 빈 파일만 무시하고
			//다음 FileItem 처리로 넘어간다
			}
			
			//폼필드에 대한 처리
			if ( item.isFormField() ) {
				//req.getParameter()를 대체하는 코드

				//폼 필드 데이터는 key=value 쌍으로 전달된다
				//name=value (둘다 String타입)
				
				// name은 item.getFieldName(); 메소드로 추출한다
				// value는 item.getString(); 메소드로 추출한다
				
				// **item.getString("UTF-8"); 을 이용하여 한글 인코딩을 설정한다
				
				//name 추출하기
				String name = item.getFieldName();
				
				//value 추출하기(한글 인코딩 설정 포함)
				String value = null;
				
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			
				//전달파라미터의 name에 맞춰 DTO 객체에 value 저장하기
				if( "title".equals(name) ) {
					param.setTitle(value);
				} else if( "content".equals(name) ) {
					param.setContent(value);
				} else if( "boardno".equals(name) ) {
					param.setBoardno(Integer.parseInt(value) );
				}
				
				//첨부파일 삭제 체크 여부 설정하기
				if( "delCheck".equals(name) ) {
					if( "y".equals(value) ) {
						isDel = true;
					}
				}
				
			} // ( if !item.isFormField() ) end	
				
			// 7-2. 파일
			// 파일은 디스크에 저장한다
			// 파일의 정보를 DB에 INSERT 한다 ( uploadfile TB )
			
			//파일에 대한 처리
			if( !item.isFormField() ) {
				
				boardFile = new BoardFile();
				
				//업로드된 파일을 서버의 로컬 디스크의 폴더에 저장한다
				//파일의 이름을 원본과 다르게 바꿔서 저장하는데
				//이때 DB에 원본 파일의 이름을 기억하고 바꾼 파일 이름과 같이 저장한다
				//DB 테이블에 원본 이름, 바꿔서 저장한 이름 모두 INSERT한다
				
				//파일 이름을 바꾸는 방법에는 여러 가지가 있으나, 여기서는 날짜/시간 이용한다
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				
				//** SimpleDateFormat의 메소드
//				String Date포맷 문자열 = sdf.format( Date 타입 );
				
//				Date 변환된 Date = sdf.parse( "Date포맷 문자열" );
				
				//저장될 파일 이름 생성하기
				String rename = sdf.format( new Date() );
				
				boardFile.setOriginName(item.getName());
				boardFile.setStoredName(rename);
				
				boardFile.setFilesize((int)item.getSize());
				
				//fileno - sequence방식으로 처리
				//originname, storedname은 이미 처리
				//나머지는 Foreign Key로 쓸 예정
				
				//** 업로드된 임시 파일을 실제 저장소 폴더로 옮긴다

				//실제 저장하는 파일 저장소(폴더) 생성
				File uploadFolder = new File ( context.getRealPath("upload") );
				uploadFolder.mkdir();
				
				//실제 저장할 파일 객체
				File up = new File(uploadFolder, rename);
				
				//여기서 item은 임시파일을 지칭
				try {
					//임시파일(item객체)을 실제 업로드 파일(up)으로 출력한다
					//Stream이어서 try-catch가 있음
					item.write(up);
					
					//임시 파일을 삭제한다
					item.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} // if( !item.isFormField() ) end
			
		} //while( iter.hasNest() ) end
		
		//8. DB에 최종 데이터 삽입하기
		// -> factory 객체는 멤버필드인 SqlSessionFactory 객체
		SqlSession sqlSession = this.factory.openSession();

		//DAO객체 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		int res = 0;
		
		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//세션의 로그인 정보로부터 게시글의 작성자 아이디 정보 삽입
		param.setUserid( String.valueOf( httpSession.getAttribute( "userid" ) ) );
		
		//게시글 데이터 수정하기
		res += boardDao.update( param );
		
		//첨부 파일 삭제
		if( isDel ) {
			boardFileDao = sqlSession.getMapper(BoardFileDao.class);
			
			//업로드된 실제 파일 삭제하기
			List<BoardFile> fileList = boardFileDao.selectAllByBoardno( param );
			for( BoardFile bf : fileList ) {
				File file = new File( context.getRealPath("upload"), bf.getStoredName() );
				file.delete();
			}
			
			//업로드된 DB 파일 정보 삭제하기
			boardFileDao.delete( param );
		}
		
		//첨부파일이 존재할 때
		if( boardFile != null ) {
			//DAO객체 연결
			boardFileDao = sqlSession.getMapper(BoardFileDao.class);

			//FK 값 연결하기
			boardFile.setBoardno( param.getBoardno() );

			//첨부파일 데이터 삽입하기
			res += boardFileDao.insert( boardFile );
		}
		
		//트랜잭션 관리
		if( boardFile != null && res < 2 ) {
			sqlSession.rollback();
		} else if( boardFile == null && res < 1 ) {
			sqlSession.rollback();
		} else {
			sqlSession.commit();
		}
	
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		
	}

	@Override
	public void delete(HttpServletRequest req) {
		// 게시글 번호 얻어오기
		Board boardno = this.getBoardno(req);
		
		ServletContext context = req.getServletContext();
		
		//SQL 접속
//		SqlSession sqlSession = this.factory.openSession();
		SqlSession sqlSession = factory.openSession();
		
		//DAO객체 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		boardFileDao = sqlSession.getMapper(BoardFileDao.class);
		
		//업로드된 실제 파일 삭제하기
		List<BoardFile> fileList = boardFileDao.selectAllByBoardno( boardno );
		for(BoardFile bf : fileList ) {
			File file = new File( context.getRealPath("upload"), bf.getStoredName() );
			file.delete();
		}
		
		//첨부 파일 삭제
		boardFileDao.delete(boardno);
		
		//게시글 삭제
		boardDao.delete(boardno);
		
		//트랜잭션 관리
		sqlSession.commit();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
	}

	@Override
	public boolean checkUserid(HttpServletRequest req) {
		//로그인한 사용자의 userid
		String loginid = (String) req.getSession().getAttribute("userid");
		
		//게시글 번호 얻어오기
		Board param = this.getBoardno(req);
		
		//SQL 접속
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		boardDao = sqlSession.getMapper(BoardDao.class);
		
		//게시글 정보 얻어오기
		Board board = boardDao.selectBoardByBoardno(param);
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//로그인한 사용자와 작성자 비교
		if( loginid.equals( board.getUserid() ) ) {
			return true;
		}
		return false;
	}


}
