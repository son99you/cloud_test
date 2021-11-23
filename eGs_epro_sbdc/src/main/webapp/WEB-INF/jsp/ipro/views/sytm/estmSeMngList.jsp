<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 평가구분관리 목록
 *
 * <pre>
 * sytm
 *    |_ estmSeMngList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 11.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/estmSeMngList.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">평가구분관리 목록</li>
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
	<form id="searchFrm" name="searchFrm" method="POST">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_SEARCH_AT" name="P_SEARCH_AT" value="Y">
		
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<%-- <div class="form-setting">
					<span class="txt-label">평가구분</span>
					<comTag:comCmcdCdValueComboBox id="P_ESTM_SECD_S" name="P_ESTM_SECD_S" selectKey="${param.P_ESTM_SECD_S}" cdId="ESTM_SECD"  headerKey="" headerValue="전체" className="component-select"/>
				</div> --%>
				<!-- Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">작성부서</span>
						<div class="component-input-search">
							<c:if test="${sessionScope.loginResult.AUTH_ID eq '1' }">
								<input type="text" id="P_ESTM_CHRG_DEPT_NM_S" name="P_ESTM_CHRG_DEPT_NM_S" value="${param.P_ESTM_CHRG_DEPT_NM_S }" class="component-input" readonly="readonly">
								<input type="hidden" id="P_ESTM_CHRG_DEPT_NO_S" name="P_ESTM_CHRG_DEPT_NO_S" value="${param.P_ESTM_CHRG_DEPT_NO_S }">
								<a href="javascript:" class="btn-search-close" id="estmChrgDeptDelBtn"></a>
								<a href="javascript:" class="btn-search" id="estmChrgDepSrchBtn"></a>
							</c:if>
							<c:if test="${sessionScope.loginResult.AUTH_ID ne '1' }">
								<input type="text" id="P_ESTM_CHRG_DEPT_NM_S" name="P_ESTM_CHRG_DEPT_NM_S" value="${sessionScope.loginResult.DEPT_NM}" class="component-input" readonly="readonly">
								<input type="hidden" id="P_ESTM_CHRG_DEPT_NO_S" name="P_ESTM_CHRG_DEPT_NO_S" value="${sessionScope.loginResult.DEPT_NO}">
							</c:if>
						</div>
					<!-- <input type="text" class="component-input"> -->
				</div>
				<!-- //Form Setting -->
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
					<button type="button" class="component-button-s" style="" id="codeRegistBtn">구분추가</button>
					<%-- <c:if test="${sessionScope.loginResult.USR_ID eq 'son99you' }">
						<button type="button" class="component-button-s" style="width: 100px;" id="registResultBtn">결과신규작성</button>
					</c:if> --%>
				</div>
				<!-- //Left -->
			
				<!-- Right -->
				<div class="option-right">
	
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(estmSeMngListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/estmSeMngListExcelDwld.do','${comFn:nvl(estmSeMngListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
	
			<table class="component-table">
				<colgroup>
					<col width="80px">
					<col width="150px">
					<col width="auto;">
					<col width="250px">
				</colgroup>
				<thead id="excelTh">
					<tr>
						<th class="txt-center">순번</th>
						<th class="txt-center">평가구분코드</th>
						<th>평가구분명</th>
						<th>작성부서</th>
					</tr>
				</thead>
				<tbody>
					<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
					<input type="hidden" name="P_EXCEL_TD" value="ESTM_SECD"/>
					<input type="hidden" name="P_EXCEL_TD" value="ESTM_SENM"/>
					<input type="hidden" name="P_EXCEL_TD" value="ESTM_DEPT_NM"/>
					<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(estmSeMngListTotCnt, 0) == 0}">
						<tr>
							<td class="txt-center" colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${estmSeMngListTotCnt > 0}">
						<c:forEach var="data" items="${estmSeMngList}" varStatus="status">
							<tr style="cursor: pointer;" onclick="detailInqire('${data.ESTM_SECD}','${data.ESTM_DEPT_NO}');">
								<td class="txt-center">${data.RNUM}</td>
								<td class="txt-center">${data.ESTM_SECD}</td>
								<td>${data.ESTM_SENM}</td>
								<td>${data.ESTM_DEPT_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(estmSeMngListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
	</form>		
</div>

<form id="registFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_ESTM_SECD" name="P_ESTM_SECD">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${sessionScope.loginResult.AUTH_ID}">
	<input type="hidden" id="P_ARA_DEPT_CD" name="P_ARA_DEPT_CD">
	<input type="hidden" name="setMulti">
</form>