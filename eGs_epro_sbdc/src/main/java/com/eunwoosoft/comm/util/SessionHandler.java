package com.eunwoosoft.comm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;

/**
 * SessionHandler
 * 
 * @author : 
 * @date : 2014. 11. 11.
 * @version : 1.0
 */
public class SessionHandler extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception
		  {
		    HttpSession session = request.getSession();
		    
		    StringBuffer arr = request.getRequestURL();
		    String isOpro = "";
		    //도메인에 특정 주소 포함여부 확인 econt , econtract 등
//		    if(arr.toString().contains("8080")) {     로컬에서 테스트    
		    if(arr.toString().contains("econt.")) {    
		       isOpro = "Y";
		    }else{
		       isOpro = "N";
		    }
		    
		    if("Y".equals(isOpro)){
			    if (session == null) {
			      response.sendRedirect("/opro/main/emgncLoginForm.do");
			      return false;
			    }
			    
			    FwkDataEntity login = (FwkDataEntity)session.getAttribute("loginResult");
	
			    if (login == null) {
			      response.sendRedirect("/opro/main/emgncLoginForm.do");
			      return false;
			    }
			    
		    }else if("N".equals(isOpro)){
		    	
		    	if (session == null) {
				      response.sendRedirect("/main/emgncLogoutForm.do");
				      return false;
				    }
				    
				   
				    FwkDataEntity login = (FwkDataEntity)session.getAttribute("loginResult");
		
				    if (login == null) {
				      response.sendRedirect("/main/emgncLogoutForm.do");
				      return false;
				    }
				    
		    }
		    return true;
		  }	
}
