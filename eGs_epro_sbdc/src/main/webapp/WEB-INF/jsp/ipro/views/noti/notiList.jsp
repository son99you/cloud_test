<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 공지사항 목록
 *
 * <pre>
 * noti 
 *    |_ notiList.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 이주연
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/noti/notiList.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">공지사항 목록</li>
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

<div class="layout-contents">
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
				
				<div class="form-setting">
					<span class="txt-label">등록자</span>
					<input type="text" id="P_REGR_NM_S" name="P_REGR_NM_S" value="${param.P_REGR_NM_S}" class="component-input">
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
			
				<!-- Left -->
				<div class="option-left">
				
				<c:if test="${loginResult.AUTH_ID eq '1' }">
					<button type="button" class="component-button-s" id="regBtn">신규등록</button>
				</c:if>
					
				</div>
				<!-- //Left -->

				<!-- Right -->
				<div class="option-right">

					<div class="table-num type-fleft">
						총 <strong>${comFn:nvl(notiListTotCnt, 0)}</strong>건
					</div>
					<a href="javascript:excelDwd('searchFrm', '/noti/notiListExcelDwld.do','${comFn:nvl(notiListTotCnt, 0)}');" class="btn-download-s type-fleft">
						<i class="icon-download"></i>엑셀 다운로드
					</a>
				</div>
				<!-- //Right -->
			</div>
			<!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="8%">
             		<col width="*">
             		<col width="12%">
             		<col width="15%">
             		<col width="8%">
             		<col width="8%">
				</colgroup>
				<thead id="excelTh">
					<tr>
						<th class="txt-center">순번</th>
						<th>제목</th>
						<th class="txt-center">등록자</th>
						<th class="txt-center">등록일자</th>
						<th class="txt-center">조회수</th>
						<th class="txt-center non">파일</th>
					</tr>
				</thead>
				<tbody>
					<!-- 엑셀다운로드데이터 시작 -->
					<input type="hidden" name="P_EXCEL_TD" value="RNUM"/>
					<input type="hidden" name="P_EXCEL_TD" value="TTL_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="REGR_NM"/>
					<input type="hidden" name="P_EXCEL_TD" value="REG_DT_F"/>
					<input type="hidden" name="P_EXCEL_TD" value="INQ_CNT"/>
					<!-- //엑셀다운로드데이터 종료 -->
					<c:if test="${comFn:nvl(notiListTotCnt, 0) == 0}">
						<tr>
							<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					
					<c:if test="${notiListTotCnt > 0}">
						<c:forEach var="data" items="${notiList}" varStatus="status">
							<tr onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<td class="txt-center">${data.RNUM}</td>
								<td title="${data.TTL_NM}">${comFn:toShorten(data.TTL_NM, 40)}</td>
								<td class="txt-center">${data.REGR_NM }</td>
								<td class="txt-center">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
								<td class="txt-center">${comFn:nvl(data.INQ_CNT, 0)}</td>
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
		</div>
		
		<!-- pageing -->
		<div class="component-pageing">
			<comTag:pagingIpro totalCount="${comFn:nvl(notiListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		<!--//pageing -->
		
	</div>
</form>
</div>

<%-- DETAIL FORM --%>  
<form id="detailFrm" class="search_form" method="POST">
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>