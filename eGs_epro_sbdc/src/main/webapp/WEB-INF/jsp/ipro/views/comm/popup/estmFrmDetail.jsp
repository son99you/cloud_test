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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmFrmDetail.js"></script>

<!-- 서식데이터세팅 -->
<c:forEach var="data" items="${estmFrmItemList}" varStatus="status">
	<input type="hidden" name="D_ESTM_ITEM_NO" value="${data.ESTM_ITEM_NO }"><!-- 항목번호 -->
	<input type="hidden" name="D_ESTM_ITEM_NM" value="${data.ESTM_ITEM_NM }"><!-- 항목명 -->
	<input type="hidden" name="D_ESTM_ITEM_DSMK" value="${data.ESTM_ITEM_DSMK }"><!-- 배점 -->
	<input type="hidden" name="D_ESTM_ITEM_DESC" value="${data.ESTM_ITEM_DESC }"><!-- 내용 -->
	<input type="hidden" name="D_ESTM_MTHD_SECD" value="${data.ESTM_MTHD_SECD }"><!-- 평가항목/평가기준 -->
</c:forEach>

<!-- //서식데이터세팅 -->

<input type="hidden" name="P_ESTM_FRM_PROCD_SECD" value="${estmFrmDetail.ESTM_FRM_PROCD_SECD }"/>

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
					
	<div id="tableDiv" style="margin-top: 20px;">
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


	<!-- bottom button -->
	<div class="bottom-buttons">
		<c:if test="${param.P_GBN ne 'cmpl' }">
			<a href="javascript:" id="updtBtn" class="btn-bottom type-b">수정</a>
		</c:if>
		<a href="javascript:" id="closeBtn" class="btn-bottom type-a">닫기</a>
	</div>
	<!-- //bottom button -->
				
</div>

<form id="updtFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}" >
	<input type="hidden" name="P_ESTM_NO" id="P_ESTM_NO" value="${estmFrmDetail.ESTM_NO }">
	<input type="hidden" name="P_ESTM_SECD" id="P_ESTM_SECD" value="${estmSeProcdDetail.ESTM_SECD }">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" id="P_ESTM_PROCD_SEQ" value="${estmFrmDetail.ESTM_PROCD_SEQ }">
</form>