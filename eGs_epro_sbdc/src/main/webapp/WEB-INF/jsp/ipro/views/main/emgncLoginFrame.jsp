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