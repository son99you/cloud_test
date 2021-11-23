<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 반려팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_return.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/return.js"></script> 

<div id="windowPopup" class="w_500">
	<h3 class="windowTitle">반려사유</h3>
	<div class="formLayer">
		
           
		<!-- Data Total Count -->
		<br />
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>내부 담당자 목록</caption>
				<tbody>
					<tr>
						<td><textarea id="MEMO" name="MEMO" rows="10" class="w670" style="width:505px; imargin-left:5px;padding-top:5px;"></textarea></td>
					</tr>
				</tbody> 
			</table>
			
			<form id="registFrm" method="POST" action="">
		   		<input type="hidden" name="resourceName" value="${param.resourceName}">
		   		<input type="hidden" name="resourceDesc" value="${param.resourceDesc}">
		   		<input type="hidden" name="gbn" value="${gbn}">
		   	</form>
			
			<!-- 페이징 끝 -->
		</div>
		<br>
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="saveBtn" >저장</button>
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	</div>
</div>
