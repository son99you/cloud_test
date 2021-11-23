<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 알림마당 > 공지사항 목록
 *
 * <pre>
 * noti 
 *    |_ noticeListPopup.jsp
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

<script type="text/javascript" src="${jsPath}/opro/views/main/popup/noticeListPopup.js"></script>

<div class="layout-pop">

	<form id="searchFrm" name="searchFrm" method="post">
		<input type="hidden" id="resourceName" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_BBS_SECD" name="P_BBS_SECD" value="${param.P_BBS_SECD}">
		
		<div class="pop_header">
			<div class="title">
				<c:if test="${param.P_BBS_SECD eq 'FAQ'}">FAQ</c:if>
				<c:if test="${param.P_BBS_SECD eq 'NOTI'}">공지</c:if>
				목록
			</div>
		</div>
		
		<div class="form-setting-box">
			<div class="ui-setting">

				<!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">제목</span>
	                <input type="text" name="P_TTL_NM_S"  id="P_TTL_NM_S" value="${param.P_TTL_NM_S}" class="component-input w50"/>
	            </div>
	            <!-- //Form Setting -->
	            
	            <!-- Form Setting -->
	            <div class="form-setting">
	                <span class="txt-label">등록자</span>
	                <input type="text" name="P_REGR_NM_S"  id="P_REGR_NM_S" value="${param.P_REGR_NM_S}" class="component-input w50"/>
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
	                  총 <strong>${comFn:nvl(notiGnrlListTotCnt, 0)}</strong>건
	              </div>
	          </div>
	          <!-- //Right -->
	      </div>
	      <!-- //Option -->

			<table class="component-table">
				<colgroup>
					<col width="8%">
					<col width="*">
					<col width="10%">
					<col width="12%">
					<col width="10%">
					<col width="8%">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">No</th>
						<th>제목</th>
						<th>등록자</th>
						<th class="txt-center">등록일자</th>
						<th class="txt-center">조회수</th>
						<th class="txt-center">파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${comFn:nvl(notiGnrlListTotCnt, 0) == 0}">
						<tr>
							<td class="txtc" colspan="6">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${notiGnrlListTotCnt > 0}">
						<c:forEach var="data" items="${notiGnrlList}" varStatus="status">
							<tr onclick="detailInqire('${data.BBS_SECD }', '${data.BBS_SN}');">
								<td class="txt-center">${data.RNUM}</td>
								<td title="${data.TTL_NM }">${comFn:toShorten(data.TTL_NM, 40)}</td>
								<td>${data.REGR_NM}</td>
								<td class="txt-center">${comFn:formatDate(data.REG_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd')}</td>
								<td class="txt-center">${comFn:nvl(data.INQ_CNT, 0)}</td>
								<td class="txt-center">
									<c:if test="${not empty data.FILE_GRP_NO and data.FILE_CNT > 0}">
										<img src="${imagePath}/ipro/icon/ico_power.png" alt="첨부파일">
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
			<comTag:pagingIpro totalCount="${comFn:nvl(notiGnrlListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
		</div>
		 <!--// list_bottom E -->
		 
		 <div class="bottom-buttons">
		 	<button type="button" class="btn-bottom type-a" onclick="javascript:window.close();">닫기</button>
		</div> <!--// list_bottom E -->			
	</form>
</div>

<%-- DETAIL FORM --%>  
<form id="detailFrm" class="search_form" method="POST" >
	<input type="hidden" name="resourceDesc" value="${ param.resourceDesc }" > 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_BBS_SECD">
	<input type="hidden" name="P_BBS_SN">
</form>