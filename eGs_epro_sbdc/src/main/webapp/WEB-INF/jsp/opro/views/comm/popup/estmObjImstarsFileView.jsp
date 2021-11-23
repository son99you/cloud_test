<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
 *
 * <pre>
 * comm 
 *   |_ popup
 *     |_ estmObjImstarsFileView.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/opro/views/comm/popup/estmObjImstarsFileView.js"></script>

<div class="layout-pop">
	<form id="detailFrm" class="detailFrm" method="POST">
		<input type="hidden" name="resourceDesc" value="${ param.resourceDesc}">
		<input type="hidden" name="resourceName" value="${comFn:nvl(param.resourceName, resourceName)}">
		<input type="hidden" id="P_BSNM_REGIST_NO" name="P_BSNM_REGIST_NO" value="${comFn:nvl(estmObjImstarsMainDetail.BSNM_REGIST_NO, param.P_BSNM_REGIST_NO)}">
		<input type="hidden" id="P_GOODSNO" name="P_GOODSNO" value="${comFn:nvl(goodsDetail.GOODSNO, param.P_GOODSNO)}">
		<input type="hidden" id="P_REQST_NO" name="P_REQST_NO" value="${param.P_REQST_NO }">
		
		<div class="pop_header">
			<div class="title">지원사업관리</div>
		</div> <!--// pop_header E -->
		
		<!-- tab -->
		<div class="component-tab-line">
			<ul class="list-tab">
				<li>
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(1);">기업정보</a>
				</li>
				<li>
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(2);">상품정보</a>
				</li>
				<li class="is-selected">
					<a href="javascript:" onclick="pageObj.objImstarsTabEvent(3);">첨부파일</a>
				</li>
			</ul>
		</div>
		<!-- //tab -->
		
		<br><br><br>
		
		<div>
			<!-- Top -->
			<div class="top-detail" style="display: none;">
				<div class="type-fleft">
					<div class="contents-label">첨부파일</div>
				</div>
			</div>
			<!-- //Top -->
			<table class="component-detail-table">
				<colgroup>
					<col width="8%">
					<col width="30%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>파일명</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty fileDetailInfo }">
	                  	<c:forEach items="${fileDetailInfo}" var="data" varStatus="status">	
                  			<tr>
                  				<td class="txt-center">${status.count }</td>
								<td>${data.FILE_NM }</td>
								<td><a href="javascript:imstarsDownload('${data.FILE_ID}', '${data.FILE_SN }');">${data.WON_FILE_NM }</a></td>
                  			</tr>
		                </c:forEach>
                   	</c:if>
                   	
                   	<c:if test="${ empty fileDetailInfo }">
                   		<tr>
                   			<td colspan="3" class="txt-center">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
                   		</tr>
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
	<input type="hidden" name="P_FILE_ID">
	<input type="hidden" name="P_FILE_SN">
</form>