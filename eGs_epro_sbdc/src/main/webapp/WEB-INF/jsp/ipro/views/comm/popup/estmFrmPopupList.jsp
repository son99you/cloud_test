<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가서식 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmFrmPopupList.jsp
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
		<input type="hidden" id="P_ESTM_PROCD_SECD" name="P_ESTM_PROCD_SECD" value="${param.P_ESTM_PROCD_SECD}">
		
		<div class="pop_header">
			<div class="title">평가서식 목록</div>
		</div> <!--// pop_header E -->
		
	  <div class="area-list">
	  	<!-- Option -->
	      <div class="table-option">
	          <!-- Right -->
	          <div class="option-right">
	              <div class="table-num type-fleft">
	                  총 <strong>${comFn:nvl(estmFrmPopupListTotCnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	      </div>
	      <!-- //Option -->
	      
		<table class="component-table">
			<colgroup>
				<col width="10%">
				<col width="30%">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th class="txt-center">순번</th>
					<th class="txt-center">평가절차구분</th>
					<th>평가서식명</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${comFn:nvl(estmFrmPopupListTotCnt, 0) == 0}">
					<tr>
						<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				
				<c:if test="${estmFrmPopupListTotCnt > 0}">
					<c:forEach var="data" items="${estmFrmPopupList}" varStatus="status">
						<tr class="txt-center" onclick="setEstmFrmInfo('${data.ESTM_FRM_NO}', '${data.ESTM_FRM_NM}');" style="cursor: pointer;">
							<td class="txt-center">${data.RNUM}&nbsp;</td>
							<td class="txt-center"><input type="hidden" name="P_ESTM_FRM_NO" value="${data.ESTM_FRM_NM }"> ${data.ESTM_FRM_NO}&nbsp;</td>
							<td><input type="hidden" name="P_ESTM_FRM_NM" value="${data.ESTM_FRM_NM }"> ${data.ESTM_FRM_NM}&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>

		</table>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(estmFrmPopupListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
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
