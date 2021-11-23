<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 일반공지 > 일반공지
 *
 * <pre>
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <script type="text/javascript" src="${jsPath}/ipro/views/main/popup/helpPopup.js"></script> --%>
<!-- <script type="text/javascript" src="/raonkupload/js/raonkupload.js"></script>
<script type="text/javascript" src="/raonkeditor/js/raonkeditor.js"></script> -->

<div class="layout-pop">
	
	<form id="detailFrm" class="search_form" method="POST">
		<div class="pop_header">
			<div class="title">
				시스템 담당자정보
			</div>
		</div>
	
		<div>
			<table class="component-detail-table type-line-none">
                <colgroup>
                   	<col width="25%" align="left">
					<col width="35%" align="left">
					<col width="15%" align="left">
                </colgroup>
                <tbody>
					<tr>
						<th class="txt-center">업부구분</th>
						<th class="txt-center">담당자명</th>
						<th class="txt-center">연락처</th>
					</tr>
					<tr>
						<td>업무문의</td>
						<td>김정태 대리</td>
						<td>02-000-0000</td>
					</tr>
					<tr>
						<td>기술문의</td>
						<td>은우소프트</td>
						<td>02-6925-0721</td>
					</tr>
					<tr>
						<td>화상시스템문의</td>
						<td>쿼랜시스</td>
						<td>02-6925-0721</td>
					</tr>
                </tbody>
            </table>
		</div>
		
		
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="javascript:window.close();">닫기</a>
		</div> <!--// btn_wrap E -->
			
	</form> 
</div>


<%-- 파일 VIEW 폼 --%>
<form id="listFrm" method="POST">
	<input type="hidden" id="P_BBS_SECD"  name="P_BBS_SECD" value="${notiDetail.BBS_SECD }">
	<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
	<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
</form>