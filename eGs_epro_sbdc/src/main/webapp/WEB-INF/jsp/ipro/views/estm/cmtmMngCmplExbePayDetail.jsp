<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가완료현황 상세 - 수당지급
 *
 * <pre>
 * estm
 *    |_ estmCmplExbePayDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngCmplExbePayDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가완료현황 상세</li>
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
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR" name="P_ESTM_CMTM_SLCT_NGR" value="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1)}"><!-- 현재차수  -->
		<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
		
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
				<li>
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
				<li class="is-selected">
					<a href="javascript:cmtmMngCmplTabEvent(9);">수당지급</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 수당지급인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가위원정보</div>
				</div>
			</div>
			<!-- //Top -->

			<div style="overflow-x: scroll; overflow-y:hidden">
				<table class="component-detail-table ">
					<colgroup>
						<%-- <col width="60px"> --%>
						<col width="100px"><!-- 평가위원명 -->
						<col width="100px"><!-- 내/외부 구분 -->
						<col width="100px"><!-- 대분류 -->
						<col width="180px"><!-- 중분류 -->
						<col width="210px"><!-- 소분류 -->
						<col width="110px"><!-- 휴대폰번호 -->
						<col width="200px"><!-- 이메일 -->
						<col width="150px"><!-- 주민등록번호 -->
						<col width="80px"><!-- 예금주 -->
						<col width="100px"><!-- 지급은행 -->
						<col width="200px"><!-- 지급계좌번호 -->
						<col width="120px"><!-- 집행부서코드 -->
						<col width="250px"><!-- 집행부서 -->
						<col width="120px"><!-- 선별구분 -->
						<col width="120px"><!-- 지급상태 -->
						<col width="120px"><!-- 기능 -->
						<col width="120px"><!-- 평가위원첨부 -->
						
					</colgroup>
					<thead>
						<tr>
							<!-- <th>순위</th> -->
							<th>평가위원명</th>
							<th>내/외부 구분</th>
							<th>대분류</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
							<th>주민등록번호</th>
							<th>예금주</th>
							<th>지급은행</th>
							<th>지급계좌번호</th>
							<th>집행부서코드</th>
							<th>집행부서</th>
							<th>선별구분</th>
							<th>지급상태</th>
							<th>기능</th>
							<th>평가위원첨부</th>
						</tr>
					</thead>
					<tbody >
						<c:if test="${not empty cmtmExbePayList}">
							<c:forEach var="data" items="${cmtmExbePayList}" varStatus="status">
								<tr>
									<td class="txt-center">
										<input type="hidden" name="P_ESTM_CMTM_NO" class="component-input w100" value="${data.ESTM_CMTM_NO}">
										<input type="hidden" name="P_ASSESS_DT" class="component-input w100" value="${data.ASSESS_DT}">
										<input type="hidden" name="P_ASSESS_SEQ" class="component-input w100" value="${data.ASSESS_SEQ}">
										${data.ESTM_CMTM_NM }
									</td><!-- 평가위원명 -->
									<td class="txt-center">${data.INO_CMTM_SECD_NM}</td><!-- 내/외부 구분 -->
									<td>${data.LLF_SECD_NM }</td><!-- 대분류 -->
									<td>${data.MLF_SECD_NM }</td><!-- 중분류 -->
									<td>${data.SLF_SECD_NM }</td><!-- 소분류 -->
									<td>${data.CP_NO }</td><!-- 휴대폰번호 -->
									<td>${data.EMAL }</td><!-- 이메일 -->
									<td class="txt-center">
										<%-- ${data.RSDN_NO_1 } -${data.RSDN_NO_3 } --%>
										${data.RSDN_NO_1 } - ${data.RSDN_NO_2 }******
										<input type="hidden" id="P_RSDN_NO${status.count}" name="P_RSDN_NO" class="component-input w100" value="${data.RSDN_NO}">
										</td><!-- 주민등록번호 -->
									<td class="txt-center">
										<input type="text" id="P_DEPOSITOR_NM${status.count}" name="P_DEPOSITOR_NM" class="component-input w100 chk20" value="${data.DEPOSITOR_NM}" maxLength="20">
										<!-- 예금주 -->
									</td>
									<td class="txt-center">
										<%-- <input type="text" id="P_BANK_HQ_NM" name="P_BANK_HQ_NM" class="component-input w100" value="${data.BANK_HQ_NM}"> --%>
										<comTag:comCmcdCdValueComboBox id="P_BANK_HQ" name="P_BANK_HQ" cdId="BKCD" selectKey="${data.BANK_HQ}" headerValue="선택" className="component-select w100"/>
										<!-- 지급은행 -->
									</td>
									<td class="txt-center">
										<input type="text" id="P_BANK_ACCT${status.count}" name="P_BANK_ACCT" class="component-input w100" value="${data.BANK_ACCT}">
										<!-- 지급계좌번호 -->
									</td>
									<td class="txt-center">
										<input type="hidden" id="P_YM${status.count}" name="P_YM" class="component-input w100" value="${data.YM}">
										<input type="hidden" id="P_PAY_STORE${status.count}" name="P_PAY_STORE" class="component-input w100" value="${data.PAY_STORE}">
										<input type="text" id="P_PAY_ORGAN${status.count }" name="P_PAY_ORGAN" class="component-input w100" value="${data.PAY_ORGAN}" readonly="readonly">
										<!-- 집행부서코드 -->
									</td>
									<td class="txt-center">
										<div class="component-input-search">
											<input type="text" id="P_PAY_ORGAN_NM${status.count }" name="P_PAY_ORGAN_NM" class="component-input w50" value="${data.PAY_ORGAN_NM}"  readonly="readonly">
											<c:if test="${empty data.STATUS or data.STATUS eq 'C'}">
												<a href="javascript:" class="btn-search-close" id="estmChrgDeptDelBtn${status.count }" name="estmChrgDeptDelBtn"></a>
												<a href="javascript:" class="btn-search" id="estmChrgDepSrchBtn${status.count }" name="estmChrgDepSrchBtn"></a>
											</c:if>
										</div>
										<!-- 집행부서 -->
									</td>
									<td class="txt-center">
										${data.SLCT_SECD_NM}
										<!-- 선별구분 -->
									</td>
									<td class="txt-center">
										<c:if test="${data.PAY_STATUS eq '20'}">지불대기</c:if>
										<c:if test="${data.PAY_STATUS eq '30'}">지불예정</c:if>
										<c:if test="${data.PAY_STATUS eq '35'}">지불확정</c:if>
										<c:if test="${data.PAY_STATUS eq '38'}">지불집행</c:if>
										<!-- 지급상태 -->
									</td>
									<td class="txt-center"><!-- 기능 -->
										<c:if test="${data.STATUS eq 'U'}">정상</c:if>
										<c:if test="${data.STATUS eq 'R'}">재요청</c:if>
										<c:if test="${data.STATUS eq 'D'}">취소</c:if>
										<c:if test="${data.STATUS eq 'C'}">
											<a href="javascript:" class="component-button-s type-a" id="paySubmit${status.count }" name="paySubmit" type="button">재요청</a>
										</c:if>
										<c:if test="${empty data.STATUS}">
											<a href="javascript:" class="component-button-s type-a" id="paySubmit${status.count }" name="paySubmit" type="button">지급요청</a>
										</c:if>
									</td>
									<td class="txt-center"><!-- 평가위원첨부파일 -->
										<a href="javascript:" onclick="pageObj.estmCmtmFileView('${data.ESTM_CMTM_NO }', '${data.FILE_GRP_NO}')">
											<img src="${imagePath}/comm/sub/icon_file.png" alt="첨부파일">
										</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- //수당지급인경우 -->
		
	</form>
	
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
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO">
	<input type="hidden" name="P_ESTM_CMTM_NO">
	<input type="hidden" name="P_SELECT_NUMBER">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>

<form id="PayFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ASSESS_DT" value=""><!-- 지급요청일자 -->
	<input type="hidden" name="P_ASSESS_SEQ" value=""><!-- 지급요청번호 -->
	<input type="hidden" name="P_ESTM_CMTM_NO" value=""><!-- 평가위원번호 -->
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }"><!-- 평가번호 -->
	<input type="hidden" name="P_ESTM_NM" value="${estmMngMstDetail.ESTM_NM }"><!-- 평가명 -->
	<input type="hidden" name="P_RSDN_NO"><!-- 평가위원주민번호 -->
	<input type="hidden" name="P_DEPOSITOR_NM"><!-- 예금주 -->
	<input type="hidden" name="P_BANK_HQ"><!-- 지급은행코드 -->
	<input type="hidden" name="P_BANK_ACCT"><!-- 지급계좌번호 -->
	<input type="hidden" name="P_YM"><!-- 년월 -->
	<input type="hidden" name="P_PAY_STORE"><!-- STORE -->
	<input type="hidden" name="P_PAY_ORGAN"><!-- ORGAN -->
</form>
