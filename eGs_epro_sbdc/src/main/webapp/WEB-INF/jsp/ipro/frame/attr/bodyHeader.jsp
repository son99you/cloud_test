<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/frame/bodyHeader.js"></script>

<div class="wrap-page-sub">
	<!-- Header -->
	<div class="layout-header ">
		<div class="wrap-header">
			<h1 class="img-logo" onclick="mainPage()" style="cursor: pointer;"></h1>
			<!-- User -->
			<div class="area-user">
				${loginResult.USR_NM}님 방문을 환영합니다.
				<a href="javascript:" class="btn-user-login" onclick="javascript:logout();">로그아웃</a>
			</div>
			<!-- //User -->
			<div class="header-menu">
				<ul class="list-menu">
					<c:forEach var="menuList" items="${myMenuList.myMenuList}" varStatus="idx">
						<li>
							<a href="#" class="<c:if test="${menuList.MENU_ID eq fn:substring(comFn:nvl(param.resourceName, resourceName) ,0,6)}">on</c:if>" 
								onclick="clickLeftMenuMove('${menuList.LNK_URL}', '${menuList.URL_ID}', '${menuList.MENU_NM}');" title="${menuList.MENU_NM}">${menuList.MENU_NM}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- Header -->

	<form id="menuLeftMoveFrm" method="post">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
		<input type="hidden" name="P_CHRGR_ID_S" value="${loginResult.USR_ID}">
		<input type="hidden" name="P_CHRGR_NM_S" value="${loginResult.USR_NM}">	
	</form>
	
	<form id="helpPopFrm" method="post">
		<input type="hidden" name="resourceName" value="${param.resourceName}">
	</form>
	
	<form id="logOutFrm" method="post">
	</form>
	
	<form id="trmFrm" method="post">
		<input type="hidden" name="P_TTL_NM">	
	</form>