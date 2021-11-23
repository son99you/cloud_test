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
<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>
<script type="text/javascript" src="${jsPath}/statics/ipro/frame/common.js"></script>

<!--공통css-->
<link rel="stylesheet" type="text/css" href="${cssPath}/buyerCommon.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/main.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/buyerBoard.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/buyerLayout.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/buyerBase.css">

<!--header , footer 영역 기본 script-->
<script type="text/javascript" src="${jsPath}/commonLayerControl.js"></script>
 
<script type="text/javascript" src="${jsPath}/select.js"></script>
<script type="text/javascript" src="${jsPath}/tapMenu.js"></script>
<script type="text/javascript" src="${jsPath}/archiveMenu.js"></script> 

 
<!-- 2015.01.07 bskim 추가 -->
<link rel="stylesheet" type="text/css" href="${cssPath}/comm/pageContent.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/comm/lnbVerticalStyle.css">
