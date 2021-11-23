<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원분야매핑 목록
 *
 * <pre>
 * estm
 *    |_ estmCmtmSpheMpgList.jsp
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmCmtmSpheMpgList.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">평가위원분야매핑 목록</li>
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
				<span class="txt-label">평가분야구분</span>
				<%-- <comTag:comTableValueComboBox id="P_ESTM_SPHE_SECD_S" name="P_ESTM_SPHE_SECD_S" selectKey="${param.P_ESTM_SPHE_SECD_S}" tableId="T_ESTM_SPHE_MST" columnId="ESTM_SPHE_SE" whereId1= "DEL_AT" whereVal1="N" headerKey="" headerValue="전체" className="component-select"/> --%>
				<select id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" class="component-select"></select>
			</div>
			<!-- Form Setting -->
			
			<!-- Form Setting -->
			<div class="form-setting">
				<span class="txt-label">등록상태</span>
				<select class="component-select" name="P_REG_AT_S">
					<option value="">전체</option>
					<c:if test="${param.P_REG_AT_S eq '' || empty param.P_REG_AT_S}">
						<option value="Y">등록</option>
						<option value="N">미등록</option>
					</c:if>
					<c:if test="${param.P_REG_AT_S eq 'Y' }">
						<option value="Y" selected="selected">등록</option>
						<option value="N">미등록</option>	
					</c:if>
					<c:if test="${param.P_REG_AT_S eq 'N' }">
						<option value="Y">등록</option>
						<option value="N" selected="selected">미등록</option>	
					</c:if>
					
				</select>
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
			</div>
			<!-- //Left -->

			<!-- Right -->
			<div class="option-right">

				<div class="table-num type-fleft">
					총 <strong>${comFn:nvl(estmCmtmSpheMpgListTotCnt, 0)}</strong>건
				</div>
				<a href="javascript:excelDwd('searchFrm'
							, '/estm/cmtmSpheMpgListExcelDwld.do'
							,'${comFn:nvl(estmCmtmSpheMpgListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
				</a>
			</div>
			<!-- //Right -->
		</div>
		<!-- //Option -->

		<table class="component-table">
			<colgroup>
				<col width="10%">
				<%-- <col width="15%"> --%>
				<col width="*">
				<col width="30%">
			</colgroup>
			<thead id="excelTh">
				<tr>
					<th class="txt-center">순번</th>
					<!-- <th class="txt-center">실평가여부</th> -->
					<th>평가분야구분명</th>
					<th class="txt-center">등록상태</th>
				</tr>
			</thead>
			<tbody>
				<!-- 엑셀다운로드데이터 시작 -->
				<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
				<input type="hidden" name="P_EXCEL_TD" value="ESTM_SPHE_SENM"/>
				<input type="hidden" name="P_EXCEL_TD" value="REG_AT"/>
				<!-- //엑셀다운로드데이터 종료 -->
				
				<c:if test="${comFn:nvl(estmCmtmSpheMpgListTotCnt, 0) == 0}">
					<tr>
						<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				
				<c:if test="${estmCmtmSpheMpgListTotCnt > 0}">
					<c:forEach var="data" items="${estmCmtmSpheMpgList}" varStatus="status">
					<%-- <c:forEach var="real" items="${realEstmYn }" varStatus="status"> --%>
						<tr onclick="detailInqire('${data.ESTM_SPHE_SECD }','${data.REG_AT}');">
							<td class="txt-center">${data.RNUM}</td>
							<%-- <td class="txt-center">
								<c:if test="${real.REAL_ESTM_YN eq 'Y' }" ><b style="color: red">실제</b></c:if>
								<c:if test="${real.REAL_ESTM_YN eq 'N' }"><b style="color: green">모의</b></c:if>
								<c:if test="${real.REAL_ESTM_YN eq  null}">ㅡ</c:if>
							</td> --%>
							<td>${data.ESTM_SPHE_SENM}</td>
							<td class="txt-center">
								<c:if test="${data.REG_AT eq 'Y' }"><span class="tag-status type-ing">등록</span></c:if>
								<c:if test="${data.REG_AT eq 'N' }"><span class="tag-status type-change">미등록</span></c:if>
							</td>
						</tr>
						<%-- </c:forEach> --%>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	</form>
	
	<!-- pageing -->
	<div class="component-pageing">
		<comTag:pagingIpro totalCount="${comFn:nvl(estmCmtmSpheMpgListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
	</div>
	<!--//pageing -->
		
</div>

<form id="registFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
</form>

<form id="detailFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_ESTM_SPHE_SECD" name="P_ESTM_SPHE_SECD" value="">
	
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${sessionScope.loginResult.AUTH_ID}">
	<input type="hidden" id="P_ARA_DEPT_CD" name="P_ARA_DEPT_CD">
</form>