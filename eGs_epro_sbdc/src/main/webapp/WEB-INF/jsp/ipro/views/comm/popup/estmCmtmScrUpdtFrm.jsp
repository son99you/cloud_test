<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가관리 > 평가위원위원평가 팝업 상세
 *
 * <pre>
 * estm/popup 
 *    |_ estmCmtmScrUpdtFrm.jsp
 * 
 * </pre>
 * @date : 2021.04.26
 * @version : 1.0
 * @author : 은우소프트 이재인
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmScrUpdtFrm.js"></script>


<div class="layout-pop">

	<form id="updtFrm" method="POST">
		<input type="hidden"  id="resultCode" value="${resultCode}">
		<input type="hidden" id="P_ESTM_NO" name="P_ESTM_NO" value="${estmCmtmScrDetail.ESTM_NO}">
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${estmCmtmScrDetail.ESTM_CMTM_NO}">
		
		<div class="pop_header">
			<div class="title">평가위원평가등록</div>
		</div> <!--// pop_header E -->
	
		<table class="component-detail-table type-line-none">
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
			<tr>
				<th>평가점수</th>
				<td><input type="text"  class="component-input w85" value="${estmCmtmScrDetail.ESTM_TOT_SCR }" name="P_ESTM_TOT_SCR"></td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>평가위원의견</th>
				<td colspan="3">
						<textarea class="component-textarea" style="height: 70px;" placeholder="" name="P_ESTM_OPNN">${estmCmtmScrDetail.ESTM_OPNN}</textarea>
					</td>
			</tr>
			</tbody>
		</table>

		<div class="bottom-buttons">
			<button type="button" id="saveBtn" class="btn-bottom type-b">저장</button>
			<button type="button" id="colseBtn" class="btn-bottom type-a">닫기</button>
		</div>
	</form>
</div> 
