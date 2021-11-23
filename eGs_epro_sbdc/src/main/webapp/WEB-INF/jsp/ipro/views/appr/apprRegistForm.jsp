<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재선관리 등록 
 *
 * <pre>
 * appr
 *    |_ apprRegistForm.jsp
 * 
 * </pre>
 * @date : 2017. 06. 16
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/appr/apprRegistForm.js"></script> 
<script type="text/javascript" src="${jsPath}/ipro/views/info/jquery.tablednd.js"></script>

<!-- 네비게이션 -->
<div class="area-detail">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">
			
			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">결재등록</li>
				</ul>
			</div>
			
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">${myMenuList.bigMenuNm}</a></li>
					<li><a href="#">${myMenuSubList.smallMenuNm}</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//네비게이션 -->
 
<div class="area-detail">
	<form id="registFrm" class="registFrm" method="POST">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_APPL_SE" name="P_APPL_SE" value="${param.P_APPL_SE }">
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기본정보</div>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="150">
					<col width="*">
					<col width="150">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th><i class="icon-necessary"></i>결재선명</th>
						<td colspan="3">
							<input type="text" class="component-input w100" id="P_APPL_NM" name="P_APPL_NM"  value="${param.P_APPL_NM}" maxlength="300">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">결재자정보</div>
				</div>
	
				<div class="type-fright">
	
					<a href="javascript:" class="component-button-s type-add" id="popupBtn" name="popupBtn">추가</a>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table ">
				<colgroup>
					<col width="88">
					<col width="180">
					<col width="180">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>구분</th>
						<th>성명</th>
	                   	<th>부서명</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="userFrame">
				</tbody>
			</table>
		</div>
	
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	
	</form>
</div>
<!-- //Detail -->

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="setMulti" value="Y">
	<input type="hidden" id="P_APPL_SE" name="P_APPL_SE" value="${ param.P_APPL_SE }">
</form>

<!-- LIST FORM -->
<form id="listFrm" class="listFrm" method="POST" action="${contextPath}/info/infoApprlineList.do">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
</form>