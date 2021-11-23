<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 상세 - 평가위원
 *
 * <pre>
 * estm
 *    |_ cmtmMngCmplCmtmDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/cmtmMngCmplCmtmDetail.js"></script>

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
		
		<!-- 동적 Tab 이동시 필요 -->
		<input type="hidden" name="P_ESTM_PROCD_SEQ">
		<input type="hidden" name="P_ESTR_SECD">
		
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR" name="P_ESTM_CMTM_SLCT_NGR" value="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1)}"><!-- 현재차수  -->
		<input type="hidden" id="P_ESTM_CMTM_SLCT_NGR_CLICK" name="P_ESTM_CMTM_SLCT_NGR_CLICK" value="${ P_ESTM_CMTM_SLCT_NGR_CLICK}"><!-- 선정차수 버튼 클릭 -->
		<input type="hidden" id="P_CMPL_YN" name="P_CMPL_YN" value="${param.P_CMPL_YN }"><!-- 최종여부 -->
		
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
				<li class="is-selected">
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
		
		<div class="table-detail">
			<div class="type-fleft">
				<c:if test="${comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1) > 0 }">
					
					<c:set var="btnClass" value="type-add" />
					
					<c:forEach begin="1" end="${ comFn:nvl(estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR, 1) }" step="1" var="estmCmtmSlctNgr" varStatus="loop">
						<c:if test="${ estmCmtmSlctNgr ne P_ESTM_CMTM_SLCT_NGR_CLICK}">
							<c:set var="btnClass" value="type-del" />
						</c:if>
						<c:if test="${ estmCmtmSlctNgr eq P_ESTM_CMTM_SLCT_NGR}">
							<c:set var="btnClass" value="type-add" />
						</c:if>
						<a href="javascript:" class="component-button-s ${btnClass }" onclick="estmCmtmSlctNgrDetail('${estmCmtmSlctNgr }')">${estmCmtmSlctNgr }</a>
					</c:forEach>
					
					<c:set var="btnClass" value="type-N" />
					<c:if test="${estmMngMstDetail.CMTM_SLCT_CMPL_YN eq 'Y'}">
						<c:if test="${ param.P_CMPL_YN eq 'Y'}">
							<c:set var="btnClass" value="type-Y" />
						</c:if>
						<a href="javascript:" class="component-button-s ${btnClass }" onclick="estmCmtmSlctCmplDetail()">최종</a>
					</c:if>
				</c:if>
			</div>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">내부평가위원정보&nbsp;(${estmMngMstDetail.INN_ESTM_CMTM_CNT }&nbsp;명) : ${estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD_NM }</div>
				</div>
				
				<div class="type-fright">
					<c:if test="${not empty estmMngMstDetail.INN_CMTM_SLCT_CNT}">
						<div class="contents-sub_label">현재 선정된 평가위원 수 : ${estmMngMstDetail.INN_CMTM_SLCT_CNT}</div>
					</c:if>
				</div>
			</div>
			<!-- //Top -->
			
			<div style="overflow-x: scroll; overflow-y:hidden">
				<table class="component-detail-table ">
					<colgroup>
						<col width="80px">
						<col width="60px">
						<col width="100px">
						<col width="100px">
						<col width="110px">
						<col width="200px">
						<col width="100px"><!-- 선정여부 -->
						<col width="200px"><!-- 비고 -->
						<col width="100px"><!-- 대분류 -->
						<col width="100px"><!-- 내역 -->
						<col width="180px">
						<col width="210px">
						<col width="80px">
						
					</colgroup>
					<thead>
						<tr>
							<th>선정차수</th>
							<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD eq 'B' }">
								<th>순위</th>
							</c:if>
							<c:if test="${ estmMngMstDetail.INN_CMTM_SLCT_MTHD_SECD ne 'B' }">
								<th>선택</th>
							</c:if>
							<th>평가위원명</th>
							<th>내/외부 구분</th>
							<th>휴대폰번호</th>
							<th>이메일</th>
							<th>선정여부</th>
							<th>비고</th>
							<th>대분류</th>
							<th>내역</th>
							<th>중분류</th>
							<th>소분류</th>
							<th>선별구분</th>
						</tr>
					</thead>
					<tbody id="inShowTbdy">
						<c:if test="${param.P_PRIO_RNK_RE_SLCT ne 'Y'}">
							<c:if test="${not empty innEstmCmtmList}">
								<c:forEach var="data" items="${innEstmCmtmList}" varStatus="status">
									<tr>
										<td class="txt-center">${data.ESTM_CMTM_SLCT_NGR }</td>
										<td class="txt-center">${data.PRIO_RNK }</td>
										<td class="txt-center">${data.ESTM_CMTM_NM }</td>
										<td class="txt-center">${data.INO_CMTM_SECD_NM}</td>
										<td>${data.CP_NO }</td>
										<td>${data.EMAL }</td>
										<td class="txt-center"><!-- 선정여부 -->
											<c:if test="${ data.SLCT_YN == 'Y' }">선정</c:if>
											<c:if test="${ data.SLCT_YN == 'N' }"><font color="red">비선정</font></c:if>
										</td>
										<td>${data.RMK }</td>
										<td>${data.LLF_SECD_NM }</td>
										<td>${data.CNTN_SECD_NM }</td>
										<td>${data.MLF_SECD_NM }</td>
										<td>${data.SLF_SECD_NM }</td>
										<td class="txt-center">${data.SLCT_SECD_NM}</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		
		
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