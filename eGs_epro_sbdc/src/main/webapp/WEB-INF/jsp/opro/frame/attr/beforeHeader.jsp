<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/frame/bodyHeader.js"></script> 

<div class="wrap-page-sub">
	<!-- Header -->
	<div class="layout-header ">
		<div class="wrap-header">
			<h1 class="img-logo" onclick="beforeLoginPage();" style="cursor: pointer; margin-left: 99px;"></h1>
			<!-- User -->
		</div>
	</div>
	<!-- Header -->


	<form id="menuLeftMoveFrm" method="post">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
	</form>
	
	<form id="logOutFrm" method="post">
	</form>
	
	<form id="trmFrm" method="post">
		<input type="hidden" name="P_TTL_NM">	
	</form>