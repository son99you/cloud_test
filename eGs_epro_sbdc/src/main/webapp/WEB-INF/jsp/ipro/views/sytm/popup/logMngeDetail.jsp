<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
 * 로그관리 상세(팝업)
 *
 * <pre>
 * vend 
 *    |_ logMngeDetail.jsp
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

<div class="pop_sp_sec">
	<h3 class="sp_tit">로그관리</h3>

	<div class="sp_cont">
		<input type="hidden" id="P_LOG_SN" value="${logMngeDetail.LOG_SN }">
		
		<table class="form_tb">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="16%">
				<col width="34%">
			</colgroup>
			<tr>
				<th>업체명</th>
				<td>${logMngeDetail.REGR_NM }</td>
				<th>사업자번호</th>
				<td>${comFn:formatBizNumber(logMngeDetail.CONN_ID) }</td>
			</tr>
		</table>
			
		<table class="form_tb">
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			<tr>
				<th>오류구분</th>
				<td>${logMngeDetail.LOG_ERR_SECD_NM }</td>
			</tr>
			<tr>
				<th>오류내용</th>
				<td><textarea style="height: 100px; width: 100%;" readonly="readonly">${logMngeDetail.ERR_CNTN }</textarea></td>
			</tr>
		</table>
		
		<div class="btm_btns">
			<button type="button" class="btn ty04" id="closeBtn" onclick="javascript:window.close();">닫기</button>
		</div> <!--// btn_wrap E -->
	</div>
</div>