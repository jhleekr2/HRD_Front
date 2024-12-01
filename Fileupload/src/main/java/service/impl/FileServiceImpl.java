package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.ParamdataDao;
import dao.face.UploadfileDao;
import dao.impl.ParamDaoImpl;
import dao.impl.UploadfileDaoImpl;
import dto.Paramdata;
import dto.Uploadfile;
import service.face.FileService;

public class FileServiceImpl implements FileService {
	
	//DAO 객체
	private ParamdataDao paramdataDao = new ParamDaoImpl();
	private UploadfileDao uploadfileDao = new UploadfileDaoImpl();

	@Override
	public boolean fileupload(HttpServletRequest req) {
		System.out.println("FileService fileupload() 호출");
		//--------------------------------------------------------------------------
		
		//1. 파일업로드 형식의 인코딩이 맞는 지 검사한다
		// -> 요청 메시지의 content-type이 multipart.form-data인지 확인
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart 데이터가 아닐 경우
		if(!isMultipart) {
			System.out.println("파일 업로드(multipart) 데이터 형식이 아닙니다");
		
			//파일 업로드 처리 종료 (실패)
			return false;
		}
		
		//--------------------------------------------------------------------------

		//2. 업로드된 데이터를 처리하는 방법을 설정한다
		//반드시 org.apache.commons. 를 import해야 한다
		
		// FileItem
		// 클라이언트가 전송한 전달 파라미터들을 객체로 표현한 것
		// FileItem 객체는 전달 파라미터 1개를 표현한다
		// 폼필드(form-field), 파일(file)
		
		// 자바에서 ~~~Factory -> Factory앞에 붙은 ~~~를 만드는 역할 
		// FileItemFactory
		// FileItem객체를 생성하는 방식을 설정해두는 클래스
		// FileItem객체를 생성할 때 필요한 설정 항목을 저장한다
		// 초기화될 멤버 필드의 값, 필요한 라이브러리 설정 등을 기록해둔다 
		
		// FileItem Factory -> Builder -> FileItem 순 으로 파일이 만들어진다
		// 이를 팩토리 빌더 패턴이라 한다
		
		// DiskFileItemFactory
		// 하드 디스크(HDD, SSD) 기반으로 FileItem을 처리하는 팩토리 클래스
		// 업로드된 파일을 하드 디스크에 임시 저장하여 처리한다
		// 파일의 용량이 작으면 메모리에서 처리
		// 파일의 용량이 크면 하드 디스크에서 처리
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//--------------------------------------------------------------------------
		
		//3. FileItem을 메모리에서 처리할 용량 크기
		
//		int maxMem = 1,073,741,824;
//		int maxMem = 1048576;
		int maxMem = 1 * 1024 * 1024; // 1 MB
//		시간, 날짜, 용량 같은 것을 적을때는 직접 계산 안하고 계산식을 그대로 적는다
//		그렇게 함으로써 개발할때도 편하고 유지보수할때도 편해진다
		factory.setSizeThreshold( maxMem );
		
		//--------------------------------------------------------------------------
		
		//4. 메모리 처리 사이즈보다 크면 임시 파일을 만들어서 처리한다
		// 임시 파일을 저장할 폴더를 설정할 수 있다
		
		//서블릿 컨텍스트 객체
		// -> 요청받은 정보를 처리하고 있는 서블릿의 컨텍스트 환경을 확인/처리할 수 있다
		ServletContext context = req.getServletContext();
		
		//서버에 배포된 서블릿의 실제 서버 경로를 이용하여 tmp 경로를 지정한다
		String path = context.getRealPath("tmp");
		System.out.println("FileService fileupload() - tmp경로 : " + path );

//		실제 서버 경로(학원 컴퓨터 기준)
//		D:\workspace_web
//		\.metadata\.plugins\org.eclipse.wst.server.core
//		\tmp1\wtpwebapps //이클립스 파일 폴더
//		\Fileupload //Context 폴더 -> 이클립스 webapp 폴더
//		\tmp 
		
		//실제 서버 경로는 리눅스 서버에 직접 배포할때 알고 있어야만 한다!
		
		//빈 폴더는 이클립스 톰캣에서 자동 배포해주지 않는다
		//그리고, 이클립스 톰캣 자동배포 서버 경로는 개발자가 직접 건들지 말자
		//직접 건들면 오류가 발생할 수 있다
		
		//임시 파일을 저장할 폴더의 File객체
		File tmpRepository = new File(path);
		
		//임시 저장소 폴더 생성하기
		// -> .mkdir() 메소드는 대상 폴더가 있으면 생성하지 않고
		// 폴더가 없을 경우 생성한다
		tmpRepository.mkdir();
		
		//임시 파일을 저장할 폴더를 팩토리 객체에 설정하기
		factory.setRepository(tmpRepository);
		
		//--------------------------------------------------------------------------

		//5. 파일 업로드를 수행하는 객체를 설정하며 생성하기
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//최대 업로드 허용 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10 MB
		upload.setFileSizeMax( maxFile );
		//설정 안할 경우에는 서버 용량이 금방 부족해진다.
		//통상적으로 서버의 파일 업로드 허용 사이즈는 5~20 MB 정도로 설정한다.
		//사실 10 MB정도만 되도 업로드 허용 사이즈가 꽤 커서 디도스 공격에 취약해진다
		//일반 사이트의 경우 동시접속자 - 일반적으로 5-6명 이하
		//동시접속자 100명정도 사이트 - 굉장히 크고 흥한 사이트
		//실제 서버의 경우에는 파일을 저장하는 파일서버를 따로 둔다
		//물론, 일부 케이스의 경우에는 BLOB를 이용하여 DB에 파일을 저장하는 경우도 있다
		
		//--------------------------------------------------------------------------

		//--- 파일 업로드 준비 완료 ---
		
		//--------------------------------------------------------------------------
		
		//6. 파일 업로드 처리
		// -> 전달 파라미터 추출하기
		
		List<FileItem> items = null;
		try {
			
			//요청 정보 객체에 담겨있는 전달 파라미터를 추출한다
			// -> 폼필드, 파일 모두 파싱한다
			items = upload.parseRequest(req);
			//전달 파라미터 4개를 파싱해서 꺼내줄 것이다
			//반환형은 List<FileItem>
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//전달 파라미터 확인
		// List니까 for-each 구문 사용해보자
		// 이때 1-10 MB 사이의 파일로 테스트해본다
		for(FileItem item : items) {
			System.out.println("FileService fileupload() - item : " + item);
		}
		
		//여기까지 진행하고 테스트하면
		//java.lang.ClassNotFoundException: org.apache.commons.io.IOUtils 에러가 날 것이다
		
		//https://commons.apache.org/proper/commons-fileupload/using.html
		//사이트의 기술문서 읽어보면
		//'FileUpload depends on Commons IO' 라는 이야기가 나온다
		
		//-> Commons IO 파일을 아파치 사이트에서 다운로드 해서 추가로 lib에 넣어줘야 한다
		//--------------------------------------------------------------------------

		//7. 파싱된 전달 파라미터 데이터 분류 처리하기
		// -> List<FileItem> 객체에 "파일" 과 "폼필드" 데이터가 들어있다
		
		// ** FileItem에 저장된 전달 데이터의 유형
		// - 용량이 0인 파일, 빈 파일 (Empty File)
		// 파일을 무시한다(잘못 올렸거나, 악의적인 목적이 있는 것으로 간주)
		// ex)웹하드 사이트에서 0바이트 파일의 업로드가 안되기 때문에 폴더명을 이용해
		// 공지사항을 작성한다
		
		// - 폼필드
		// 전달된 데이터를 DB에 INSERT한다 ( Paramdata TB )
		
		// - 파일
		// 파일은 디스크에 저장한다
		// 파일의 정보를 DB에 INSERT 한다 ( uploadfile TB )
		
		// ** 폼필드와 파일은 기본적으로 RAM에 저장되지만
		// 파일의 크기에 따라
		// 0 Byte - 무시
		// 0 Byte 초과 ~ 1MB 미만 - 메모리 처리
		// 1MB이상 ~ 10MB 미만 - 디스크 처리
		// 10MB이상 - 예외 발생
		// 하도록 세팅해 놓았다
		
		//폼필드 전달 파라미터를 저장할 DTO객체
		Paramdata paramdata = new Paramdata();
		
		//파일 정보를 저장할 DTO객체
		Uploadfile uploadfile = new Uploadfile();
		
		//파일 아이템 리스트의 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			
			//전달 파라미터를 저장한 FileItem 객체
			FileItem item = iter.next(); // 아이템이 4개이므로 4번 반복할거임
			//전달 파라미터 title, data1, data2, upfile(폼태그 파일인 fileupload.jsp에 있음)
			//** 전달 파라미터 같은 것을 서로 못알아봐서 프로젝트에서 빈번하게 싸움이 일어난다
			//-> 만일, 서로간에 전달파라미터 문제로 감정이 쌓인다면 즉시 강사에게 도움요청하자!
			
			//빈 파일에 대한 처리 (용량이 0B )
			if ( item.getSize() <= 0 ) {
				//버그를 줄이기 위해 부정조건으로 보통 적는다 -> 0Byte보다 작은 경우까지 포함
				
				continue; //break하면 중단되어 버리므로 continue문을 통해 빈 파일만 무시하고
				//다음 FileItem 처리로 넘어간다
			}
			
			//폼필드에 대한 처리
			if ( item.isFormField() ) {
				//req.getParameter()를 대체하는 코드가 들어갈 것이다
				
				//폼 필드 데이터는 key=value 쌍으로 전달된다
				//name=value (둘다 String타입)
				
				// name은 item.getFieldName(); 메소드로 추출한다
				// value는 item.getString(); 메소드로 추출한다
				
				// **item.getString("UTF-8"); 을 이용하여 한글 인코딩을 설정한다
				
				//--------------------------------------------------------------------------
				
				//name 추출하기
				String name = item.getFieldName();

				//value 추출하기
//				String value = item.getString(); // 한글이 깨진다. 문제 해결 필요
				String value = null;
				
				try {
					//한글 인코딩 설정하기
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				System.out.println("FileService fileupload() - name : " + name);
				System.out.println("FileService fileupload() - value : " + value);
				
				//--------------------------------------------------------------------------
				
				//전달파라미터의 name에 맞춰 DTO 객체에 value 저장하기
				if( "title".equals(name) ) {
					paramdata.setTitle(value);
				} else if( "data1".equals(name) ) {
					paramdata.setData1(value);
				} else if( "data2".equals(name) ) {
					paramdata.setData2(value);
				}
				
			} // if ( item.isFormField() ) end
			
			//파일에 대한 처리
			if( !item.isFormField() ) {
				//업로드된 파일을 서버의 로컬 디스크의 폴더에 저장한다
				//파일의 이름을 원본과 다르게 바꿔서 저장한다
				//(다른 사람이 올린 이름만 같은 다른 파일과 충돌나는 것을 피하기 위해서)
				
				//이때 DB에 원본 파일의 이름을 기억하고 바꾼 파일 이름과 같이 저장한다
				//DB 테이블에 원본 이름, 바꿔서 저장한 이름 모두 INSERT한다
				
				//파일이름 바꾸는 방법
				//1. 파일이름(1),(2) 등등 숫자로 관리
				
				//2. 파일이름을 랜덤으로 생성 - 관련 API가 존재
				
				//3. 날짜/시간으로 관리 - 동시접속 상황에서 맹점이 존재
				
				//여기서는 날짜/시간을 이용해본다
				
				//날짜/시간 -> 문자열 변환 (java.util.Date -> String 변환)
				// -> SimpleDateFormat 클래스 이용
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				
				//** SimpleDateFormat의 메소드
//				String Date포맷 문자열 = sdf.format( Date타입 );
				
//				Date 변환된 Date = sdf.parse( "Date포맷 문자열" );
				
				String rename = sdf.format( new Date() );
				
				//--------------------------------------------------------------------------
				
				System.out.println("FileService fileload() - 원본파일명 : " + item.getName());
				System.out.println("FileService fileload() - 원본파일명 : " + rename);
				
				uploadfile.setOriginName(item.getName());
				uploadfile.setStoredName(rename);
				
				//fileno - sequence방식으로 처리
				//originname, storedname은 이미 처리
				//나머지는 Foreign Key로 쓸 예정
				//--------------------------------------------------------------------------
				
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
			
		} // while( iter.hasNext() ) end
		
		// 코드가 길어지면 중괄호 닫는 것과 관련하여 혼동이 발생한다
		// 따라서 들여쓰기를 잘하는 습관과, 중괄호 닫는 위치관련 주석을 다는 습관이 필요하다
		
		System.out.println("FileService fileload() - " + paramdata);
		System.out.println("FileService fileload() - " + uploadfile);
		
		//--------------------------------------------------------------------------
		
		//8. DB에 최종 데이터 삽입하기
		Connection conn = JDBCTemplate.getConnection();
		
		//datano 값 조회하기 - 시퀀스
		int datano = paramdataDao.selectNextDatano( conn );
		paramdata.setDatano(datano);
		
		//조회결과 테스트 데이터
//		System.out.println("LAST_NUMBER : " + datano);
		//폼필드 데이터 삽입하기
		int res1 = paramdataDao.insert( conn, paramdata );
		
		//FK값 연결하기
		uploadfile.setDatano( datano );
		
		//fileno 값 조회하기 - 시퀀스 - 이쪽이 PK
		int fileno = uploadfileDao.selectNextFileno( conn );
		uploadfile.setFileno( fileno );
		
		int res2 = uploadfileDao.insert( conn, uploadfile );
		//--------------------------------------------------------------------------
		
		// 파일을 업로드하지 않아도 DB에 null로 datano가 추가되는 논리적 오류가 있다

		//해결방안 
		//null두고 파일에 대한 처리과정에서 만들면 DB데이터 삽입부분에서
//		if(uploadfile != null) {
			
//		}
		//로 해결 가능
		
		if( res1 + res2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//파일 업로드 정상 종료 (성공)
		return true;
	}

	@Override
	public List<Uploadfile> list() {
		System.out.println("FileService list() - 호출");
		
		//DB연결 객체 - JDBCTemplate 객체 이용
//		Connection conn = JDBCTemplate.getConnection();
		
		//uploadfile테이블 전체 조회 - UploadfileDao 객체 이용
//		List<Uploadfile> list = uploadfileDao.selectAll(conn);
		
		//조회 결과 반환
//		return list;
		
		//위의 코드를 한줄로 쓰면
		return uploadfileDao.selectAll( JDBCTemplate.getConnection() );
		//메소드 호출할때 에러가 났었는데 DAO객체 정의할때 소문자로 했기 때문에 소문자 호출
	}

	@Override
	public Paramdata info(Paramdata datano) {
		System.out.println("FileService info() - 호출");
		
		//DB연결 객체 - JDBCTemplate 객체 이용
		Connection conn = JDBCTemplate.getConnection();
		
		//uploadfile 테이블 조회 - paramdataDao 객체 이용
		Paramdata info = paramdataDao.selectByDatano(conn, datano);
		
		//조회 결과 반환
		return info;
	}

	@Override
	public Paramdata getParamdata(HttpServletRequest req) {

		String datanoParam = req.getParameter("datano");
		
		int datano = 0;
		if( datanoParam!=null && !"".equals(datanoParam) ) {
			datano = Integer.parseInt(datanoParam);
		}
		
		Paramdata paramdata = new Paramdata();
		paramdata.setDatano(datano);
		paramdata.setTitle(req.getParameter("title"));
		paramdata.setData1(req.getParameter("data1"));
		paramdata.setData2(req.getParameter("data2"));
		
		return paramdata;
	}

	@Override
	public void edit(Paramdata paramdata) {
		Connection conn = JDBCTemplate.getConnection();
		
		int res = paramdataDao.update(conn, paramdata);
		
		if(res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
}
