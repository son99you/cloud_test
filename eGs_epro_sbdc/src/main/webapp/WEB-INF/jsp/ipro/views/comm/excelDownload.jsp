<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Content-Type", "application/vnd.ms-xls");
	response.setHeader("Content-Disposition", "attachment; filename=myfile.xls");
%>

<!document html>
<html lang="ko">
   <body>
      123
   </body>
</html>

