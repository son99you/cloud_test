<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가위원첨부 상세 팝업
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_ estmCmtmFileView.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estmCmtmFileView.js"></script>

<div class="layout-pop">
	<form id="detailFrm" class="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		
		<div class="pop_header">
			<div class="title">평가위원첨부 상세</div>
		</div> <!--// pop_header E -->
		
		<div class="table-detail">
			<table class="component-detail-table"> 
				<colgroup>
					<col width="30%">
					<col width="70%">
				</colgroup>
				<thead>
					<tr>
						<th>파일명</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty cmtmBnkbCpyFile }">
						<tr>
                			<th>통장사본</th>
                			<td></td>
                		</tr>
					</c:if>
					
					<c:if test="${not empty cmtmBnkbCpyFile }">
						<c:forEach items="${cmtmBnkbCpyFile}" var="data" varStatus="status">
                  			<tr>
                  				<th>통장사본</th>
                  				<td><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
                  			</tr>
	                   	</c:forEach>
					</c:if>
					
					<c:if test="${empty cmtmHldfPfdcFile }">
						<tr>
                			<th>재직증명서</th>
                			<td></td>
                		</tr>
					</c:if>
					
					<c:if test="${not empty cmtmHldfPfdcFile }">
						<c:forEach items="${cmtmHldfPfdcFile}" var="data" varStatus="status">
                  			<tr>
                  				<th>재직증명서</th>
                  				<td><a href="javascript:download('${data.FILE_GRP_NO}', '${data.FILE_SN}');">${data.SYS_FILE_NM }</a></td>
                  			</tr>
	                   	</c:forEach>
					</c:if>
				</tbody>
			</table> 
		</div>
		
		<div class="bottom-buttons">
			<a href="javascript:" class="btn-bottom type-a" onclick="window.close();">닫기</a>
		</div>
	</form>
</div> 

<!-- DOWNLOAD FORM -->
<form id="downloadFrm" method="POST">
	<input type="hidden" name="P_FILE_SN">
	<input type="hidden" name="P_FILE_GRP_NO">
</form>