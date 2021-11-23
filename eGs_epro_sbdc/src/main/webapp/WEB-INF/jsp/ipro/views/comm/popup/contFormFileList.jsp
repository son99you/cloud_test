<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
  이전 자동첨부파일 조회
 *
 * <pre>
 * comm 
 *  |_popup
 *   |contFormFileList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/contFormFileList.js"></script>
<div class="pop_sp_sec"> 
	<h3 class="sp_tit">첨부파일</h3>
	<div class="sp_cont">
		<table class="list_tb" summary="첨부파일 입니다.">
			<colgroup>
				<col width="5%">
				<col width="30%">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th class="txtc">번호</th>
					<th class="txtc">파일명</th>
					<th class="txtc">파일첨부</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty fileList}">
					<tr>
						<td class="txtc" colspan="3">파일이 존재하지 않습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty fileList}">
					<c:forEach var="data" items="${fileList}" varStatus="status" >
						<tr>
							<td class="no">${status.count}</td>
							<td class="txtl">${data.FILE_DOC_NM}</td>
							<td class="txtl">${data.SYS_FILE_NM}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="btm_btns">
       		<button type="button" class="btn ty04" id="closeBtn" onclick="window.close();">닫기</button>
		</div>	
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->