<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 업체팝업 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_entrpsList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css">

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/entrpsList.js"></script>

<div id="windowPopup" class="w_800">
	<h3 class="windowTitle">업체목록</h3>
	<div class="formLayer">
		<form id="searchFrm" class="search_form" method="POST" action="${contextPath}/cmmn/popup/entrpsInqireNotInList.do">
		<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
		<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
		<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
		<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
		<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
		<input type="hidden" id="over" name="over" value="${param.over}">
		<input type="hidden" id="P_ENTRPS_REGIST_NO" name="P_ENTRPS_REGIST_NO" value="${param.P_ENTRPS_REGIST_NO}">
		
		<input type="hidden" id="P_CNTRCT_NO" name="P_CNTRCT_NO" value="${P_CNTRCT_NO}">
		<input type="hidden" id="P_CHANGE_ODR" name="P_CHANGE_ODR" value="${P_CHANGE_ODR}">
			
		
			<table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<colgroup>
											<col width="15%" align="left">
											<col width="35%" align="left">
											<col width="15%" align="left">
											<col width="35%" align="left">
										</colgroup>
										<tr height="29px">
											<td>
												
												업체명
											</td>
											<td colspan="3"><input type="text" name="P_ENTRPS_NM_S" id="P_ENTRPS_NM_S" style="width: 90%;" value="${param.P_ENTRPS_NM_S}" maxlength="600"></td>
										</tr>
										<tr>
											<td class=" searchBtnTd" colspan="4"> 
												<button type="button" class="grayBtn ico" id="searchBtn">
								                    <img src="${imagePath}/ipro/icon/ico_search.png" alt="조회 버튼"> 조회
								                </button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
           
		<!-- Data Total Count -->
		<br />
	    <div class="tableComment">
	        <p class="list_count">총 <span>${comFn:nvl(entrpsInqireNotInListTotcnt, 0)}</span>건</p>
	    </div>
	       
	    <!-- Data List -->
		<div class="list">
			<table>
	            <caption>업체 목록</caption>
	            <colgroup>
	                <col width="50px">
					<col width="100px">
					<col width="150px">
					<col width="100px">
					<col width="200px">
	            </colgroup>			
				<thead>
	                <tr>
	                   <th class="noBg">번호</th>
						<th>사업자번호</th>
						<th>업체명</th>
						<th>대표자명</th>
						<th>업체주소</th>
						 <!-- <th>사업자번호</th>
	                    <th>업체명</th>
	                    <th>대표자명</th>
						 <th>업종명</th> -->
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${comFn:nvl(entrpsInqireNotInListTotcnt, 0) == 0}">
						<tr>
							<td colspan="5" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${entrpsInqireNotInListTotcnt > 0}">
						<c:forEach var="data" items="${entrpsInqireNotInList}" varStatus="status">
							<tr class="row" onclick="setEntrpsInfo('${data.ENTRPS_REGIST_NO}','${data.BIZRNO }', '${data.ENTRPS_NM}', '${data.RPRSNTV_NM}', '${data.ADDRESS}', '${data.BIZCND_NM}','${data.INDUTY_NM}','${data.TELNO}','${data.EMAIL}','${data.CHARGER_NM}');" style="cursor: pointer;">
								<td>${data.RNUM}&nbsp;</td>
								<td>${comFn:formatBizNumber(data.BIZRNO)}&nbsp;</td>
								<td>${data.ENTRPS_NM}&nbsp;</td>
								<td>${data.RPRSNTV_NM}&nbsp;</td> 
								<td>${data.ADDRESS}&nbsp;</td>
							</tr>
						</c:forEach> 
					</c:if>
				</tbody>  
			</table>
			
			<!-- Data Paging -->
			<div class="paging_place">
				<comTag:pagingIpro totalCount="${entrpsInqireNotInListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
			</div>
		</div> 
		<br>
	    <div class="T_btnLayer fr">
			<button type="button" class="blueBtn L" id="closeBtn" onclick="window.close();">닫기</button> 
	    </div>
	</div>
	
<%--page move form --%>
<form id="detailFrm" method="POST"> 
	<input type="hidden" name="contextPath" value="${contextPath}">
	<input type="hidden" name="P_NTT_SN">
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_USER_ID}">
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>     
</div>
