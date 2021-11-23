<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 메인 > 평가진행현황 목록
 *
 * <pre>
 * recr 
 *    |_ recrAnncList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/recr/recrAnncList.js"></script>

<!-- 네비게이션 -->
<div class="area-detail" style="float:left; width:1050px;">
	<div class="table-detail">
		<!-- Top -->
		<div class="nav_sec">

			<div class="btn-help" style="display:none;">
				<a href="javascript:helpPopup();">도움말</a>
			</div>
			
			<div class="option-left">
				<ul class="location">
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 목록</li>
				</ul>
			</div>
			<div class="option-right">
				<ul class="location">
					<li class="home"><a href="#">홈</a></li>
					<li><a href="#">평가진행현황 목록</a></li>
					<%-- <li><a href="#">${myMenuList.bigMenuNm}</a></li> --%>
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
					<span class="txt-label">평가명</span>
					<input type="text" id="P_ESTM_NM_S" name="P_ESTM_NM_S" value="${param.P_ESTM_NM_S}" class="component-input" style="width: 300px;">
				</div>
				
				<div class="form-setting">
					<span class="txt-label">평가구분</span>
					<comTag:comCmcdCdValueComboBox id="P_ESTM_SECD_S" name="P_ESTM_SECD_S" selectKey="${param.P_ESTM_SECD_S}" cdId="ESTM_SECD" headerKey="" headerValue="전체" className="component-select"/>
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
						총 <strong>${comFn:nvl(recrGnrlListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/opro/recr/recrAnncListExcelDwld.do','${comFn:nvl(recrGnrlListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			<table class="component-table">
			<!-- <table class="component-detail-table type-line-none"> -->
             	<colgroup>
             		<col width="5%">
             		<col width="10%">
             		<col width="30%">
             		<col width="15%">
           		</colgroup>
           		<thead id="excelTh">
			    	<tr>
	                	<th class="txt-center">순번</th>
	                	<th class="txt-center">실평가여부</th>
						<th>평가명</th>
						<th>평가구분</th>
			        </tr>
           		</thead>
           		<tbody>
           			<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
					<input type="hidden" name="P_EXCEL_TD" value="REAL_ESTM_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="ESTM_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="ESTM_SECD_NM"/>
					<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(recrGnrlListTotCnt, 0 ) == 0 }">
						<tr>
							<td colspan="4" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다</td>
						</tr>
					</c:if>
					
					<c:if test="${recrGnrlListTotCnt > 0 }">
						<c:forEach var="data" items="${recrGnrlList}" varStatus="status">
							<tr onclick="detailInqire('${data.ESTM_NO}');">
								<td class="txt-center">${data.RNUM }</td>
								<td class="txt-center">
									<c:if test="${data.REAL_ESTM_YN eq 'Y' }" ><b style="color: red;">실제</b></c:if>
									<c:if test="${data.REAL_ESTM_YN eq 'N' }"><b style="color: green">모의</b></c:if>
									<c:if test="${data.REAL_ESTM_YN eq  null}">ㅡ</c:if>
								</td>
								<td title="${data.ESTM_NM }">${comFn:toShorten(data.ESTM_NM, 40)}</td>
								<td> ${data.ESTM_SECD_NM }</td>
							</tr>
						</c:forEach>
					</c:if>
           		</tbody>
		    </table>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(recrGnrlListTotCnt, 0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div> <!--// list_bottom E -->			
	    <!-- //pageing -->		
	    	
	</form>
	</div>

<%-- DETAIL FORM --%>  
<form id="detailFrm"  method="POST" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden"   id="P_ESTM_NO" name="P_ESTM_NO">
</form>

<form id="manualFrm" method="post">
</form>