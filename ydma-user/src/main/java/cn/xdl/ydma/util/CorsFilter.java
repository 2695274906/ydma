package cn.xdl.ydma.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//filter过滤器
@WebFilter(servletNames= {"dispatcherServlet"})
public class CorsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse  = (HttpServletResponse)response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Method", "GET,POST");
		System.out.println("老贼，休走");
		//放行静态资源
		chain.doFilter(request, httpResponse);
	}

}
