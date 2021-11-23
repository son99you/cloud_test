<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가진행현황 상세 - 정량평가 
 *
 * <pre>
 * estm
 *    |_ estmProgProcdBDetail.jsp
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

<script type="text/javascript" src="${jsPath}/ipro/views/estm/estmProgProcdBDetail.js"></script>

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
					<li style="font-size: 30px; font-weight: 500;">평가진행현황 상세</li>
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
		<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${param.P_ESTM_PROCD_SEQ}">
		<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
		<input type="hidden" name="P_ESTR_SECD">
		<input type="hidden" name="P_ESTM_CMTM_SLCT_NGR" value="${estmMngMstDetail.MAX_ESTM_CMTM_SLCT_NGR }">
		<input type="hidden" name="P_CMPL_YN" value="${estmMngMstDetail.CMTM_SLCT_CMPL_YN }">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 100)}">
		
		<input type="hidden" id="resultCode" value="${resultCode }">
		
		
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
					<a href="javascript:tabEvent(1);">기본정보</a>
				</li>
				<li>
					<a href="javascript:tabEvent(2);">평가대상</a>
				</li>
				<li>
					<a href="javascript:tabEvent(3);">평가위원</a>
				</li>
				<!--
					2021.04.15 손연우
					평가절차에 따른 탭 리스트형식으로 받아오기
					ESTM_PROCD_SEQ : 평가절차순번
					ESTR_SECD : 평가자구분
				 -->
				<c:forEach var="data" items="${estmTabList}" varStatus="status">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ eq data.ESTM_PROCD_SEQ }">
						<li class="is-selected">
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm', '');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SEQ ne data.ESTM_PROCD_SEQ }">
						<li>
							<a href="javascript:dynamicEstmTabEvent('${data.ESTM_PROCD_SEQ }','${data.ESTR_SECD }','detailFrm', '');">${data.ESTM_PROCD_NM }</a>
						</li>
					</c:if>
				</c:forEach>
				<li>
					<a href="javascript:tabEvent(7);">평가결과</a>
				</li>
				<li class="">
					<a href="javascript:tabEvent(8);">화상회의</a>
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
				<div class="type-fleft" style="float: right">
					<div class="contents-label">총 <strong>${comFn:nvl(estmObjListTotCnt, 0)}</strong>건</div>
				</div>

			</div>
			<!-- //Top -->

			<table class="component-detail-table ">
				<colgroup>
					<col width="80px;">
					<col width="250px;">
					<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
						<col width="150px;"><!-- 상품명 -->
					</c:if>
					<col width="100px;">
					<col width="auto">
					<c:if test="${estmProcdDetail.ESTM_PROCD_SECD ne 'C'}">
						<col width="120px;">
					</c:if>
					<c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C' and estmProcdDetail.ESTM_PROCD_PSCD eq 'B002'  }">
						<c:if test="${not empty data.ESTM_RNK }">
							<col width="100px;"><!-- 선정여부 -->
						</c:if>
					</c:if>
				</colgroup>
				<thead>
					<tr>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C'}">
							<th rowspan="2" style="vertical-align: middle;">순위</th>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD ne 'C' }">
							<th rowspan="2" style="vertical-align: middle;">순번</th>
						</c:if>
						
						<th rowspan="2" style="vertical-align: middle;">평가대상명</th>
						<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'B'}">
							<th rowspan="2" style="vertical-align: middle;">상품명</th>
						</c:if>
						<th rowspan="2" style="vertical-align: middle;">평가대상<br>첨부파일</th>
						<th colspan="${estmCmtmLastListCnt }" style="vertical-align: middle;">심사담당자</th>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD ne 'C'}">
							<th rowspan="2" style="vertical-align: middle;">총점</th>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C' and estmProcdDetail.ESTM_PROCD_PSCD eq 'B002'  }">
							<%-- <c:if test="${not empty data.ESTM_RNK }"> --%>
								<th rowspan="2" style="vertical-align: middle;">
									<label class="component-checkbox">
										<input type="checkbox" id="slctCheckAll">
										<i></i>
									</label>
									<span class="txt-checkbox" style="vertical-align: top;">선정여부</span>
								</th>
							<%-- </c:if> --%>
						</c:if>
					</tr>
					<tr>
						<c:forEach var="data" items="${estmChrgrList}" varStatus="status">
							<th>${data.ESTM_CMTM_NM }</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${estmObjList}" varStatus="status">
					<tr>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C'}">
							<td class="txt-center">${data.ESTM_RNK }</td>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD ne 'C' }">
							<td class="txt-center">${data.RNUM }</td>
						</c:if>
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
							<%-- <td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', '${estmMngMstDetail.ESTM_OBJ_SECD}', '${data.ESTM_CMTM_NO}');">
								${data.ITEM_NM }
							</td> --%>
						</c:if>
						<c:if test="${estmMngMstDetail.ESTM_OBJ_SECD eq 'C' or estmMngMstDetail.ESTM_OBJ_SECD eq 'D' or estmMngMstDetail.ESTM_OBJ_SECD eq 'E'}">
							<td class="txt-center" style="cursor: pointer;" onclick="objDetailPopup('popupFrm', '${estmMngMstDetail.ESTM_OBJ_SECD}', '${data.ESTM_OBJ_PE_NO}');">
								${data.ESTM_OBJ_PE_NM }
							</td>
						</c:if>
						<td class="txt-center">
							<a href="javascript:" class="component-button-s type-a" onclick="pageObj.estmObjFileView('${data.ESTM_OBJ_SEQ }', '${data.RSDN_NO }', '${data.BIZRNO }', '${data.FILE_GRP_NO }')">보기</a>
						</td>
						<c:forEach var="cmtm" items="${estmChrgrList}" varStatus="status">
							<c:forEach var="cmtmVal" items="${estmChrgrValueList}" varStatus="status">
								<c:if test="${data.ESTM_OBJ_SEQ eq cmtmVal.ESTM_OBJ_SEQ }"><!-- 업체 -->
									<c:if test="${cmtm.ESTM_CMTM_NO eq cmtmVal.ESTM_CMTM_NO }"><!-- 평가위원 -->
										<td class="txt-center" onclick="detailInqirePopup('${data.ESTM_OBJ_SEQ}','${cmtm.ESTM_CMTM_NO}','B');" style="cursor: pointer;">${cmtmVal.CMTM_TOT_VAL }</td>
									</c:if>
								</c:if>
							</c:forEach>
						</c:forEach>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD ne 'C'}">
							<td class="txt-center">${data.ESTM_TOT_SCR }</td>
						</c:if>
						<c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C' and estmProcdDetail.ESTM_PROCD_PSCD eq 'B002'  }">
							<c:if test="${not empty data.ESTM_RNK }">
								<td class="txt-center">
									<input type="hidden" name="P_ESTM_SLCT_YN" value="${comFn:nvl(data.SLCT_YN, 'N') }">
									<label class="component-checkbox">
									<input type="checkbox" name="slctCheck"
										<c:if test="${data.SLCT_YN eq 'Y' }">checked</c:if>
									>
									<i></i>
									</label>
									<input type="hidden" name="P_ESTM_OBJ_SEQ" value="${data.ESTM_OBJ_SEQ }">
								</td>
							</c:if>
						</c:if>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- //정량평가인경우 -->
		
	</form>
	
	<!-- pageing -->
	<div class="component-pageing">
		<comTag:pagingIpro totalCount="${comFn:nvl(estmObjListTotCnt,0)}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 100)}" clickPage="pageObj.clickPage"/>
	</div>
	<!--//pageing -->
	
	<!-- bottom button -->
	<div class="bottom-buttons">
		<!-- 
		<a href="javascript:" class="btn-bottom type-b" id="estmEndBtn">평가종료</a>
		 -->
		 <!-- 평가절차구분 : 적격 + 절차진행상태 : 평가완료인경우 -->
		 <c:if test="${estmProcdDetail.ESTM_PROCD_SECD eq 'C' and estmProcdDetail.ESTM_PROCD_PSCD eq 'B002' }">
			<a href="javascript:" class="btn-bottom type-b" id="estmSlctBtn">평가대상선정</a>
		</c:if>
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
	<input type="hidden" name="P_FILE_GRP_NO">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="${estmProcdDetail.ESTM_PROCD_SEQ }">
	<input type="hidden" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_OBJ_SEQ"/>
	<input type="hidden" name="P_ESTM_CMTM_NO"/>
	<input type="hidden" name="P_ESTR_SECD"/>
	<input type="hidden" name="P_ESTM_OBJ_PE_NO"/>
	<input type="hidden" name="P_ESTM_PROCD_PSCD" value="${estmProcdDetail.ESTM_PROCD_PSCD}"/>
	<input type="hidden" name="reloadURL"/>
	<input type="hidden" name="P_BSNM_REGIST_NO">
	<input type="hidden" name="P_GOODSNO">
	<input type="hidden" name="P_REQST_NO">
	<input type="hidden" name="P_ESTM_INFO_CNTC_NO" value="${estmMngMstDetail.ESTM_INFO_CNTC_NO }">
</form>