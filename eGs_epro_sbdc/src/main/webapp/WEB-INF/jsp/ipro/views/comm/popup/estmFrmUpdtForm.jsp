<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가절차 - 평가표보기 팝업
 *
 * <pre>
 * popup
 *    |_ estmFrmDetail.jsp
 * 
 * </pre>
 * @date : 2017. 06. 13
 * @version : 1.0
 * @author : 은우소프트 홍찬일
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %> 
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %> 

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmFrmUpdtForm.js"></script>

<!-- 서식데이터세팅 -->
<c:forEach var="data" items="${estmFrmItemList}" varStatus="status">
	<input type="hidden" name="D_ESTM_ITEM_NO" value="${data.ESTM_ITEM_NO }"><!-- 항목번호 -->
	<input type="hidden" name="D_ESTM_ITEM_NM" value="${data.ESTM_ITEM_NM }"><!-- 항목명 -->
	<input type="hidden" name="D_ESTM_ITEM_DSMK" value="${data.ESTM_ITEM_DSMK }"><!-- 배점 -->
	<input type="hidden" name="D_ESTM_ITEM_DESC" value="${data.ESTM_ITEM_DESC }"><!-- 내용 -->
	<input type="hidden" name="D_ESTM_MTHD_SECD" value="${data.ESTM_MTHD_SECD }"><!-- 평가항목/평가기준 -->
</c:forEach>

<!-- //서식데이터세팅 -->


<!-- Detail -->
<div class="layout-pop">
	<div class="pop_header">
		<div class="title">평가위원평가표</div>
	</div> <!--// pop_header E -->


	<table class="component-detail-table type-line-none">
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>평가절차구분</th>
				<td>${estmFrmDetail.ESTM_PROCD_SECD_NM }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>평가서식명</th>
				<td colspan="3">${estmSeProcdDetail.ESTM_FRM_NM }</td>
			</tr>
		</tbody>
	</table>
<form id="modiFrm" method="POST">					
	<input type="hidden" name="P_ESTM_FRM_NO" id="P_ESTM_FRM_NO" value="${estmFrmDetail.ESTM_FRM_NO}">
	<input type="hidden" name="P_ESTM_NO" id="P_ESTM_NO" value="${estmFrmDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" id="P_ESTM_PROCD_SEQ" value="${estmFrmDetail.ESTM_PROCD_SEQ }">
	<input type="hidden" name="P_ESTM_SECD" id="P_ESTM_SECD" value="${estmSeProcdDetail.ESTM_SECD }">

	<div class="table-detail">
		<!-- Top -->
		<div class="top-detail">
			<div class="type-fleft">
				<div class="contents-label">평가항목</div>
			</div>
		</div>
		<!-- //Top -->
		<div id="tableDiv">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: auto;">
				</colgroup>
				<tbody id="copyArea">
				<tr>
					<th>평가항목명</th>
				</tr>
				<tr>
					<td>
						<input type="hidden" class="component-input" name="P_ESTM_DTL_ITEM_NO">
						<input type="text" class="component-input type-full" placeholder="평가항목명" name="P_ESTM_DTL_ITEM_NM">
						<textarea class="component-textarea" placeholder="평가항목내용" name="P_ESTM_ITEM_DTL_CNTN"></textarea>
						<input type="text" class="component-input" name="P_ESTM_DTL_ITEM_SCR" placeholder="배점" numeric>
						<select class="component-select type-division" name="P_ESTM_DTL_ITEM_SCR_SECD"><option value="A">합산</option><option value="B">최고점</option></select>
						<button type="button" class="component-button-s type-line" name="regBtn">+</button>
		  				<button type="button" class="component-button-s type-line" name="regChildBtn">>></button>
						<!-- <a href="javascript:regBtnClick('01');" class="component-button-s type-line">+</a>
						<a href="javascript:regChildBtnClick('01');" class="component-button-s type-line"></a> -->
					
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</form>

	<!-- bottom button -->
	<div class="bottom-buttons">
		<a href="javascript:" id="saveBtn" class="btn-bottom type-b">저장</a>
		<a href="javascript:" id="closeBtn" class="btn-bottom type-a">닫기</a>
	</div>
	<!-- //bottom button -->
				
</div>

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_NO" id="P_ESTM_NO" value="${estmFrmDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" id="P_ESTM_PROCD_SEQ" value="${estmFrmDetail.ESTM_PROCD_SEQ }">
</form>