<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가진행이력 목록 조회(팝업)
 *
 * <pre>
 * comm 
 *    |_ popup
 *       |_ estmHistList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		
		<div class="pop_header">
			<div class="title">평가진행이력 목록</div>
		</div> <!--// pop_header E -->
		
	  <div class="area-list">
	      <!-- Option -->
	      <div class="table-option">
	          <!-- Right -->
	          <div class="option-right">
	              <div class="table-num type-fleft">
	                  총 <strong>${comFn:nvl(estmHistListTotCnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="10%">
					<col width="25%">
					<col width="*">
					<col width="12%">
					<col width="25%">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">순번</th>
						<th>진행상태</th>
						<th>내용</th>
						<th>등록자</th>
						<th class="txt-center">등록일자</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(estmHistListTotCnt, 0) == 0}">
						<tr>
							<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${estmHistListTotCnt > 0}">
						<c:forEach var="data" items="${estmHistList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count}&nbsp;</td>
								<td>${data.ESTM_PSCD_NM}&nbsp;</td>
								<td>${data.RMK}&nbsp;</td>
								<td>${data.REGR_NM}&nbsp;</td>
								<td class="txt-center">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:SS')}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 
 
<%--page move form --%>
<form id="detailFrm" method="POST">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      
