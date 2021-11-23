<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>

<html lang="ko">
	<head>
		<tiles:insertAttribute name="head" />		
	</head>
	<body>
		<tiles:insertAttribute name="body.contents" />
	</body>
</html>