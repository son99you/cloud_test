<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.jsp
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/opro/views/estm/estmCmtmCmplVidoMtngDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가진행완료 상세</li>
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
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTR_SECD" value="">
		
		<input type="hidden" name="P_ESTM_CMTM_NO" value="${sessionScope.loginResult.USR_ID}">
		<!-- 심사위원 서명파일 file_grp_no -->
		<input type="hidden" id="P_SIGN_FILE_GRP_NO" name="P_SIGN_FILE_GRP_NO" value="${estmMngMstDetail.SIGN_FILE_GRP_NO }">
		<input type="hidden"  id="P_SIGN_YN" name="P_SIGN_YN" value="${comFn:nvl(estmMngMstDetail.SIGN_YN, 'Y')}">
		
		<!-- 심사위원 첨부파일 등록 여부 -->
		<input type="hidden" id="P_CMTM_FILE_GRP_NO"  name="P_CMTM_FILE_GRP_NO" value="${comFn:nvl(estmMngMstDetail.CMTM_FILE_GRP_NO,"") }">
		
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
						<th>&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:cmplTabEvent(1);">기본정보</a>
				</li>
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${data.ESTR_SECD eq 'A' }"><!-- 평가위원 평가만 보이도록 세팅 -->
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
							<li class="is-selected">
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }','cmpl');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
							<li>
								<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_PSCD }','cmpl');">${data.ESTM_PROCD_NM }</a>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				<li class="is-selected">
					<a href="javascript:cmplTabEvent(8);">화상회의</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 화상회의인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">화상회의정보</div>
				</div>

			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<col width="auto;">
					<col width="150px;">
					<col width="150px;">
					<col width="200px;">
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>화상회의명</th>
						<th>회의시작일시</th>
						<th>회의종료일시</th>
						<th>회의녹화파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty estmVidoList}">
						<tr>
							<td colspan="5" class="txt-center">데이터가 존재하지 않습니다</td>
						</tr>
					</c:if>
					<c:if test="${not empty estmVidoList}">
						<c:forEach var="data" items="${estmVidoList}" varStatus="status">
							<tr>
								<td class="txt-center">
									${data.RNUM }
								</td>
								<td>
									${data.VIDO_MTNG_NM }
									<input type="hidden" class="component-input type-full" name="P_VIDO_MTNG_SEQ" value="${data.VIDO_MTNG_SEQ }">
								</td>
								<td class="txt-center"><!-- 회의시작일시 -->
									${data.VIDO_ST_DT}
								</td>
								<td class="txt-center"><!-- 회의종료일시 -->
									${data.VIDO_END_DT}
								</td>
								<td class="txt-center">
									<a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmVidoFileView('${data.VIDO_MTNG_SEQ }','${data.FILE_GRP_NO }')">보기</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<!-- //화상회의인경우 -->
		
	</form>
	
	<div class="table-detail" style="margin-top: 10px;">
		<div class="option-left">
			<ul class="location">※ 화상회의 참여 방법 : 화상회의시스템을 설치 후 화상회의실에 입장하시기 바랍니다.
			</ul>
		</div>
		
	</div>
	<!-- <div class="table-detail" style="margin-top: 20px;">
		<button type="button" class="btn-download" name="vidoDownBtn" style="float:left;">화상회의 시스템 다운로드</button>
	</div> -->
	
	<!-- bottom button -->
	<div class="bottom-buttons">
		<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
	</div>
	<!-- //bottom button -->

</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO}">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${estmMngMstDetail.ESTM_PROCD_SEQ}">
	<!-- <input type="hidden" name="P_ESTM_PROCD_SEQ"> -->
	<input type="hidden" name="P_VIDO_MTNG_SEQ">
	<input type="hidden" name="P_FILE_GRP_NO" value="">
	<input type="hidden" name="P_UPDT_PSBL_YN" value="N">
</form>