package com.eunwoosoft.comm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class CORSFilter implements Filter  {
	 
	@SuppressFBWarnings("PERMISSIVE_CORS")  //라온업로드 사용위해 설정 필요
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, RAONK-Encoded");
        response.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(req, res);
        
    }

    public void init(FilterConfig filterConfig) {
    	//초기화
    }

    public void destroy() {
    	//객체 소멸
    }

}
