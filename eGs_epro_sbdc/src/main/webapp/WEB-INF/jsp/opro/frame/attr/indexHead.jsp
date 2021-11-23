<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>중소기업유통센터 비대면 평가관리 시스템</title>
<c:set var="requestURL" value="${pageContext.request.requestURL}" scope="application" />
<c:if test="${fn:indexOf(requestURL , '/bid/') >-1 }">
    <c:set var="contextPath" value="/bid" scope="application" />
</c:if>
<c:if test="${fn:indexOf(requestURL , '/bid/') < 0 }">
    <c:set var="contextPath" value="" scope="application" />
</c:if>

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
<script type="text/javascript" src="${contextPath}/statics/libs/slick.min.js"></script>

<!-- 공통css -->
<script type="text/javascript" src="${jsPath}/opro/views/comm/comUtil.js"></script>

<!-- 중소기업유통센터 css -->
<link rel="stylesheet" href="${cssPath}/comm/main_sbdc.css">
<link rel="stylesheet" href="${cssPath}/comm/slick_sbdc.css">