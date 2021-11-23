<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 결재 > 의견조회 및 업데이트(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_bsnsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/info/popup/infoApprRsnForm.js"></script> 

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">의견작성</h3>

	<form id="rsnFrm" class="search_form" method="POST" action="">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_APRDC_NO" name="P_APRDC_NO" value="${tApprTgl[0].APRDC_NO}">
		<input type="hidden" id="P_APRP_SN" name="P_APRP_SN" value="${tApprTgl[0].APRP_SN}">
		
	    <!-- Data List -->
		<div class="tableLayer list">
			<table class="tableList" summary="결재 의견 입니다.">
	            <caption>결재 의견</caption>
	            <colgroup>
	                <col width="80px">
	                <col width="*">
	            </colgroup>			
				<tbody>
					<tr>
	                    <th>의견</th>
						<td>
							<label for="P_RSN" class="blind">의견</label>
							<textarea  id="P_RSN" name="P_RSN" rows="10" cols="80">${tApprTgl[0].RSN}</textarea>
						</td>
					</tr>
				</tbody> 
			</table>
			
	        <div class="T_btnLayer fr">
	        	<button type="button" class="blueBtn L" id="saveBtn">저장</button>
	            <button type="button" class="btn btn_s btn_gray2" id="closeBtn">닫기</button>
	        </div>
		</div>
	</form>         
</div>