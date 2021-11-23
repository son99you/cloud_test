<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 전자계약 > 반려 팝업
 *
 * <pre>
 * ebid
 *    |_popup
 *        |_bfanRtnRsnPopup.jsp
 * 
 * </pre>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/contReturn.js"></script>

<div class="pop_sp_sec">
	<h1 class="sp_tit">
		<c:if test="${param.P_CONT_PSCD  eq 'C011'}">검토 반려</c:if>
		<c:if test="${param.P_CONT_PSCD  eq 'C010'}">검토 승인 의견</c:if>
		<c:if test="${param.P_CONT_PSCD  eq 'C014'}">계약 요청 반려</c:if>
		<c:if test="${param.P_CONT_PSCD  eq 'C032'}">작성 완료 반려</c:if>
	</h1>
   	<form id="registFrm"  method="POST" >
   		<input type="hidden" id="P_CONT_NO" name="P_CONT_NO" value="${param.P_CONT_NO }">
   		<input type="hidden" id="P_CHNG_NGR" name="P_CHNG_NGR" value="${param.P_CHNG_NGR }">
   		<input type="hidden" id="P_CONT_PSCD" name="P_CONT_PSCD" value="${param.P_CONT_PSCD }">
    	<div class="sp_cont">
		    <!-- Data List -->
			<table class="form_tb">
			    <caption>반려 사유</caption>
                <colgroup>
                    <col width="15%">
                    <col width="*">
                </colgroup>
                <tr>
                	<th>
						<c:if test="${param.P_CONT_PSCD  eq 'C011' || param.P_CONT_PSCD  eq 'C014' || param.P_CONT_PSCD  eq 'C032'}">반려사유</c:if>
						<c:if test="${param.P_CONT_PSCD  eq 'C010'}">승인 의견</c:if>
					</th>
                	<td><textarea id="P_RTRN_RSN" name="P_RTRN_RSN" rows="10"  style="width: 98%;"></textarea></td>
                </tr>
			</table>				
		</div>
   	</form>		
	<div class="btm_btns">
	    <button type="button" class="btn ty02" id="registBtn">저장</button>
	    <button type="button" class="btn ty04" id="closeBtn">닫기</button>
	</div>
</div>