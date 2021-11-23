<%@ page import="java.net.URLDecoder" %>
<%

	request.setCharacterEncoding("UTF-8");

	String urlName = "returnUrl=";
	String returnUrl = getNextUrl(request.getQueryString(), urlName);

	try {
		returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
	} catch(Exception e) {}
	
	returnUrl = returnUrl.replaceAll("\\$", "\\&");
	System.out.println(" ### returnUrl : "+returnUrl);

	/************************************************************
	 *	ReturnURL 설정
	 ************************************************************/	
	if (!returnUrl.trim().equals("")) session.setAttribute("returnURL", returnUrl);	
	response.sendRedirect("business.jsp");

%>

<%!
	public String getNextUrl(String fullPath, String urlName) {
		if(null == fullPath) return "";
		int idx = fullPath.indexOf(urlName);
		String nextURL = "";
		if(idx > -1)
			nextURL =  fullPath.substring(idx+urlName.length());
		
		return nextURL;
	}
%>  
