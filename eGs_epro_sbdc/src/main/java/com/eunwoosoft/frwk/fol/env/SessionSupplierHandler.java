/*******************************************************************
 *
 * 
 * Comment 작성
 * 세션 핸들러 클래스
 * 
 ******************************************************************/

package com.eunwoosoft.frwk.fol.env;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;

public class SessionSupplierHandler extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
        HttpSession	session	= request.getSession();
        
        if ( session == null ) {
        	response.sendRedirect("/supplier/erro/session.do");
        	return false;
		}
		
        FwkDataEntity login = (FwkDataEntity) session.getAttribute( "loginResult" );
		
		if ( login == null ) {
			response.sendRedirect("/supplier/erro/session.do");
        	return false;
		}

		return true;
	}
}
