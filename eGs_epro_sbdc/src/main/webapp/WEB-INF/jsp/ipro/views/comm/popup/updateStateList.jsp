<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
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


<script type="text/javascript" src="${jsPath}/frwk/comUtil.js"></script>
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/updateStateList.js"></script> 
 
<div id="windowPopup" class="w_800"> 
	<h3 class="windowTitle">수정이력 조회</h3>
	<div class="formLayer"> 
           
		<%-- Data Total Count --%>
	    <div class="tableComment" style="padding-left: 5%; padding-right: 5%; width: 90%;">
	        <p class="list_count">총 <span>${updateStateListTotcnt}</span>건</p>
	    </div>	
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>수정이력 목록</caption>
	            <colgroup>
	                <col width="40px"/>
                   <col width="200px"/>
                   <col width="150px"/>
                   <col width="80px"/>
	            </colgroup>			
				<thead  class="line">
	                <tr>
	                    <th class="noBg" scope="col">No</th>
	                    <th scope="col">수정자</th>
	                    <th scope="col">수정일시</th>
	                    <th scope="col">처리이력</th>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${comFn:nvl(updateStateListTotcnt, 0) == 0}">
						<tr>
							<td colspan="4">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${updateStateListTotcnt > 0}">
						<c:forEach var="data" items="${updateStateList}" varStatus="status" >
							<tr class="row" >
								<td style="text-align : center">${data.RNUM}</td>
								<td style="text-align : center">${data.UPDUSR_NM}</td> 
								<td style="text-align : center">${comFn:formatDate(data.UPDT_DT, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss')}</td>
								<td style="text-align : center">${data.RM_CN}</td> 
							</tr> 
						</c:forEach>
					</c:if>
				</tbody> 
			</table>
			<div class="paging_place" style="padding-left: 5%; padding-right: 5%;">
				<button type="button" class="blueBtn L"  id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> 
	</div> 
		
	<%--page move form --%>
	<form id="detailFrm" method="POST" >
		<input type="hidden" name="contextPath" value="${contextPath}" >
		<input type="hidden" name="P_NTT_SN" >
		<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	</form>
</div>
