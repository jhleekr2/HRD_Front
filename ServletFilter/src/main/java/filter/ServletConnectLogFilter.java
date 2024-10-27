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
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ServletConnectLogFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 반드시 형변환이 필요
		HttpServletRequest req = (HttpServletRequest)request;
		
		System.out.println(req.getRequestURI() + " [" + req.getMethod() + "]");
		chain.doFilter(request, response);
		
		// 필터를 잘쓰면 테스트 코드를 줄일 수 있어서 개발이 편해진다
		// 하지만, 현업에서는 필터의 상위호환이 존재해서 자주 사용되지는 않는다
	}
}
