<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 업체정보관리
 *
 * <pre>
 * vend 
 *    |_ vendMngeList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/vendMngeList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">업체정보관리 목록</li>
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

<form id="searchFrm" name="searchFrm" method="POST">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
	<input type="hidden" id="P_SEARCH_S" name="P_SEARCH_S">
		
	<div class="page-list">
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">업체명</span>
					<input type="text" id="P_VEND_NM_S" name="P_VEND_NM_S" value="${param.P_VEND_NM_S}" class="component-input">
				</div>
				<!-- // Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label"  style="width:100px;" >사업자등록번호</span>
					<input type="text" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S}" class="component-input">
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
				<!-- Right -->
				<div class="option-right">
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(vendMngeListTotcnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/vendMngeListExcelDwld.do','${comFn:nvl(vendMngeListTotcnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
		
			<table class="component-table">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="*">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead id="excelTh">
					<tr>
						<th class="txt-center">순번</th>
						<th class="txt-center">사업자번호</th>
						<th>업체명</th>
						<th class="txt-center">대표자명</th>
						<th class="txt-center">등록자</th>
					</tr>
				</thead>
				<tbody>
					<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
					<input type="hidden" name="P_EXCEL_TD" value="BIZRNO"/>
					<input type="hidden" name="P_EXCEL_TD" value="VEND_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="RPRS_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="REG_NM"/>
					<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(vendMngeListTotcnt, 0) == 0}">
						<tr>
							<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if> 
					<c:if test="${vendMngeListTotcnt > 0}">
						<c:forEach var="data" items="${vendMngeList}" varStatus="status">
							<c:if test="${empty data.BSNM_REGIST_NO }">
								<tr>
							</c:if>
							<c:if test="${not empty data.BSNM_REGIST_NO }">
								<tr onclick="estmObjImstarsVendView('popupFrm', '${data.BIZRNO}')">
							</c:if>
								<td class="txt-center">${data.RNUM}</td>
								<td class="txt-center">${comFn:formatBizNumber(data.BIZRNO)}</td>
								<td title="${data.VEND_NM}">${data.VEND_NM}</td>
								<td class="txt-center">${data.RPRS_NM }</td>
								<td class="txt-center">${data.REG_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		 <!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(vendMngeListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> 	
		 <!-- //pageing -->		
	</div>				
</form>

<%-- DETAIL FORM --%>
<form id="detailFrm"  class="search_form" method="POST" > 
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_VEND_REG_NO">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_BSNM_REGIST_NO">
</form>
