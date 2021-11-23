<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 회의참가요청(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ mtngPrtcRqstList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/mtngPrtcRqstList.js"></script>

<div class="layout-pop">
	<form id="searchFrm" class="search_form" method="POST">
		<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
		<input type="hidden" id="P_GBN" name="P_GBN" value="${comFn:nvl(param.P_GBN, GBN)}">
		<input type="hidden" id="P_FRM_GBN" name="P_FRM_GBN" value="${param.P_FRM_GBN}">
		
		<div class="pop_header">
			<div class="title">회의참가요청목록</div>
		</div> <!--// pop_header E -->
		
	  <div class="area-list">
	  	  <!-- Option -->
	      <div class="table-option">
	          <div class="option-left">
	              <div class="table-num type-fleft">평가위원목록</div>
	          </div>
	      </div>
			<table class="component-detail-table">
				<colgroup>
					<col width="10%">
					<col width="30%">
					<col width="auto">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th class="txt-center">순번</th>
						<th class="txt-center">평가위원명</th>
						<th class="txt-center">이메일주소</th>
						<th class="txt-center">진행상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${vidoCmtmList}" varStatus="status">
						<tr>
							<td class="txt-center">
								${data.RNM }
							</td>
							<td class="txt-center">
								${data.ESTM_CMTM_NM}
							</td>
							<td>
								<%-- ${data.EMAL} --%>
								<input type="text" class="w100" name="P_EMAL" value="${data.EMAL}">
							</td>
							<td class="txt-center">
								<button type="button" name="vidoRqst" class="component-button-s type-a">참가요청</button>
								<input type="hidden" name="P_MAIL_USER_ID" value="${data.MAIL_USER_ID }">
								<input type="hidden" name="P_ROLE" value="interviewer">
								<%-- <input type="hidden" name="P_EMAL" value="${data.EMAL}"> --%>
							</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="area-list">
		  	  <!-- Option -->
		      <div class="table-option">
		          <div class="option-left">
		              <div class="table-num type-fleft">평가대상목록</div>
		          </div>
		      </div>
				<table class="component-detail-table">
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="auto">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="txt-center">순번</th>
							<th class="txt-center">평가대상명</th>
							<th class="txt-center">이메일주소</th>
							<th class="txt-center">진행상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="data" items="${vidoObjList}" varStatus="status">
							<tr>
								<td class="txt-center">
									${data.RNM }
								</td>
								<td class="txt-center">
									${data.OBJ_NM}
								</td>
								<td>
									<%-- ${data.EMAL} --%>
									<input type="text" class="w100" name="P_EMAL" value="${data.EMAL}">
								</td>
								<td class="txt-center">
									<button type="button" name="vidoRqst" class="component-button-s type-a">참가요청</button>
									<input type="hidden" name="P_MAIL_USER_ID" value="${data.MAIL_USER_ID }">
									<input type="hidden" name="P_ROLE" value="interviewee">
									<%-- <input type="hidden" name="P_EMAL" value="${data.EMAL}"> --%>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		<%-- <c:if test="${param.P_ESTM_OBJ_SECD eq 'A'}">
			<div class="area-list">
		  	  <!-- Option -->
		      <div class="table-option">
		          <div class="option-left">
		              <div class="table-num type-fleft">평가대상목록-업체</div>
		          </div>
		      </div>
				<table class="component-detail-table">
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="auto">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="txt-center">순번</th>
							<th class="txt-center">업체명</th>
							<th class="txt-center">이메일주소</th>
							<th class="txt-center">진행상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="data" items="${vidoObjList}" varStatus="status">
							<tr>
								<td class="txt-center">
									${data.RNM }
								</td>
								<td class="txt-center">
									${data.VEND_NM}
								</td>
								<td>
									${data.EMAL}
								</td>
								<td class="txt-center"><button name="vidoRqst" class="component-button-s type-a">참가요청</button></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${param.P_ESTM_OBJ_SECD eq 'B'}">
			<div class="area-list">
		  	  <!-- Option -->
		      <div class="table-option">
		          <div class="option-left">
		              <div class="table-num type-fleft">평가대상목록-상품</div>
		          </div>
		      </div>
				<table class="component-detail-table">
					<colgroup>
						<col width="10%">
						<col width="20%">
						<col width="20%">
						<col width="auto">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="txt-center">순번</th>
							<th class="txt-center">상품명</th>
							<th class="txt-center">업체명</th>
							<th class="txt-center">이메일주소</th>
							<th class="txt-center">진행상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="data" items="${vidoObjList}" varStatus="status">
							<tr>
								<td class="txt-center">
									${data.RNM }
								</td>
								<td class="txt-center">
									${data.ITEM_NM}
								</td>
								<td class="txt-center">
									${data.VEND_NM}
								</td>
								<td>
									${data.EMAL}
								</td>
								<td class="txt-center"><button name="vidoRqst" class="component-button-s type-a">참가요청</button></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>

		<c:if test="${param.P_ESTM_OBJ_SECD eq 'C'}">
			<div class="area-list">
		  	  <!-- Option -->
		      <div class="table-option">
		          <div class="option-left">
		              <div class="table-num type-fleft">평가대상목록-사람(크리에이터)</div>
		          </div>
		      </div>
				<table class="component-detail-table">
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="auto">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="txt-center">순번</th>
							<th class="txt-center">신청자명</th>
							<th class="txt-center">이메일주소</th>
							<th class="txt-center">진행상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="data" items="${vidoObjList}" varStatus="status">
							<tr>
								<td class="txt-center">
									${data.RNM }
								</td>
								<td class="txt-center">
									${data.OBJ_NM}
								</td>
								<td>
									${data.EMAL}
								</td>
								<td class="txt-center"><button name="vidoRqst" class="component-button-s type-a">참가요청</button></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if> --%>
		
		
		<div class="bottom-buttons">
			<button id="colseBtn" class="btn-bottom type-a">닫기</button>
		</div>
	</form>
</div> 

<form id="meetMailFrm" name="meetFrm" method="POST">
	<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
	<input type="hidden" name="P_ESTM_NO" value="${param.P_ESTM_NO}">
	<input type="hidden" name="P_ESTM_PROCD_SEQ" value="1">
	<input type="hidden" name="P_VIDO_MTNG_SEQ" value="${param.P_VIDO_MTNG_SEQ}">
	<input type="hidden" name="P_MAIL_USER_ID">
	<input type="hidden" name="P_ROLE">
	<input type="hidden" name="P_EMAL">
	<input type="hidden" name="P_VIDO_ST_DT" value="${param.P_VIDO_ST_DT}">
	<input type="hidden" name="P_VIDO_END_DT" value="${param.P_VIDO_END_DT}">
	<input type="hidden" name="P_VIDO_MTNG_NM" value="${param.P_VIDO_MTNG_NM}">
</form>