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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmCmtmCmplProcdBDetail.js"></script>

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
						<td>${estmMngMstDetail.ESTM_OBJ_SECD_NM }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //필수정보 고정 -->
		
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:cmtmCmplTabEvent(1);">기본정보</a>
				</li>
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicCmtmEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_SECD }','cmpl');">${data.ESTM_PROCD_NM }</a>						
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicCmtmEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm','${data.ESTM_PROCD_SECD }','cmpl');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:cmtmCmplTabEvent(7);">평가결과</a>
				</li>
				<li class="">
					<a href="javascript:cmtmCmplTabEvent(8);">화상회의</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<!-- 절차진행상태 고정 -->
		<div class="table-detail">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="85%">
				</colgroup>
				<tbody>
					<tr>
						<th>절차진행상태</th>
						<td>${estmProcdDetail.ESTM_PROCD_PSNM}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- //절차진행상태 고정 -->
		
		
		<!-- 정량평가인경우 -->
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">평가목록</div>
				</div>
			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<%-- <col width="80px;"> --%>
					<col width="auto">
					<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
						<col width="150px;"><!-- 상품명 -->
					</c:if>
					<col width="100px;">
					<col width="250px;">
					<%-- <col width="120px;"> --%>
				</colgroup>
				<thead>
					<tr>
						<th rowspan="1" style="vertical-align: middle;">순위</th>
						<th rowspan="1" style="vertical-align: middle;">평가대상명</th>
						<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
							<th rowspan="1" style="vertical-align: middle;">상품명</th>
						</c:if>
						<th rowspan="1" style="vertical-align: middle;">평가대상<br>첨부파일</th>
						<%-- <th colspan="${estmCmtmLastListCnt }" style="vertical-align: middle;">심사위원</th> --%>
						<th rowspan="1" style="vertical-align: middle;">총점</th>
					</tr>
					<tr>
						<%-- <c:forEach var="data" items="${estmCmtmLastList}" varStatus="status">
							<th>${data.ESTM_CMTM_NM }</th>
						</c:forEach> --%>
					</tr>
				</thead>
				<tbody>
					<!-- 세션로그인 한 사람이 평가위원중 한명인지 체크하는 로직 -->
					<c:set var="cmtmAt" value="F" />
					<c:forEach var="cmtm" items="${estmCmtmLastList}" varStatus="status">
						<c:if test="${cmtm.ESTM_CMTM_NO eq sessionScope.loginResult.USR_ID }">
							<c:set var="cmtmAt" value="T" />
						</c:if>
					</c:forEach>
					<!-- //세션로그인 한 사람이 평가위원중 한명인지 체크하는 로직 -->
					
					<c:forEach var="data" items="${estmObjList}" varStatus="status">
					<tr>
						<td class="txt-center"></td>
						<c:if test="${estmMngMstDetail.ESTM_SECD eq 'E006'}">
							<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', 'CMTM', '${data.ESTM_CMTM_NO}');">
								${data.OBJ_NM }
							</td>
						</c:if>
						<c:if test="${estmMngMstDetail.ESTM_SECD ne 'E006'}">
							<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'A'}">
								<td class="txt-center">
									<a href="javascript:" onclick="estmObjImstarsMainView('popupFrm', '${data.ITEM_NO }', '${data.BIZRNO }', '${data.ESTM_OBJ_SEQ }')">${data.OBJ_NM }</a>
								</td>
							</c:if>
							<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
								<td class="txt-center" style="cursor: pointer;" onclick="estmObjImstarsMainView('popupFrm', '${data.ITEM_NO}', '${data.BIZRNO}', '${data.ESTM_OBJ_SEQ }');">
									${data.OBJ_NM }
								</td>
								<td class="txt-center" style="cursor: pointer;" onclick="estmObjImstarsDetailView('popupFrm', '${data.ITEM_NO}', '${data.BIZRNO}', '${data.ESTM_OBJ_SEQ }');">
									${data.ITEM_NM }
								</td>
							</c:if>
							<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'C' or estmMngMstDetail.ESTM_OBJ_SECD eq 'D' or estmMngMstDetail.ESTM_OBJ_SECD eq 'E'}">
								<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', '${estmMngMstDetail.ESTM_OBJ_SECD}', '${data.ESTM_OBJ_PE_NO}');">
									${data.ESTM_OBJ_PE_NM }
								</td>
							</c:if>
							<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'C' or estmMngMstDetail.ESTM_OBJ_SECD eq 'D' or estmMngMstDetail.ESTM_OBJ_SECD eq 'E'}">
								<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', '${estmMngMstDetail.ESTM_OBJ_SECD}', '${data.ESTM_OBJ_PE_NO}');">
									${data.ESTM_OBJ_PE_NM }
								</td>
							</c:if>
						</c:if>
						<td class="txt-center">
							<a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')">보기</a>
						</td>
						<!-- 세션로그인 한 사람이 평가위원중 한명인지 체크하여 아니면 일괄적으로 0으로 세팅하고 평가팝업에서 저장을 할 수 없도록 구현하기 -->
						<c:if test="${ cmtmAt eq 'T' }">
						<c:forEach var="cmtm" items="${estmCmtmLastList}" varStatus="status">
							<c:forEach var="cmtmVal" items="${estmValueList}" varStatus="status">
								<c:if test="${data.ESTM_OBJ_SEQ eq cmtmVal.ESTM_OBJ_SEQ }"><!-- 업체 -->
									<c:if test="${cmtm.ESTM_CMTM_NO eq cmtmVal.ESTM_CMTM_NO }"><!-- 평가위원 -->
										<c:if test="${cmtm.ESTM_CMTM_NO eq sessionScope.loginResult.USR_ID }"><!-- 나의 평가위원 체크 -->
											<td class="txt-center" onclick="detailInqirePopup('${data.ESTM_OBJ_SEQ}','${cmtm.ESTM_CMTM_NO}','A');" style="cursor: pointer;">${cmtmVal.CMTM_TOT_VAL }</td>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
						</c:forEach>
						</c:if>
						<c:if test="${ cmtmAt eq 'F' }">
							<td class="txt-center" onclick="detailInqirePopup('${data.ESTM_OBJ_SEQ}','cmtmAtFalse','A');" style="cursor: pointer;">0</td>
						</c:if>
						<!-- //세션로그인 한 사람이 평가위원중 한명인지 체크하여 아니면 일괄적으로 0으로 세팅하고 평가팝업에서 저장을 할 수 없도록 구현하기 -->
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- //정량평가인경우 -->
		
	</form>
	
</div>
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="popupFrm" method="POST"> 
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${estmProcdDetail.ESTM_PROCD_SEQ }">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SEQ"/>
	<input type="hidden" name="P_ESTM_CMTM_NO"/>
	<input type="hidden" name="P_ESTM_OBJ_PE_NO"/>
	<input type="hidden" name="P_GBN" value="cmpl"/>
	<input type="hidden" name="P_BSNM_REGIST_NO">
	<input type="hidden" name="P_GOODSNO">
	<input type="hidden" name="P_REQST_NO">
	<input type="hidden" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }">
</form>