<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/frame/bodyLeft.js"></script>

<div class="page-view">
	<!-- Lnb -->
	<div class="sub-lnb">
		<div class="lnb-title">${myMenuList.bigMenuNm}</div>
		
		<ul class="list-sub-lnb">
		<c:if test="${not empty myMenuSubList}">
			<c:set value="1" var="li_flag" />
			<c:forEach var="menuSubList" items="${myMenuSubList.myMenuSubList}" varStatus="idx">
				<c:if test="${menuSubList.MENU_SECD eq 'S'}">
					<c:if test="${empty menuSubList.LNK_URL}">
						<c:if test="${li_flag eq 2}">
								</ul>
							</li>	
						</c:if>		
						<c:set value="2" var="li_flag" />
						<li>
							<a href="#" class="<c:if test="${menuSubList.MENU_ID eq fn:substring(comFn:nvl(param.resourceName, resourceName) ,0,6)}">is-selected</c:if>">${menuSubList.MENU_NM}</a>
							<ul <c:if test="${menuSubList.MENU_ID eq fn:substring(comFn:nvl(param.resourceName, resourceName) ,0,6)}">style="display: block;"</c:if>>
					</c:if>
					<c:if test="${not empty menuSubList.LNK_URL}">
						<c:if test="${li_flag eq 2}">
								</ul>
							</li>	
						</c:if>
						<c:set value="1" var="li_flag" />
						
						<c:if test="${menuSubList.MENU_LVL eq '3' }">	
							<li class="is-show"><!-- class="type-show is-show" -->
								<a href="#" onclick="clickMenuMove('${menuSubList.LNK_URL}', '${menuSubList.MENU_ID}', '${menuSubList.MENU_NM}' );" title="${menuSubList.MENU_NM}">${menuSubList.MENU_NM}</a>
								
								<ul class="sub-list-in">
									
						</c:if>
						<c:if test="${menuSubList.MENU_LVL ne '3' }">
							<li class="<c:if test="${menuSubList.MENU_ID eq comFn:nvl(param.resourceName, resourceName)}">is-selected</c:if>">
								<a href="#" onclick="clickMenuMove('${menuSubList.LNK_URL}', '${menuSubList.MENU_ID}', '${menuSubList.MENU_NM}' );" title="${menuSubList.MENU_NM}">${menuSubList.MENU_NM}</a>
							</li>
						</c:if>
					</c:if>
				</c:if>
				
				<c:if test="${menuSubList.MENU_LVL eq '4' }">
					<li>
						<a href="#" class="<c:if test="${menuSubList.MENU_ID eq comFn:nvl(param.resourceName, resourceName)}">is-selected_Lvl3</c:if>" onclick="clickMenuMove('${menuSubList.LNK_URL}', '${menuSubList.MENU_ID}', '${menuSubList.MENU_NM}' );" title="${menuSubList.MENU_NM}">${menuSubList.MENU_NM}</a>
						</li>
					</li>
				</c:if>
			</c:forEach>	
		</c:if>
	</ul>
		
</div>
<!-- //Lnb -->

<form id="manualFrm" method="post">
</form>
<form id="menuMoveFrm" method="post">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="resourceName">
	<input type="hidden" name="resourceDesc">
</form>