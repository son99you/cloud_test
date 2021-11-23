<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="agentInfo.jsp"%>
<%
    String resultCode = session.getAttribute("resultCode") == null ? "" : session.getAttribute("resultCode").toString();
    String resultMessage = session.getAttribute("resultMessage") == null ? "" : session.getAttribute("resultMessage").toString();
    boolean isErrorPage = session.getAttribute("isErrorPage") == null ? false : (Boolean)session.getAttribute("isErrorPage");

    System.out.println("isErrorPage :  " + isErrorPage);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <%
    if(isErrorPage == true || !resultCode.equals("000000")) {
    %>
        <p>토큰검증이 실패하였습니다.</p>
        <p><%=resultCode%></p>
    
    <%    
    } else {
    	%>
    	 Login Success!!
    	<%
    	//SSO 인증 성공 후 아래 예시를 참조하여 순서대로 처리.
    	
    	//1. 업무시스템 로그인처리 페이지로 이동
    	//ex)
    	response.sendRedirect("/main/ssoEmplyrLogin.do");
    	
    	//2. 로그인처리 페이지에서 사용자 사번 값 사용하여 업무시스템 로그인 세션 생성
    	//** 운영 전환 시 세션 키값이 변경될 수 있습니다.
    	//ex) 사번 값 사용
    	//String userid = session.getAttribute("SSO_ID") == null ? "" : session.getAttribute("SSO_ID").toString();
    	
    	//3. 로그인처리 페이지에서 returnURL 처리
    	//ex) 세션에 저장된 returnURL값 사용
    	//String returnURL = session.getAttribute("returnURL") == null ? "" : session.getAttribute("returnURL").toString();
    	
    	//ex) returnURL값이 있는 지 확인 후 있으면 해당 경로로 이동하고 없으면 메인페이지로 이동
		//if (!returnURL.equals("")) {
	    //session.setAttribute("returnURL", "");
		//response.sendRedirect(returnURL);
		//return;
		//}
		//response.sendRedirect("/main/mainPage.do");
    	
        %>
    <%    
    }
    %>
</body>
</html>
