package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
    
	//강사의 코드에서 Warning이 발생하여 경고 억제용 코드 추가
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// 한글 인코딩 처리 : UTF-8
		req.setCharacterEncoding("UTF-8");
		
		chain.doFilter(req, resp);
	}

}
