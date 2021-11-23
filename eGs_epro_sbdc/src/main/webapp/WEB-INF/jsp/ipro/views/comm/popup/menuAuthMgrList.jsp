<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 공통 > 권한별메뉴관리 (팝업)
 *
 * <pre>
 * comm
 *     |_ popup
 *       |_ menuAuthMgrList.jsp
 * 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<!-- <script type="text/javascript" src="/statics/fwk/CmmnUtil.js"></script> -->
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/menuAuthMgrList.js"></script>

<div class="layout-pop"> 
	<div class="sp_cont">
		<form id="listFrm" name="listFrm" class="search_form" method="POST" action="${contextPath}/comm/popup/menuAuthMgrList.do">
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_AUTH_ID" name="P_AUTH_ID" value="${param.P_AUTH_ID}">
			
			<div class="pop_header">
				<div class="title">권한별메뉴관리</div>
			</div> <!--// pop_header E -->
			
			<div class="form-setting-box">
	        	<div class="ui-setting">
			
			<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">메뉴명</span>
	                <input type="text" id="P_MENU_NM_S" name="P_MENU_NM_S" value="${param.P_MENU_NM_S}"  class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
			</div>
		</div>
		<!-- buttons -->
		<div class="setting-button">
	    	  <button class="component-button" id="searchBtn">조회</button>
	  	</div>
		<!-- //buttons -->
		<!-- //buttons -->
	   <div class="area-list">
	      <!-- Option -->
	      <div class="table-option">
	          <!-- Right -->
	          <div class="option-right">
	              <div class="table-num type-fleft">
	                  총 <strong>${comFn:nvl(menuAuthMgrListTotcnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	    </div>
	    <!-- //Option -->
	    
		<table class="component-table">
			<colgroup>
				<col width="30%">
				<col width="30%">
				<col width="40%">
			</colgroup>
			<thead>
					<tr>
						<th class="txt-center">권한명</th>
	                    <th>메뉴ID</th>
	                    <th>메뉴명</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${comFn:nvl(menuAuthMgrListTotcnt, 0) == 0}">
					<tr>
						<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${menuAuthMgrListTotcnt > 0}">
					<c:forEach var="data" items="${menuAuthMgrList}" varStatus="status" >
						<tr  class="row" onclick="setdeptInfo('${data.AUTH_NM}', '${data.MENU_ID}', '${data.MENU_NM}');" style="cursor: pointer;" > 
						<tr class="row">
							<td class="txt-center">${data.AUTH_NM}&nbsp;</td>
							<td>${data.MENU_ID}&nbsp;</td>
							<td>${data.MENU_NM}&nbsp;</td>
						</tr> 
					</c:forEach>
				</c:if>
			</tbody> 
		</table>	
			
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(menuAuthMgrListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
			
		<div class="bottom-buttons">
			<a href="javascript:"   class="btn-bottom type-a"id="closeBtn">닫기</a>
		</div>
	</div>
</div>