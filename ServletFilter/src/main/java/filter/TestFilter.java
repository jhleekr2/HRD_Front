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

//web.xml 없이도 서블릿 필터를 처리할 수 있다
//필터 클래스가 적용될 URL패턴을 지정한다
//필터 클래스 등록도 함께 수행한다
//@WebFilter(value = {"/board/*", "/login/*", "/test/page"})
//@WebFilter(value = {"/*"})
//@WebFilter(value = "/*") //URL-Pattern을 1개만 적용하면 {} 생략 가능
//@WebFilter("/*") //설정 속성이 value만 있다면 value= 생략 가능
//-> 자동 완성시에 나오는 WebFilter 모양 나옴!
//annotation방식이 xml방식보다 서블릿 필터 설정이 더 간단하다
//요즘은 Factory Builder 패턴을 이용한 Java Class Builder방식을 많이 사용한다

//스프링 프레임워크 -> Annotation 방식
//스프링 부트 -> Java Builder 방식

//현재는 세가지 서블릿 필터 설정방식을 혼용해서 쓰고 있다

@WebFilter(value= {"/board/insert"}, filterName="testFilter")
//필터가 적용될 대상으로 서블릿 이름(servletName)을 지정하여 등록한다
//@WebFilter(servletNames = { "boardController", "empController", "loginController" })
//서블릿 이름으로 필터관리는 URL방식에 비해 불편하다

//필터의 이름을 적용해서 등록하기
//-> URL패턴이 지정되지 않았다
//-> web.xml의 <filter-mapping>으로 등록할 수 있다
//@WebFilter(filterName ="필터 이름")

//@WebFilter(filterName = "testFilter")
public class TestFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("TestFilter doFilter() - 실행");
		
		chain.doFilter(req, resp);
	}

}
