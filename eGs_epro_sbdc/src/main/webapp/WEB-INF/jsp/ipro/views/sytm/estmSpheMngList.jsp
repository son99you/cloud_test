<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 평가분야관리 목록
 *
 * <pre>
 * sytm
 *    |_ estmSpheMngList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23
 * @version : 1.0
 * @author : 은우소프트
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmSpheMngList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가분야관리 목록</li>
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

<div class="page-list">
	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">평가분야구분명</span>
					<input type="text" id="P_ESTM_SPHE_SENM_S" name="P_ESTM_SPHE_SENM_S" value="${param.P_ESTM_SPHE_SENM_S }" class="component-input w50">
				</div>
				<!-- Form Setting -->
				
			</div>
		</div>
		
		
		<!-- buttons -->
		<div class="setting-button">
			<button type="button" class="component-button" id="searchBtn">조회</button>
		</div>
		<!-- //buttons -->
		
		<div class="area-list">
			<!-- Option -->
			<div class="table-option">
				<!-- Left -->
				<div class="option-left">
					<button type="button" class="component-button-s" id="registBtn">신규등록</button>
				</div>
				<!-- //Left -->
	
				<!-- Right -->
				<div class="option-right">
	
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(estmSpheMngListTotCnt, 0)}</strong>건
					</div>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
	
			<table class="component-table">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">순번</th>
						<th>평가분야구분명</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(estmSpheMngListTotCnt, 0) == 0}">
						<tr>
							<td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${estmSpheMngListTotCnt > 0}">
						<c:forEach var="data" items="${estmSpheMngList}" varStatus="status">
							<tr onclick="detailInqire('${data.ESTM_SPHE_SECD }');">
								<td class="txt-center">${data.RNUM}</td>
								<td>${data.ESTM_SPHE_SENM }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(estmSpheMngListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
	</form>
</div>

<!-- DETAIL FORM -->
<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_SPHE_SECD">
</form>
