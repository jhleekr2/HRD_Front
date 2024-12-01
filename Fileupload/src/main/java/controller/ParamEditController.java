package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Paramdata;
import service.face.FileService;
import service.impl.FileServiceImpl;

@WebServlet("/param/edit")
public class ParamEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private FileService fileService = new FileServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/param/edit [GET]");
		
		//datano라는 Paramdata형식 참조형 변수를 지정
		Paramdata datano = new Paramdata();
		
		//전달받은 데이터 값 꺼내고, int형으로 형변환 후 datano 참조변수에 대입
		datano.setDatano( Integer.parseInt( req.getParameter( "datano" ) ) );
		System.out.println(datano.getDatano());

		//파라미터 정보 조회
		Paramdata info = fileService.info( datano );
		
		//조회값 출력
		System.out.println(info.getDatano());
		System.out.println(info.getTitle());
		System.out.println(info.getData1());
		System.out.println(info.getData2());
		
		//View(JSP)에 MODEL값 전달하기
		req.setAttribute("info", info);

		//View(JSP)로 포워드(forward)
		//이때 JSP파일은 반드시 views폴더 아래에 만든다
		//여기서는 param/edit 폴더에 서블릿이 있으므로, /param/edit.jsp 로 파일을 만든다.
		req.getRequestDispatcher("/WEB-INF/views/param/edit.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/param/edit [POST]");
		
		Paramdata paramdata = fileService.getParamdata(req);
		
		fileService.edit(paramdata);
		
		Paramdata result = fileService.info(paramdata);

		//DB에 저장되어 있는 데이터 수정
		
		//View(JSP)에 MODEL값 전달하기
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("/WEB-INF/views/param/result.jsp").forward(req, resp);
	}
}
