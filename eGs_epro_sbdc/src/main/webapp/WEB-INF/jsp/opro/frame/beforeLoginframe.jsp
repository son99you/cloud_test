<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	<head>
		<tiles:insertAttribute name="beforehead" />
	</head>
	
	<body>
		<tiles:insertAttribute name="before.header" />
		<!-- Contents -->
		<div class="layout-contents" style="width: 1100px; margin: 0 auto; padding: 69px 0 50px 0; overflow: hidden;">
			<tiles:insertAttribute name="body.contents" />
		</div>
		<tiles:insertAttribute name="body.footer" />
	</body>
</html>