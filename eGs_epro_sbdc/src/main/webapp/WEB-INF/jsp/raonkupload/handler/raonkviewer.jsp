<%@ page contentType="text/html;charset=utf-8"%><%@ page import="com.raonwiz.kupload.RAONKHandler" %><%
	out.clear();
	
	request.setCharacterEncoding("UTF-8");
	
	RAONKHandler raonk = new RAONKHandler();
	
	// 디버그시 사용(system.out.println 출력)
	raonk.settingVo.setDebugMode(true, "C");
	raonk.settingVo.setDebugMode(true, "L");

	// Viewer 페이지의 Encoding을 설정할 때 사용 (기본값 : utf-8)
	// raonk.SetEncoding("utf-8");

	String result = raonk.Viewer(request, response);

    if(!result.equals("")) {
		out.print(result);
	}
%>