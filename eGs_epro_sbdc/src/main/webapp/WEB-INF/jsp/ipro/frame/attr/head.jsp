<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<title>중소기업유통센터 비대면 평가관리 시스템</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />
<c:set var="imagePath" value="${contextPath}/statics/images" scope="application" />
<c:set var="cssPath" value="${contextPath}/statics/css" scope="application" />
<c:set var="jsPath" value="${contextPath}/statics/js" scope="application" />
<c:set var="raonPath" value="${contextPath}/raonkupload" scope="application" />
<c:set var="rkEditorPath" value="${contextPath}/raonkeditor" scope="application" />

<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">

<script type="text/javascript" src="${contextPath}/statics/libs/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/json2.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/jquery-serialize-object.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>

<!--header , footer 영역 기본 script-->
<script type="text/javascript" src="${jsPath}/ipro/views/comm/comUtil.js"></script>

<!-- 중소기업유통센터 공통 CSS -->
<link rel="stylesheet" href="${cssPath}/comm/jquery-ui.css">
<link rel="stylesheet" href="${cssPath}/comm/common_sbdc.css">
<link rel="stylesheet" href="${cssPath}/comm/sub_sbdc.css">