<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 결재관리 > 결재대상 목록
 *
 * <pre>
 * info
 *    |_ infoAppConsultList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/info/infoAppConsultList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">결재대상 목록</li>
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
	<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/info/infoAppConsultList.do">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">결재명</span>
					<input type="text" id="P_APPR_NM_S" name="P_APPR_NM_S" value="${param.P_APPR_NM_S}" maxlength="50" class="component-input w50">
				</div>
				<!-- //Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">요청자</span>
					<input type="text" id="P_REGR_NM_S" name="P_REGR_NM_S" value="${param.P_REGR_NM_S}" maxlength="50" class="component-input">
				</div>
				<!-- Form Setting -->
			</div>

			<div class="ui-setting">			
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">결재요청일</span>
					<!-- Component Calen -->
					<div class="component-calen">
						<div class="data-calen">
							<input type="text" id="P_REG_DT_ST_S" name="P_REG_DT_ST_S" class="component-input datepicker start-date" value="${param.P_REG_DT_ST_S }" date>
							<!-- <i class="icon-calen" date></i> -->
						</div>
	
						<em class="txt-bar">~</em>
						<div class="data-calen">
							<input type="text" id="P_REG_DT_END_S" name="P_REG_DT_END_S" class="component-input datepicker end-date" value="${param.P_REG_DT_END_S }" date>
							<!-- <i class="icon-calen"></i> -->
						</div>
					</div>
					<!-- //Component Calen -->
				</div>
				<!-- //Form Setting -->
				
			</div>
		</div>
		
		
		<!-- buttons -->
		<div class="setting-button">
			<button class="component-button" id="searchBtn">조회</button>
		</div>
		<!-- //buttons -->
		<div class="area-list">
			<!-- Option -->
			<div class="table-option">
	
				<!-- Right -->
				<div class="option-right">
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(tApprMstListTotCnt, 0)}</strong>건
					</div>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
	
			<table class="component-table">
				<colgroup>
					<col width="150">
					<col width="*">
					<col width="150">
					<col width="150">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">결재번호</th>
                    	<th>결재명</th>
						<th class="txt-center">결재요청일</th>
						<th class="txt-center">요청자</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(tApprMstListTotCnt, 0) == 0}">
						<tr>
							<td class="txt-center" colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${tApprMstListTotCnt > 0}">
						<c:forEach var="data" items="${tApprMstList}" varStatus="status">
							 <tr onclick="infoApprlineDetail('${data.APPR_NO}');">
								<td class="txtc">${data.APPR_NO}</td>
								<td class="tit pl5 txtl">${data.APPR_NM}</td>
								<td class="txtc">${comFn:formatDate(data.REG_DT, 'yyyyMMddhhmmss', 'yyyy-MM-dd')}</td>
								<td class="txtc">${data.REGR_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(tApprMstListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
	</form>
</div>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST">
	<input type="hidden" id="P_APPR_NO" name="P_APPR_NO">
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>