<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="title" content="중소기업유통센터">
<meta name="description" content="중소기업유통센터">
<meta name="keywords" content="중소기업유통센터"> 
<title>중소기업유통센터</title>
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
<!-- CSS  -->     
<link rel="stylesheet" type="text/css" href="${cssPath}/opro/base.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/opro/layout.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/opro/common.css">	
<link rel="stylesheet" type="text/css" href="${cssPath}/opro/contents.css">	

<!-- JS -->
<script src="${jsPath}/jquery-1.12.2.min.js"></script>
<script src="${jsPath}/jquery.bxslider.js"></script>
<script src="${jsPath}/common.js"></script>
<script src="${jsPath}/jquery-ui.js"></script>

<script type="text/javascript" src="${jsPath}/commonLayerControl.js"></script>
<script type="text/javascript" src="${jsPath}/select.js"></script>
<script type="text/javascript" src="${jsPath}/tapMenu.js"></script>
<script type="text/javascript" src="${jsPath}/archiveMenu.js"></script>

<script type="text/javascript" src="${contextPath}/statics/libs/json2.min.js"></script>
<script type="text/javascript" src="${contextPath}/statics/libs/jquery-serialize-object.js"></script>

<script type="text/javascript" src="${contextPath}/statics/fwk/CmmnUtil.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/Common.js"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/DateUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${contextPath}/statics/fwk/MssageUtil.js"></script>

