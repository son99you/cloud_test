<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="ko">
	<head>
		<%-- <tiles:insertAttribute name="head" />  --%>
		<script type="text/javascript">
		function print_cont(){
			 window.print();  
		}
		</script>		 
	</head>
	<body onload="print_cont();"> 
    	<!-- <div id="windowPopup"> -->
			<tiles:insertAttribute name="body.contents" />	
		<!-- </div> --> 
	</body>
</html>