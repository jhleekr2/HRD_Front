package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Uploadfile;
import service.face.FileService;
import service.impl.FileServiceImpl;

@WebServlet("/file/list")
public class FilelistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private FileService fileService = new FileServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/file/list [GET]");
		//------------------------------------------------------------
		
		//파일 리스트 조회
		List<Uploadfile> list = fileService.list();
		
		for( Uploadfile u : list ) {
			System.out.println(u);
		}		
		
		
		//조회된 결과(List<Uploadfile>)를 View(JSP파일)에 전달한다
//		req.setAttribute("전달할 변수의 이름", 전달할 객체);
		
		//View(JSP)에 MODEL값 전달하기
		req.setAttribute("list", list);
		
		//View(JSP)로 포워드(forward)
		//이때 JSP파일은 반드시 views폴더 아래에 만든다
		//여기서는 file/list 폴더에 서블릿이 있으므로, /file/list.jsp 로 파일을 만든다.
		req.getRequestDispatcher("/WEB-INF/views/file/list.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
