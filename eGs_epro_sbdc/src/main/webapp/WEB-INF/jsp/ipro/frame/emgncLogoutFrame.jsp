<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>중소기업유통센터 비대면 평가관리 시스템</title>
		<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />
		<c:set var="imagePath" value="${contextPath}/statics/images" scope="application" />
		<c:set var="cssPath" value="${contextPath}/statics/css" scope="application" />
		<c:set var="jsPath" value="${contextPath}/statics/js" scope="application" />
		
		<link rel="stylesheet" href="${cssPath}/comm/common_sbdc.css">
		<link rel="stylesheet" href="${cssPath}/comm/sub_sbdc.css">
		<link rel="stylesheet" href="${cssPath}/comm/main_sbdc.css">
		
		<script type="text/javascript" src="${contextPath}/statics/libs/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>	
	</head>

	<body>
		<div class="wrap-page-sub">
			<tiles:insertAttribute name="body.contents" />
		</div>
	</body>
</html>