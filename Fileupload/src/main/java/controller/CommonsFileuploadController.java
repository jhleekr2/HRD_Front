package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.FileService;
import service.impl.FileServiceImpl;

@WebServlet("/commons/fileupload")
public class CommonsFileuploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private FileService fileService = new FileServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/commons/fileupload [GET]");
		
		//View 지정하고 포워드
		req.getRequestDispatcher("/WEB-INF/views/commons/fileupload.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/commons/fileupload [POST]");
		
		// ** multipart/form-data 형식으로 요청을 하면
		// 쿼리스트링 형식의 전달파라미터를 추출하는
		// .getParameter("name") 메소드는 null값을 반환한다
		
		// -> multipart 형식에 맞는 파라미터 처리가 필요하다
		// -> Commons-Fileupload 라이브러리 이용 필수
		
//		System.out.println("title : " + req.getParameter("title"));
//		System.out.println("upfile : " + req.getParameter("upfile"));
		
		//--------------------------------------------------------------------------------
		
		//파일업로드 라이브러리를 이용한 파일 업로드 처리
		boolean result = fileService.fileupload( req );
		System.out.println("CommonsFileuploadController doPost() - result : " + result);
		
		//--------------------------------------------------------------------------------
		
		//파일 업로드 처리 실패
		if( !result ) {
			req.getRequestDispatcher("/WEB-INF/views/commons/error.jsp").forward(req, resp);
			
			return; //둘중에 하나만 하기 위해 추가
		}
		
		//응답 화면 리다이렉트
		resp.sendRedirect("/file/list"); //FilelistController.java라는 서블릿 추가 필요
		
		

	}
}
