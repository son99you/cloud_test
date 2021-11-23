<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ cmtmMngCmplObjDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngCmplObjDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가위원평가완료현황 목록</li>
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

<!-- Detail -->
<div class="area-detail">
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" name="P_REGR_NM" value="${sessionScope.loginResult.USR_NM}">
		<input type="hidden" name="P_USR_ID" value="${sessionScope.loginResult.USR_ID}">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR }">
		<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
		
		<!-- 동적 Tab 이동시 필요 -->
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<input type="hidden" name="P_ESTR_SECD">
		
		<!-- 필수정보 고정 -->
		<div class="table-detail">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>평가번호</th>
						<td>${estmMngMstDetail.ESTM_NO }</td>
						<th>평가진행상태</th>
						<td>${estmMngMstDetail.ESTM_PSCD_NM }</td>
					</tr>
					<tr>
						<th>평가명</th>
						<td colspan="3">${estmMngMstDetail.ESTM_NM }</td>
					</tr>
					<tr>
						<th>평가구분</th>
						<td>${estmMngMstDetail.ESTM_SECD_NM }</td>
						<th>평가정보연계번호</th>
						<td>${estmMngMstDetail.ESTM_INFO_CNTC_NO }</td>
					</tr>
					<tr>
						<th>평가분야구분</th>
						<td>${estmMngMstDetail.ESTM_SPHE_SECD_NM }</td>
						<th>평가대상구분</th>
						<td>평가위원</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:cmtmMngCmplTabEvent(1);">기본정보</a>
				</li>
				<li class="is-selected">
					<a href="javascript:cmtmMngCmplTabEvent(2);">평가대상</a>
				</li>
				<li>
					<a href="javascript:cmtmMngCmplTabEvent(3);">평가위원</a>
				</li>

				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','cmtmMngCmpl');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','cmtmMngCmpl');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				
				<li class="">
					<a href="javascript:cmtmMngCmplTabEvent(7);">평가결과</a>
				</li>
				<li class="">
					<a href="javascript:cmtmMngCmplTabEvent(8);">화상회의</a>
				</li>
				<li class="">
					<a href="javascript:cmtmMngCmplTabEvent(9);">수당지급</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 평가위원이 크리에이터인 경우 -->
		<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'C'}">
			<div class="table-detail">
				<!-- Top -->
				<div class="top-detail">
					<div class="type-fleft">
						<div class="contents-label">평가대상정보 - 사람(평가위원)</div>
					</div>
				</div>
				<!-- //Top -->
	
				<table class="component-detail-table ">
					<colgroup>
						<col width="6%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="25%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>신청자명</th>
							<th>주민번호</th>
							<th>전화번호</th>
							<th>이메일</th>
							<th>첨부파일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty estmObjList}">
							<tr>
								<td colspan="6" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty estmObjList}">
							<c:forEach var="data" items="${estmObjList}" varStatus="status">
								<tr>
									<td class="txt-center">${data.ESTM_OBJ_SEQ}</td>
									<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', 'CMTM', '${data.ESTM_CMTM_NO}');">${data.ESTM_CMTM_NM }</td>
									<td class="txt-center">
										${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
									</td>
									<td>${data.TEL_NO }</td>
									<td>${data.EMAL }</td>
									<td class="txt-center"><a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')">보기</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:if>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
		</div>
		<!-- //bottom button -->
	</form>
	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>
<form id="popupFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SEQ">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_UPDT_PSBL_YN">
</form>