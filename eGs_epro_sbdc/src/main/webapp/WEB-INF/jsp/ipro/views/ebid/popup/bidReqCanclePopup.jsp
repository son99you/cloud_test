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

<script type="text/javascript" src="${jsPath}/ipro/views/ebid/popup/bidReqCanclePopup.js"></script>

<div class="pop_sp_sec">
	<h3 class="sp_tit"> 
		<c:if test="${param.P_BID_PSCD  eq 'A023'}">입찰취소</c:if>
		<c:if test="${param.P_BID_PSCD  eq 'A024'}">낙찰취소</c:if>
	</h3>
   	<form id="registFrm"  method="POST" >
   		<input type="hidden" id="P_ANNC_NO" name="P_ANNC_NO" value="${param.P_ANNC_NO}">
   		<input type="hidden" id="P_ANNC_NGR" name="P_ANNC_NGR" value="${param.P_ANNC_NGR}">
   		<input type="hidden" id="P_ROUND_NO" name="P_ROUND_NO" value="${param.P_ROUND_NO}">
   		<input type="hidden" id="P_BID_PSCD" name="P_BID_PSCD" value="${param.P_BID_PSCD}">
    	<div class="sp_cont">
		    <!-- Data List -->
			<table class="form_tb">
			    <caption>취소사유</caption>
                <colgroup>
                    <col width="15%">
                    <col width="*">
                </colgroup>
                <tr>
                	<th>취소사유</th> 
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