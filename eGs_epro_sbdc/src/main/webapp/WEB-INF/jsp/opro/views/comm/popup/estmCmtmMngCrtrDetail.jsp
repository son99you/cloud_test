<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가대상 신청자 팝업 상세
 *
 * <pre>
 * estm/popup 
 *    |_ estmCmtmMgnCrtrDetail.jsp
 * 
 * </pre>
 * @date : 2018. 08. 22
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmMngCrtrDetail.js"></script>

<div class="layout-pop">

	<!-- Detail -->
<!-- <div class="area-detail"> -->
	<form id="detailFrm" name="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_CRTR_NO" name="P_CRTR_NO" value="${estmCmtmCrtrMstDetail.CRTR_NO }">
		<div class="table-detail">
			<!-- Top -->
			<div class="top-detail">
				<div class="type-fleft">
					<div class="contents-label">크리에이터정보</div>
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
						<th>크리에이터명</th>
						<td>${estmCmtmCrtrMstDetail.CRTR_NM }</td>
						<th>채널명</th>
						<td>${estmCmtmCrtrMstDetail.CHNL_NM }</td>
					</tr>
					<tr>
						<th>구독자수</th>
						<td>${comFn:formatMoney(estmCmtmCrtrMstDetail.SSCRT_CNT)}&nbsp;명</td>
						<th>채널주소</th>
						<td>${estmCmtmCrtrMstDetail.CHNL_ADDR }</td>
					</tr>
					<tr>
						<th>휴대폰전화번호</th>
						<td>${comFn:formatPhoneNumber(estmCmtmCrtrMstDetail.CP_NO)}</td>
						<th>이메일</th>
						<td>${estmCmtmCrtrMstDetail.EMAL }</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- bottom button -->
		<div class="bottom-buttons">
			<!-- <a href="javascript:" class="btn-bottom type-b" id="updtBtn">수정</a> -->
			<a href="javascript:" class="btn-bottom type-a" id="closeBtn">닫기</a>
		</div>
		<!-- //bottom button -->
	
	</form>
	
</div>	
<!-- //Detail -->


<!-- LIST FORM -->
<form id="listFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
</form>

<form id="downFrm" method="POST">
	<input type="hidden" name="P_FILE_GRP_NO" value="">
	<input type="hidden" name="P_FILE_SN" value="">
</form>
