<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가정보불러오기 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmInfoList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmInfoList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		<input type="hidden" id="P_E006_YN" name="P_E006_YN" value="${P_E006_YN}">
		
		<div class="pop_header">
			<div class="title">평가정보목록</div>
		</div> <!--// pop_header E -->
		
		<div class="form-setting-box">
	        <div class="ui-setting">
	        
	        	<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">평가정보연계번호</span>
	                <input type="text" id="P_ESTM_ANNC_NO_S" name="P_ESTM_ANNC_NO_S" value="${param.P_ESTM_ANNC_NO_S}"  class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
	            
	        	<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">평가명</span>
	                <input type="text" id="P_ESTM_ANNC_NM_S" name="P_ESTM_ANNC_NM_S" value="${param.P_ESTM_ANNC_NM_S}" class="component-input w50"/>
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
	                  총 <strong>${comFn:nvl(estmInfoListTotCnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="8%">
					<col width="20%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">순번</th>
						<th class="txt-center">평가정보연계번호</th>
						<th>평가명</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(estmInfoListTotCnt, 0) == 0}">
						<tr>
							<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${estmInfoListTotCnt > 0}">
						<c:forEach var="data" items="${estmInfoList}" varStatus="status">
							<tr onclick="setEstmInfo('${data.ESTM_ANNC_NO}', '${data.ESTM_ANNC_NM}');">
								<td class="txt-center">${data.RNUM}&nbsp;</td>
								<td class="txt-center">${data.ESTM_ANNC_NO}&nbsp;</td>
								<td>${data.ESTM_ANNC_NM}&nbsp;</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(estmInfoListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 
 
<%--page move form --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      
