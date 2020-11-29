package cn.itsqh.ydma.util;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrosFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse hresponse=(HttpServletResponse)response;
        hresponse.setHeader("tAccess-Control-Allow-Origin","*");
        chain.doFilter(request,response);
    }
}
