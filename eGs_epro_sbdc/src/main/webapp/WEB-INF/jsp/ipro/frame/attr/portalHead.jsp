<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">

<title>한국광해관리공단</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />
<c:set var="imagePath" value="${contextPath}/statics/images" scope="application" />
<c:set var="cssPath" value="${contextPath}/statics/css" scope="application" />
<c:set var="jsPath" value="${contextPath}/statics/js" scope="application" />

<script type="text/javascript" src="${contextPath}/statics/libs/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/json2.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/jquery-serialize-object.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>

<link rel="stylesheet" href="${cssPath}/portal/base.css">
<link rel="stylesheet" href="${cssPath}/portal/common.css">
<link rel="stylesheet" href="${cssPath}/portal/layout.css">
<link rel="stylesheet" href="${cssPath}/portal/board.css">
<link rel="stylesheet" href="${cssPath}/portal/searchResult.css">
<link rel="stylesheet" href="${cssPath}/portal/popup.css">
		
<!--header , footer 영역 기본 script-->
<script type="text/javascript" src="${jsPath}/commonLayerControl.js"></script>

<!-- 2015.01.07 bskim 추가 -->
<script type="text/javascript" src="${jsPath}/select.js"></script> 
<link rel="stylesheet" type="text/css" href="${cssPath}/comm/pageContent.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/lnbVerticalStyle.css"> 

<script type="text/javascript" src="${jsPath}/globalParam.js"></script>
<script type="text/javascript" src="${jsPath}/tapMenu.js"></script>
