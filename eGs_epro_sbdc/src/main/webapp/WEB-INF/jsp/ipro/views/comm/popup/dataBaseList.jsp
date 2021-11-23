<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 데이터베이스목록 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ dataBaseList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/dataBaseList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		
		<div class="pop_header">
			<div class="title">데이터베이스 목록</div>
		</div> <!--// pop_header E -->
		
		<div class="form-setting-box">
	        <div class="ui-setting">
	            <!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">테이블명</span>
	                <input type="text" id="P_TABLE_NM_S" name="P_TABLE_NM_S" value="${param.P_TABLE_NM_S}"  class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
	            <div class="form-setting">
	            </div>
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
	                  총 <strong>${comFn:nvl(dataBaseListTotcnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	
	
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="20%">
					<col width="*">
					<col width="20%">
					<%-- <col width="*"> --%>
				</colgroup>
				<thead>
					<tr>
						<c:choose>
							<c:when test="${param.setMulti eq 'Y'}">
								 <th class="txt-center">
								 	<label for="deptAllCbx" class="blind">체크박스</label>
			                    	<input type="checkbox" id="deptAllCbx" name="deptCbx" onclick="FwkCmmnUtil.setAllCheck('deptAllCbx','deptCbx');">
			                    </th>
							</c:when>
							<c:otherwise>
								 <th class="txt-center">번호</th>
							</c:otherwise>
						</c:choose>
						<th>테이블명</th>
						<th class="txt-center">테이블구분</th>
						<!-- <th>부서명</th> -->
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(dataBaseListTotcnt, 0) == 0}">
						<tr>
							<td colspan="4" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					
					<c:if test="${dataBaseListTotcnt > 0}">
						<c:forEach var="data" items="${dataBaseList}" varStatus="status">
							<tr class="txt-center"
								<c:if test="${param.setMulti ne 'Y'}">
									<c:if test="${GBN ne 'PRCH' }">onclick="setdeptInfo('${data.DEPT_NO}', '${data.DEPT_NM}');"</c:if>
									<c:if test="${GBN eq 'PRCH' }">onclick="setdeptInfo2('${data.DEPT_NO}', '${data.DEPT_NM}');"</c:if>
								</c:if> style="cursor: pointer;">
								
								<c:if test="${param.setMulti eq 'Y'}">
									<td class="txt-center">
										<label for="deptCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="deptCbx${status.count }" name="deptCbx">
									</td>
								</c:if>
								
								<c:if test="${param.setMulti ne 'Y'}">
									<td class="txt-center">${data.RNUM}&nbsp;</td>
								</c:if>
								<td class="txtl pl5">
									<input type="hidden" name="P_TABLE_ID" value="${data.TABLE_ID }">
									${data.TABLE_NM}&nbsp;
								</td>
								<td class="txt-center">${data.TABLE_SECD}&nbsp;</td>
								<%-- <td class="txtl pl5"><input type="hidden" name="P_DEPT_NM" value="${data.DEPT_NM }"> ${data.DEPT_NM}&nbsp;</td> --%>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(dataBaseListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
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
	<input type="hidden" name="P_NTT_SN">
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>      
