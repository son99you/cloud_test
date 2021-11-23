<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	<head>
		<tiles:insertAttribute name="head" />
	</head>
	
	<body>
		<tiles:insertAttribute name="body.header" />
		<!-- Contents -->
		<div class="layout-contents">
			<%-- <tiles:insertAttribute name="body.left" /> --%>
			<tiles:insertAttribute name="body.contents" />
		</div>
		<tiles:insertAttribute name="body.footer" />
	</body>
</html>