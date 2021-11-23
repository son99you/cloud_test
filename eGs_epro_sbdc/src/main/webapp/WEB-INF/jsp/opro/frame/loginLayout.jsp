<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
	<head>
		<tiles:insertAttribute name="head" />	
	</head>
	<body>
		<div id="wrap" class="wrap">
   			<tiles:insertAttribute name="body.left" />	
			<section id="container">
				<tiles:insertAttribute name="body.contents" />	
		    	<tiles:insertAttribute name="body.footer" />	
			</section> <!--// container E-->
		</div> <!--// wrap E-->
	</body>
</html>