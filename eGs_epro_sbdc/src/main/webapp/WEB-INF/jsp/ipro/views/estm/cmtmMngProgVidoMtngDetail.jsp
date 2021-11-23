<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 상세 - 화상회의
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgVidoMtngDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngProgVidoMtngDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가위원평가진행현황 상세</li>
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
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ }">
		<input type="hidden" name="P_ESTM_OBJ_SECD" value="${estmMngMstDetail.ESTM_OBJ_SECD}">
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
					<a href="javascript:cmtmMngProgTabEvent(1);">기본정보</a>
				</li>
				<li>
					<a href="javascript:cmtmMngProgTabEvent(2);">평가대상</a>
				</li>
				<li>
					<a href="javascript:cmtmMngProgTabEvent(3);">평가위원</a>
				</li>
				<!--
					2021.04.15 손연우
					평가절차에 따른 탭 리스트형식으로 받아오기 
				 -->
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','cmtmMngProg');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm', 'cmtmMngProg');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:cmtmMngProgTabEvent(7);">평가결과</a>
				</li>
				<li class="is-selected">
					<a href="javascript:cmtmMngProgTabEvent(8);">화상회의</a>
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

				<div class="type-fright">
					<button type="button" id="addBtn" class="component-button-s type-add">추가</buton>
					<button type="button" id="delBtn" class="component-button-s type-del">삭제</buton>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="60px;">
					<col width="auto;">
					<col width="210px;">
					<col width="210px;">
					<col width="100px;">
					<col width="100px;">
					<col width="100px;">
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>화상회의명</th>
						<th>회의시작일시</th>
						<th>회의종료일시</th>
						<th>참가자목록</th>
						<!-- <th>상태</th> -->
						<th>회의입장</th>
						<th>회의녹화파일</th>
					</tr>
				</thead>
				<!-- <tbody id="hiddenTbody">
					<tr style="display: none;">
						<td class="txt-center">
							<label class="component-checkbox">
								<input type="checkbox" name="chck">
								<i></i>
							</label>
						</td>
						<td><input type="text" class="component-input type-full" name="P_VIDO_MTNG_NM" disabled="disabled"></td>
						<td>
							<div class="area-calen">
								Component Calen
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_ST_DE" class="component-input" date>
									</div>
								</div>
								//Component Calen
		
								Component Time
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_ST_HH" class="component-input" numeric maxlength="2">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_ST_MM" class="component-input" numeric maxlength="2">
								</div>
								//Component Time
							</div>
						</td>
						<td>
							<div class="area-calen">
								Component Calen
								<div class="component-calen">
									<div class="data-calen">
										<input type="text" name="P_ESTM_PROCD_END_DE" class="component-input" date>
									</div>
								</div>
								//Component Calen
		
								Component Time
								<div class="component-time">
									<input type="text" name="P_ESTM_PROCD_END_HH" class="component-input" numeric maxlength="2">
									<em class="time-bar">:</em>
									<input type="text" name="P_ESTM_PROCD_END_MM" class="component-input" numeric maxlength="2">
								</div>
								//Component Time
							</div>
						</td>
						<td class="txt-center">
							<button name="mtngPrtcRqs" class="component-button-s type-a">보기</button>
						</td>
						<td>
							<input type="hidden" name="P_VIDO_MTNG_PRST_SECD"  disabled="disabled"/>
						</td>
						<input type="hidden" name="P_VIDO_MTNG_PRST_SECD"  disabled="disabled"/>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody> -->
				<tbody id="showTbody">
					<c:forEach var="data" items="${estmVidoList}" varStatus="status">
						<tr>
							<td class="txt-center">
								<c:if test="${empty data.VIDO_MTNG_PRST_SECD}">
									<label class="component-checkbox">
										<input type="checkbox" name="chck">
										<i></i>
									</label>
								</c:if>
							</td>
							<td>
								<c:if test="${not empty data.VIDO_MTNG_PRST_SECD}">
									${data.VIDO_MTNG_NM }
									<input type="hidden" class="component-input type-full" name="P_VIDO_MTNG_NM" value="${data.VIDO_MTNG_NM }">
									<%-- <input type="hidden" class="component-input type-full" id="P_VIDO_MTNG_SEQ" name="P_VIDO_MTNG_SEQ" value="${data.VIDO_MTNG_SEQ }"> --%>
								</c:if>
								<c:if test="${empty data.VIDO_MTNG_PRST_SECD}">
									<input type="text" class="component-input type-full" name="P_VIDO_MTNG_NM" value="${data.VIDO_MTNG_NM }">
									<%-- <input type="hidden" class="component-input type-full" id="P_VIDO_MTNG_SEQ" name="P_VIDO_MTNG_SEQ" value="${data.VIDO_MTNG_SEQ }"> --%>
								</c:if>
								<input type="hidden" class="component-input type-full" id="P_VIDO_MTNG_SEQ" name="P_VIDO_MTNG_SEQ" value="${data.VIDO_MTNG_SEQ }">
							</td>
							<td class="txt-center"><!-- 회의시작일시 -->
								${data.VIDO_ST_DT}
								<input type="hidden" class="component-input type-full" id="P_VIDO_ST_DE" name="P_VIDO_ST_DE" value="${data.VIDO_ST_DE }">
								<input type="hidden" class="component-input type-full" id="P_VIDO_ST_HH" name="P_VIDO_ST_HH" value="${data.VIDO_ST_HH }">
								<input type="hidden" class="component-input type-full" id="P_VIDO_ST_MM" name="P_VIDO_ST_MM" value="${data.VIDO_ST_MM }">
							</td>
							<td class="txt-center"><!-- 회의종료일시 -->
								${data.VIDO_END_DT}
								<input type="hidden" class="component-input type-full" id="P_VIDO_END_DE" name="P_VIDO_END_DE" value="${data.VIDO_END_DE }">
								<input type="hidden" class="component-input type-full" id="P_VIDO_END_HH" name="P_VIDO_END_HH" value="${data.VIDO_END_HH }">
								<input type="hidden" class="component-input type-full" id="P_VIDO_END_MM" name="P_VIDO_END_MM" value="${data.VIDO_END_MM }">
							</td>
							<td class="txt-center">
								<c:if test="${not empty data.VIDO_MTNG_PRST_SECD}">
									<button type="button" name="mtngPrtcRqs" class="component-button-s type-a">보기</button>
								</c:if>
								
							</td>
							<%-- <td>
								${data.VIDO_MTNG_PRST_SECD_NM}
								<input type="hidden" name="P_VIDO_MTNG_PRST_SECD" value="${data.VIDO_MTNG_PRST_SECD }"/>
							</td> --%>
							<input type="hidden" name="P_VIDO_MTNG_PRST_SECD" value="${data.VIDO_MTNG_PRST_SECD }"/>
							
							
							<td class="txt-center">
								<c:if test="${not empty data.VIDO_MTNG_PRST_SECD }">
									<c:if test="${not empty data.VIDO_MTNG_URL_INFO }">
										<%-- <a type="button" href="${data.VIDO_MTNG_URL_INFO }" class='component-button-s type-a' >방입장</a> --%>
										<a type="button" style="cursor: pointer;" onclick="moveRoom('${data.VIDO_MTNG_URL_INFO }');" class='component-button-s type-a' >방입장</a>
									</c:if>
									<!-- <a type="button" href="sc-sbdc21://?ext_room_id=190-1-1&ext_user_id=1150041&ext_password=556bbefc1c&nickname=이준성" class='component-button-s type-a' >방입장</a> -->
								</c:if>
								<c:if test="${empty data.VIDO_MTNG_PRST_SECD }">
									<button type="button" name="mtngMvmt" class="component-button-s type-a">방생성</button>
								</c:if>
							</td>
							<td class="txt-center">
								<a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmVidoFileView('${data.VIDO_MTNG_SEQ }','${data.FILE_GRP_NO }')">보기</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- //화상회의인경우 -->
		
		
		<div class="option-left" style="margin-top: 30px;">
			<ul class="location">※ 화상회의 참여 방법 : 화상회의시스템을 설치 후 화상회의실에 입장하시기 바랍니다.
			</ul>
		</div>
	</form>
	
	
	<!-- bottom button -->
	<div class="bottom-buttons">
		<a href="javascript:" class="btn-bottom type-b" id="saveBtn">저장</a>
		<a href="javascript:" class="btn-bottom type-a" id="listBtn">목록</a>
	</div>
	<!-- //bottom button -->
	
<!-- 	
	<div class="table-detail" style="margin-top: 10px;">
		<div class="option-left">
			<ul class="location">※ 화상회의 참여 방법 : 화상회의시스템을 설치 후 화상회의실에 입장하시기 바랍니다.
			</ul>
		</div>
		
	</div>
 -->
	<!-- <div class="table-detail" style="margin-top: 20px;">
		<button type="button" class="btn-download" name="vidoDownBtn" style="float:left;">화상회의 시스템 다운로드</button>
	</div> -->
	
</div>
<!-- //Detail -->

<form id="meetFrm" name="meetFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="1"><!-- P_ESTM_PROCD_SEQ 화상회의는 1로 고정-->
	<input type="hidden" name="P_VIDO_MTNG_SEQ" value="">
	
	
</form>

<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO}">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${estmMngMstDetail.ESTM_PROCD_SEQ}">
	<input type="hidden" name="P_VIDO_MTNG_SEQ" value="">
	<input type="hidden" name="P_FILE_GRP_NO" value="">
	<input type="hidden" name="P_UPDT_PSBL_YN" value="Y">
	<input type="hidden" name="P_ESTM_OBJ_SECD">
</form>