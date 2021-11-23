<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 정보관리 > 사용자관리 목록
 *
 * <pre>
 * sytm
 *    |_ userMrgList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 14
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/sytm/userMgrList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">사용자관리 목록</li>
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
	<input type="hidden" id="P_SEARCH" name="P_SEARCH" value="Y">

	<div class="page-list">
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">사원번호</span>
					<input type="text" id="P_USR_ID_S" name="P_USR_ID_S" value="${param.P_USR_ID_S}" class="component-input">
				</div>
				<!-- //Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">사용자권한</span>
					<comTag:comCmcdCdValueComboBox id="P_AUTH_ID_S" name="P_AUTH_ID_S" cdId="AUTH_ID" selectKey="${comFn:nvl(param.P_AUTH_ID_S,P_AUTH_ID_S)}" headerValue="전체" className="component-select" />
				</div>
				<!-- //Form Setting -->
			</div>
				
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">성명</span>
					<input type="text" id="P_USR_NM_S" name="P_USR_NM_S" value="${param.P_USR_NM_S}"    class="component-input">
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
						총 <strong>${comFn:nvl(userMgrListTotcnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/sytm/userMgrListExcelDwld.do','${comFn:nvl(userMgrListTotcnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->

			<div style="overflow-x: scroll; overflow-y:hidden" >
				<table class="component-table">
					<colgroup>
						<col width="60px">
						<col width="110px">
						<col width="110px">
						<col width="120px">
						<col width="150px">
						<col width="140px">
						<col width="150px">
						<col width="180px">
						<col width="200px">
					</colgroup>
					<thead id="excelTh">
		                <tr>
		                	<th class="txt-center">순번</th>
		                	<th class="txt-center">사용자ID</th>
							<th class="txt-center">사원번호</th>
					    	<th class="txt-center">성명</th>
					    	<th>부서명</th> 
					    	<th>직위</th>
					    	<th>전화번호</th>
					    	<th>이메일</th>
					    	<th>사용자권한</th>
		                </tr>
		            </thead>
					<tbody>
						<!-- 엑셀다운로드데이터 시작 -->
						<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
						<input type="hidden" name="P_EXCEL_TD" value="USR_ID"/>
						<input type="hidden" name="P_EXCEL_TD" value="EMPL_NO"/>
						<input type="hidden" name="P_EXCEL_TD" value="USR_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="DEPT_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="OFPS_NM"/>
						<input type="hidden" name="P_EXCEL_TD" value="TEL_NO"/>
						<input type="hidden" name="P_EXCEL_TD" value="EMAL_ADDR"/>
						<input type="hidden" name="P_EXCEL_TD" value="AUTH_NM"/>
						<!-- //엑셀다운로드데이터 종료 -->
						<c:if test="${comFn:nvl(userMgrListTotcnt, 0) == 0}">
							<tr>
								<td colspan="9" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
				         <c:if test="${comFn:nvl(userMgrListTotcnt, 0) > 0}">
					        <c:forEach var="data" items="${userMgrList}" varStatus="status">
					        	<tr  onclick="detailInqire('${data.USR_ID}', '${data.DEPT_NO }')" style="cursor: pointer;">
					        		<td class="txt-center">${data.RNUM}</td>
					        		<td class="txt-center">${data.USR_ID}</td>
					        		<td class="txt-center">${data.EMPL_NO }</td>
									<td class="txt-center">${data.USR_NM }</td>
									<td>${data.DEPT_NM }</td>
									<td>${data.OFPS_NM }</td>
					        		<td>${data.TEL_NO}</td>
					        		<td>${data.EMAL_ADDR}</td>
									<td>${data.AUTH_NM }</td>
					        	</tr>
					        </c:forEach>
				        </c:if>
					</tbody>
				  </table>
			</div>
			
			<!-- pageing -->
			<div class="component-pageing">
				<comTag:pagingIpro totalCount="${comFn:nvl(userMgrListTotcnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
			<!--//pageing -->
		</div>
     </div>
  </form>

<%-- DETAIL FORM --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${ param.resourceName }">
	<input type="hidden" name="P_USR_ID_S">
	<input type="hidden" name="P_LEGACYDEPTCODE">
</form>