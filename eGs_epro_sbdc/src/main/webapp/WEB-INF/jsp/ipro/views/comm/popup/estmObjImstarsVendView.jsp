<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 업체정보관리 - 업체정보 아임스타즈 연계 상세 팝업 : 기업정보
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ estmObjImstarsVendView.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmObjImstarsVendView.js"></script>

<div class="layout-pop">
	<form id="detailFrm" class="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_BSNM_REGIST_NO" name="P_BSNM_REGIST_NO" value="${comFn:nvl(estmObjImstarsMainDetail.BSNM_REGIST_NO, param.P_BSNM_REGIST_NO)}">
		
		<div class="pop_header">
			<div class="title">업체정보관리</div>
		</div> <!--// pop_header E -->
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">기업정보</div>
				</div>
			</div>
			<!-- //Top -->
	
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>업체명</th>
						<td>${estmObjImstarsMainDetail.CMPNY_NM }</td>
						<th>사업자번호</th>
						<td>${comFn:formatBizNumber(estmObjImstarsMainDetail.BSNM_REGIST_NO )}</td>
					</tr>
					<tr>
						<th>대표(이사)</th>
						<td>${estmObjImstarsMainDetail.RPRSNTV_NM }</td>
						<th>법인번호</th>
						<td>${estmObjImstarsMainDetail.CPR_REGIST_NO }</td>
					</tr>
					<tr>
						<th>회사설립일</th>
						<td>${comFn:formatDate(estmObjImstarsMainDetail.FOND_ON, 'yyyyMMdd','yyyy-MM-dd')}</td>
						<th>자본금/총자산</th>
						<td>${comFn:formatMoney(estmObjImstarsMainDetail.CAPL )}&nbsp;원&nbsp;/&nbsp;${comFn:formatMoney(estmObjImstarsMainDetail.TOT_ASSETS_AM)}&nbsp;원</td>
					</tr>
					<tr>
						<th>종업원수</th>
						<td>${comFn:formatMoney(estmObjImstarsMainDetail.EMPLY_CO )}&nbsp;명</td>
						<th>홈페이지</th>
						<td>${estmObjImstarsMainDetail.HMPG_ADRES }</td>
					</tr>
					<tr>
						<th>업태</th>
						<td>${estmObjImstarsMainDetail.BIZCND }</td>
						<th>업종</th>
						<td>${estmObjImstarsMainDetail.INDUTY }</td>
					</tr>
					<tr>
						<th>생산지</th>
						<td>${estmObjImstarsMainDetail.PRDLC_SE_NM }</td>
						<th>생산방식</th>
						<td>${estmObjImstarsMainDetail.PRDCTN_MTHD_SE_NM }</td>
					</tr>
					<tr>
						<th rowspan="2" style="vertical-align: middle;">소재지</th>
						<td colspan="3">본사&nbsp;:&nbsp;&nbsp;(${estmObjImstarsMainDetail.CMPNY_ZIP }) ${estmObjImstarsMainDetail.CMPNY_ADRES}&nbsp;${estmObjImstarsMainDetail.CMPNY_DETAIL_ADRES }</td>
					</tr>
					<tr>
						<td colspan="3">공장&nbsp;:&nbsp;&nbsp;(${estmObjImstarsMainDetail.FCTRY_ZIP }) ${estmObjImstarsMainDetail.FCTRY_ADRES}&nbsp;${estmObjImstarsMainDetail.FCTRY_DETAIL_ADRES }</td>
					</tr>					
					<%-- <tr>
						<th style="vertical-align: middle;">회사소개</th>
						<td colspan="3">
							<textarea taView style="display: none;">${estmObjImstarsMainDetail.CMPNY_INTRCN_ENG}</textarea>
							<c:if test="${not empty estmObjImstarsMainDetail.CMPNY_INTRCN_ENG && not empty estmObjImstarsMainDetail.CMPNY_INTRCN}">
								<br><br>
							</c:if>
							<textarea taView style="display: none;">${estmObjImstarsMainDetail.CMPNY_INTRCN}</textarea>
						</td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">회사매출액</div>
				</div>
			</div>
			<!-- //Top -->
			
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="20%">
					<col width="*">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align:center;">년도</th>
						<th class="txt-center">국내실적</td>
						<th class="txt-center">해외실적</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty estmObjImstarsCmpnySelng}">
						<c:forEach var="data" items="${estmObjImstarsCmpnySelng}" varStatus="status">
								<tr>
									<td class="txt-center">${data.SELNG_YY}</td>
									<td class="txt-center">${comFn:formatMoney(comFn:nvl(data.SELNG_AM,0))}&nbsp;원</td>
									<td class="txt-center">${comFn:formatMoney(comFn:nvl(data.SELNG_AM_DOLLAR,0))}&nbsp;달러</td>
								</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ empty estmObjImstarsCmpnySelng }">
                   		<tr>
                   			<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
                   		</tr>
                   	</c:if>
				</tbody>
			</table>
		</div>
		
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">대표자정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>성명</th>
						<td colspan="3">${estmObjImstarsMainDetail.RPRSNTV_NM }</td>
					</tr>
					<tr>
						<th>전화번호(유선)</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.RPRSNTV_TLPHON_NO)}</td>
						<th>팩스번호</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.CMPNY_FAX_NO)}</td>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.RPRSNTV_MOBLPHON_NO)}</td>
						<th>이메일</th>
						<td>${estmObjImstarsMainDetail.RPRSNTV_EMAIL }</td>
					</tr>
				</tbody>
			</table>
		</div>

		<%-- <div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">(실무)담당자정보</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>성명</th>
						<td >${estmObjImstarsMainDetail.ESTM_CMTM_OFPS }</td>
						<th>직위</th>
						<td >${estmObjImstarsMainDetail.ESTM_CMTM_OFPS }</td>
					</tr>
					<tr>
						<th>전화번호(유선)</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.ESTM_CMTM_OFPS)}</td>
						<th>팩스번호</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.CMPNY_FAX_NO)}</td>
					</tr>
					<tr>
						<th>핸드폰</th>
						<td>${comFn:formatPhoneNumber(estmObjImstarsMainDetail.ESTM_CMTM_OFPS)}</td>
						<th>이메일</th>
						<td>${estmObjImstarsMainDetail.ESTM_CMTM_OFPS }</td>
					</tr>
				</tbody>
			</table>
		</div> --%>
		
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">첨부파일</div>
				</div>
			</div>
			<!-- //Top -->
			
			<table class="component-detail-table ">
				<colgroup>
					<col width="8%">
					<col width="30%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>파일명</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty cmpnyFileList }">
						<c:forEach var="data" items="${cmpnyFileList}" varStatus="status">
							<tr>
								<td class="txt-center">${status.count }</td>
								<td>${data.FILE_NM}</td>
								<td><a href="javascript:imstarsDownload('${data.FILE_ID}', '${data.FILE_SN }');">${data.WON_FILE_NM }</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${ empty cmpnyFileList }">
                   		<tr>
                   			<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
                   		</tr>
                   	</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_ID">
	<input type="hidden" name="P_FILE_SN">
</form>