<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 코드관리 목록
 *
 * <pre>
 * sytm 
 *    |_ codeMngeList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/codeMngeList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">코드관리 목록</li>
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
		
	<div class="page-list">
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">코드ID</span>
					<input type="text" id="P_CD_ID_S" name="P_CD_ID_S" value="${param.P_CD_ID_S}" class="component-input">
				</div>
				<!-- // Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
				</div>
				<!-- // Form Setting -->
			</div>
				
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">코드명</span>
					<input type="text" name="P_CD_NM_S"  id="P_CD_NM_S" value="${param.P_CD_NM_S}" class="component-input" />
				</div>
				<!-- // Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					 <span class="txt-label">코드상세명</span>
					<input type="text" name="P_CD_DTL_NM_S"  id="P_CD_DTL_NM_S" value="${param.P_CD_DTL_NM_S}" class="component-input" />
				</div>
				<!-- // Form Setting -->
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
						총 <strong>${comFn:nvl(codeMngeListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/codeMngeListExcelDwld.do','${comFn:nvl(codeMngeListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->
			
			<table class="component-table">
				<colgroup>
					<col width="25%"/>
	                <col width="25%"/>
	                <col width="25%"/>
	                <col width="25%"/>
	           	</colgroup>
	           	<thead id="excelTh">
			    	<tr>
			        	<th >코드ID</th>
			            <th>코드명</th>
			        	<th>코드상세ID</th>
			            <th>코드상세명</th>
			        </tr>
	           	</thead>
		        <tbody>
		        	<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="CD_ID"/>
					<input type="hidden" name="P_EXCEL_TD" value="CD_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="CD_DTL_ID"/>
					<input type="hidden" name="P_EXCEL_TD" value="CD_DTL_NM"/>
					<!-- //엑셀다운로드데이터 종료 -->
			       <c:if test="${comFn:nvl(codeMngeListTotCnt, 0) == 0}">
						<tr>
							<td class="txt-center" colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${codeMngeListTotCnt > 0}">
						<c:forEach var="data" items="${codeMngeList}" varStatus="status">
							<tr>
								<td>${data.CD_ID}</td>
								<td>${data.CD_NM}</td>
								<td>${data.CD_DTL_ID}</td>
								<td>${data.CD_DTL_NM}</td>
							</tr>
						</c:forEach>
					</c:if>
		         </tbody>
		      </table>
		      
		      <!-- pageing -->
		      <div class="component-pageing">
		      	<comTag:pagingIpro totalCount="${comFn:nvl(codeMngeListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		      </div>
		      <!-- // pageing -->
		</div>
	</div>
</form>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>

<form id="regFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
</form>