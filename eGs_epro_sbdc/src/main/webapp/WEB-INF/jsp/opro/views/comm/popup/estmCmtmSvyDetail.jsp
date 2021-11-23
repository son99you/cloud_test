<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 평가완료현황 - 상세 : 설문조사 팝업
 *
 * <pre>
 * estm/popup 
 *    |_ estmCmtmSvyDetail.jsp
 * 
 * </pre>
 * @date : 2021. 06. 01
 * @version : 1.0
 * @author : 은우소프트 은잔디
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld"%>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld"%>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/estmCmtmSvyDetail.js"></script>
<script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>

<div class="layout-pop">

	<!-- 서식데이터세팅 -->
	<c:forEach var="data" items="${estmCmtmSvyFrmItemList}" varStatus="status">
		<input type="hidden" name="D_ESTM_ITEM_NO" value="${data.ESTM_ITEM_NO }"><!-- 항목번호 -->
		<input type="hidden" name="D_ESTM_ITEM_NM" value="${data.ESTM_ITEM_NM }"><!-- 항목명 -->
		<input type="hidden" name="D_ESTM_ITEM_DSMK" value="${data.ESTM_ITEM_DSMK }"><!-- 배점 -->
		<input type="hidden" name="D_ESTM_ITEM_DESC" value="${data.ESTM_ITEM_DESC }"><!-- 내용 -->
		<input type="hidden" name="D_ESTM_MTHD_SECD" value="${data.ESTM_MTHD_SECD }"><!-- 평가항목/평가기준 -->
		<input type="hidden" name="D_ESTM_SCR" value="${data.ESTM_SCR }"><!-- 평가위원입력점수 -->
	</c:forEach>

	<!-- //서식데이터세팅 -->
	
	<form id="registFrm" class="search_form" method="POST">
		<input type="hidden"  id="resultCode" value="${resultCode}">
		<input type="hidden" id="P_ESTM_NO" name="P_ESTM_NO" value="${estmMngMstDetail.ESTM_NO}"><!-- 평가번호 -->
		<input type="hidden" id="P_ESTM_CMTM_NO" name="P_ESTM_CMTM_NO" value="${loginResult.USR_ID}"><!-- 평가위원번호 -->
		<input type="hidden" id="reloadURL" name="reloadURL" value="${reloadURL }">
		
		<div class="pop_header">
			<div class="title">설문조사</div>
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
					<th>평가명</th>
					<td colspan="3">${estmMngMstDetail.ESTM_NM }</td>
				</tr>
			</tbody>
		</table>
		
		<div style="height: 10px;"></div>

		<div id="tableDiv">
			<table class="component-detail-table type-line-none">
				<colgroup>
					<col style="width: auto;">
				</colgroup>
				<tbody id="copyArea">
					<tr>
						<th>평가항목명</th>
					</tr>
					<tr></tr>
				</tbody>
			</table>
		</div>

		<div style="height: 10px;"></div>
		
		<c:if test="${estmMngMstDetail.ESTM_FRM_PROCD_SECD ne 'C'}">
			<table class="component-detail-table type-line-none" style="float: right; width:150px;">
				<colgroup>
					<col width="50%">
					<col width="50%">
				</colgroup>
				<tbody>
				<tr>
					<th>합계</th>
					<td><input type="text"  readonly="readonly" id="P_TOT_SCR" name="P_TOT_SCR" class="component-input type-full"  placeholder=""></td>
				</tr>
				</tbody>
			</table>
		</c:if>
		
		<div style="height: 10px;"></div>
		
		<div class="bottom-buttons">
			<button type="button" id="saveBtn" class="btn-bottom type-b">저장</button>
			<button type="button" id="colseBtn" class="btn-bottom type-a">닫기</button>
			
		</div>
		
		<div style="padding-top: 30px;"></div>
	</form>
</div> 