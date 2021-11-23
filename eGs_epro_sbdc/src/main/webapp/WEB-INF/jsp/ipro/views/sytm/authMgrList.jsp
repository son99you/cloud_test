<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 권한관리 목록
 *
 * <pre>
 * sytm
 *    |_ authMgrList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 15
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/authMgrList.js"></script> 

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
					<li style="font-size: 30px; font-weight: 500;">권한관리 목록</li>
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
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		
	<div class="page-list">
		<div class="form-setting-box">
			<dl class="ui-setting">
			<!-- Form Setting -->
			<div class="form-setting">
				<span class="txt-label">권한명</span>
				<input type="text" id="P_AUTH_NM_S" name="P_AUTH_NM_S" value="${param.P_AUTH_NM_S}" class="component-input">
			</div>	
			<!-- //Form Setting -->
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
						총 <strong>${comFn:nvl(authMgrListTotcnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/authMgrListExcelDwld.do','${comFn:nvl(authMgrListTotcnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			<table class="component-table">
	             <colgroup>
	                  <col width="30%"/>
	                  <col width="70%"/>
	                  <%-- <col width="20%"/> --%>
	           	</colgroup>
	           	<thead id="excelTh">
			    	<tr>
				    	<th class="txt-center">권한ID</th>
				    	<th>권한명</th>
			        </tr>
	           	</thead>
	           	<tbody>
	           	<!-- 엑셀다운로드데이터 시작 -->
				<input type="hidden" name="P_EXCEL_TD" value="AUTH_ID"/>
				<input type="hidden" name="P_EXCEL_TD" value="AUTH_NM"/>
				<!-- //엑셀다운로드데이터 종료 -->
	           		<c:if test="${comFn:nvl(authMgrListTotcnt, 0) == 0}">
						<tr>
							<td colspan="2" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${authMgrListTotcnt > 0}">
						<c:forEach var="data" items="${authMgrList}" varStatus="status">
							<tr onclick="detailInqire('${data.AUTH_ID}');">
								<td class="txt-center">${data.AUTH_ID}</td>
								<td><a href="#">${data.AUTH_NM }</a></td>
							</tr>
						</c:forEach>
					</c:if>
	           	</tbody>
		     </table>
		  </div>
	</div>
 </form>

<%-- POPUP FORM --%>
<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_AUTH_ID">
</form>

<form id="viewFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_AUTH_ID">
</form>