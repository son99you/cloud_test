<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 용어사전 목록
 *
 * <pre>
 * noti 
 *    |_ trmList.jsp
 * 
 * </pre>
 * @date : 2020. 10. 08.
 * @version : 1.0
 * @author : jane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/noti/trmList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">용어사전 목록</li>
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

<form id="searchFrm" name="searchFrm" method="post">
	<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
	
	<div class="page-list">
		<div class="form-setting-box">
			<div class="ui-setting">
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label">제목</span>
					<input type="text" id="P_TTL_NM_S" name="P_TTL_NM_S" value="${param.P_TTL_NM_S}" class="component-input">
				</div>
				<!--// Form Setting -->
				
				<!-- Form Setting -->
				<div class="form-setting">
					<span class="txt-label" style="width: 100px;">용어구분코드</span>
					<input type="text" id="P_TRM_SECD_S" name="P_TRM_SECD_S" value="${param.P_TRM_SECD_S}" class="component-input">
				</div>
				<!--// Form Setting -->
			</div> 
		</div>
	
		<!-- buttons -->
		<div class="setting-button">
			<button type="button" class="component-button" id="searchBtn">조회</button>
		</div>
		<!-- // buttons -->
		
		<div class="area-list">
			<!-- Option -->
			<div class="table-option">
				<!-- Left -->
				<div class="option-left">
					<button class="component-button-s" id="registBtn">신규등록</button>
				</div>
				<!-- //Left -->
	
				<!-- Right -->
				<div class="option-right">
	
					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(trmListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/noti/trmListExcelDwld.do','${comFn:nvl(trmListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->

	
			<table class="component-table">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="*%">
					<col width="15%">
					<col width="10%">
				</colgroup>
				<thead id="excelTh">
					<tr>
						<th class="txt-center">순번</th>
						<th class="txt-center">용어구분코드</th>
						<th>제목</th>
						<th class="txt-center">등록일자</th>
						<th class="txt-center non">파일</th>
					</tr>
				</thead>
		         <tbody>
		         	<!-- 엑셀다운로드데이터 시작 -->
				<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
				<input type="hidden" name="P_EXCEL_TD" value="TRM_SECD_NM"/>
				<input type="hidden" name="P_EXCEL_TD" value="TTL_NM"/>
				<input type="hidden" name="P_EXCEL_TD" value="REG_DT_F"/>
				<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(trmListTotCnt, 0) == 0}">
						<tr>
							<td colspan="5" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${trmListTotCnt > 0}">
						<c:forEach var="data" items="${trmList}" varStatus="status">
							<tr onclick="detailInqire('${data.TRM_SECD }', '${data.TRM_SN}');">
								<td class="txt-center">${data.RNUM}</td>
								<td class="txt-center">${data.TRM_SECD_NM}</td>
								<td>${data.TTL_NM }</td>
								<td class="txt-center">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
								<td class="txt-center">
									<c:if test="${not empty data.FILE_GRP_NO and data.FILE_CNT > 0}">
										<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
		         </tbody>
			</table>
			
	       <!-- pageing -->
		   <div class="component-pageing">
			    <comTag:pagingIpro totalCount="${comFn:nvl(trmListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
	       <!-- // pageing -->
		</div>
	</div>
</form>

<%-- DETAIL FORM --%>  
<form id="detailFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_TRM_SECD">
	<input type="hidden" name="P_TRM_SN">
</form>