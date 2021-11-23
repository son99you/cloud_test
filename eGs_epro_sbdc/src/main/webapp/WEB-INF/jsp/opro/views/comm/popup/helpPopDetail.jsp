<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 부서목록 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ deptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmFrmPopupList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		<input type="hidden" id="P_FRM_GBN" name="P_FRM_GBN" value="${param.P_FRM_GBN}">
		
		<div class="pop_header">
			<div class="title">도움말</div>
		</div> <!--// pop_header E -->
		
	  	<div>
			<img src="${imagePath}/menual/test.PNG" border="0"  />
		</div>
		<div class="bottom-buttons">
			<button type="button" id="colseBtn" class="btn-bottom type-a">닫기</button>
		</div>
	</form>
</div> 
 

<%--page move form --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_NTT_SN">
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      
