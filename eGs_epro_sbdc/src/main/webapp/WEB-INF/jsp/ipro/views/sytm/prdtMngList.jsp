<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 상품관리 목록
 *
 * <pre>
 * sytm
 *    |_ prdtMngList.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/prdtMngList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">상품관리 목록</li>
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
				<!-- From Setting -->
				<div class="form-setting">
					<span class="txt-label" >품목코드</span>
					<input type="text" id="P_ITEM_NO_S" name="P_ITEM_NO_S" value="${param.P_ITEM_NO_S}" class="component-input">
				</div>
				<!-- // From Setting -->
				
				<!-- From Setting -->
				<div class="form-setting">
					<span class="txt-label" >품목명</span>
					<input type="text" id="P_ITEM_NM_S" name="P_ITEM_NM_S" value="${param.P_ITEM_NM_S}" class="component-input">
				</div>
				<!-- // From Setting -->
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
						총 <strong>${comFn:nvl(prdtMngListTotcnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/prdtMngListExcelDwld.do','${comFn:nvl(prdtMngListTotcnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			<table class="component-table">
	             <colgroup>
	                 <col width="70%"/>
	                  <col width="50%"/>
	           	</colgroup>
	           	<thead id="excelTh">
	           		<tr>
	           			<th class="txt-center">품목코드</th>
	           			<th >품목명</th>
	           		</tr>
	           	</thead>
	           	<tbody>
	           		<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="ITEM_UNCD"/>
					<input type="hidden" name="P_EXCEL_TD" value="ITEM_NM"/>
					<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(prdtMngListTotcnt, 0) == 0}">
						<tr>
							<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>  
					<c:if test="${prdtMngListTotcnt > 0}">
						<c:forEach var="data" items="${prdtMngList}" varStatus="status">
							<c:if test="${empty data.GOODSNO }">
								<tr>
							</c:if>
							<c:if test="${not empty data.GOODSNO }">
								<tr onclick="estmObjImstarsPrdsView('popupFrm', '${data.ITEM_NO }')">
							</c:if>
								<td class="txt-center">${data.ITEM_NO}</td>
								<td   title="${data.ITEM_NM}"><a href="#">${data.ITEM_NM}</a></td>
							</tr>
						 </c:forEach>
					  </c:if> 
	          	 </tbody>
		    </table>
		</div>

		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(prdtMngListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!-- // pageing -->
	</div>
</form>


<%-- DETAIL FORM --%>
<form id="detailFrm"  class="search_form"  method="POST"> 
	<input type="hidden" name="resourceName" value="${param.resourceName}">
	<input type="hidden" name="P_ITEM_NO">
</form>

<!-- POPUP FORM -->
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_GOODSNO">
</form>