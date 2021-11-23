<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE HTML>
<html lang="ko">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>중소기업유통센터 비대면 평가관리 시스템</title>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />
		<c:set var="imagePath" value="${contextPath}/statics/images" scope="application" />
		<c:set var="cssPath" value="${contextPath}/statics/css" scope="application" />
		<c:set var="jsPath" value="${contextPath}/statics/js" scope="application" />
		<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/common_login.css">
		<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/login.css">
		
		<script type="text/javascript" src="${contextPath}/statics/libs/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>	
	</head>

	<body id="login-bg">
		<div id="login-wrap">
			<div class="box">
				<tiles:insertAttribute name="body.contents" />	
		    </div>
		</div>
	</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<html lang="ko">
	<head>
		<tiles:insertAttribute name="head" />		
	</head>
	<body>
		<tiles:insertAttribute name="body.header" />	
		<!-- <div id="layout-contents"> -->
		<div id="layout-mcontents">
			<tiles:insertAttribute name="body.contents" />
		</div>	
		<tiles:insertAttribute name="body.footer" />
	</body>
</html>