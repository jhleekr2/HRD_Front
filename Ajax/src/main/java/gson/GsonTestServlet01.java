package gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/gson/test01")
public class GsonTestServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Gson 객체
		Gson gson = new Gson();
		//--------------------------------------------------------------------
		
		//Java데이터 -> JSON텍스트(마샬링)
//		gson.toJson( 자바객체 ) : String
		
		//JSON텍스트 -> JAVA데이터(언마샬링)
//		gson.fromJson( "JSON텍스트", 자바타입 ) : 자바데이터
		
		//자바 int형 데이터 -> JSON텍스트
		System.out.println( gson.toJson(12345) );
		
		//자바 String형 -> JSON 텍스트
		System.out.println( gson.toJson("Apple")); //"\"Apple\""
		
		//자바 long형 데이터 -> JSON 텍스트
		System.out.println( gson.toJson(5534L) );
		System.out.println( gson.toJson(new Long(5534L)) );
		//--------------------------------------------------------------------
		
		int[] arr= { 1,2,3,4,5 };
		System.out.println( gson.toJson(arr) ); //Array, List -> JSON의 Array
		
		Map<String, Object> map = new HashMap<>();
		System.out.println( gson.toJson(map) ); //Map -> JSON의 Object
		//--------------------------------------------------------------------
		
		//JSON텍스트 -> Java데이터
		//JSON에서 자료형을 뭉개놨기에 JSON텍스트 -> Java데이터로 갈때는 일일히 자료형 지정해야 한다
		int n1 = gson.fromJson("12345", int.class );
		//객체의 자료형을 표현하기 위해 int.class, EmpDao.class와 같이 표현한다

	
	}


}
