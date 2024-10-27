package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//이때 필터는 반드시 servlet.Filter!
//이후 클래스 이름에서 에러가 나는데 이때 추상 메소드 상속이므로 Add Unimplements ~ 클릭한다.
public class EncodingFilter implements Filter {
       
	public void doFilter(
			
			ServletRequest req, //요청정보 객체
			ServletResponse resp, //응답정보 객체
			FilterChain chain //필터 체인 객체
			
			)
					throws IOException, ServletException {

		System.out.println("EncodingFilter doFilter() - 실행");
		//--------------------------------------------------------------
		
		//서블릿으로 가기 전
		System.out.println("--- 서블릿 동작 이전 ---");
		
		//한글 인코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		//여기서 서블릿으로 넘어간다
		//필터 체인
		// -> 요청 정보를 다음으로 보낸다
		// -> 다음 필터 또는 서블릿이 동작한다
		chain.doFilter(req, resp);
		
		//서블릿으로 넘어간 후
		System.out.println("--- 서블릿 동작 이후 ---");
	}

}
