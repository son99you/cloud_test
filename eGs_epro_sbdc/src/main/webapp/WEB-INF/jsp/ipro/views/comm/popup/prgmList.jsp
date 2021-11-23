<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/prgmList.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">편성프로그램조회목록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="over" name="over" value="${param.over}"> <!-- 중복가능 -->
			<input type="hidden" id="orgP_ENTRPS_REGIST_NO" name="orgP_ENTRPS_REGIST_NO" value="${param.orgP_ENTRPS_REGIST_NO}" >
			<input type="hidden" id="CNTRCT_MTH_CD" name="CNTRCT_MTH_CD" value="${param.CNTRCT_MTH_CD}" >
			<input type="hidden" id="returnGbn" name="returnGbn" value="${param.returnGbn}" >
			<!-- 값을 받는 방법이 TEXT인경우 td에 id로 적혀있는 경우 -->
			<input type="hidden" id="P_TEXT_GBN" name="P_TEXT_GBN" value="${param.P_TEXT_GBN}" >
			
			<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="35%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr height="29px">
							<td>
								미디어명
							</td>
							<td>
								<div class="selectLayer2 w_120">
									<comTag:comCmcdCdValueComboBox id="P_MEDA_CODE" name="P_MEDA_CODE"  selectKey="${param.MEDA_CODE}" cdId="MEDA_CODE" headerKey="" headerValue="선택" />
								</div>
							</td>
							<td>
								편성프로그램명
							</td>
							<td>
								<input type="text" class="w_95p" size="20" maxlength="600" id="P_ORPG_NAME" name="P_ORPG_NAME" value="${param.P_ORPG_NAME}">
							</td>
						</tr>
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
		</form>
		
		<div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn">
				<img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회
			</button>
		</div> <!--// btn_wrap E -->
		
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="list_count">총 <span>${comFn:nvl(prgmListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>업체 목록</caption>
		            <colgroup>
		                <col width="9%">
		                <col width="15%">
		                <col width="64%">
		                <col width="12%">
		            </colgroup>			
					<thead>
		                <tr>
		                    <th>매체명</th>
		                    <th>편성프로그램코드</th>
		                    <th>편성프로그램명</th>
		                    <th>기준일자</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${empty prgmList}">
							<tr>
								<td colspan="2" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${not empty prgmList}">
						<c:forEach var="data" items="${prgmList}" varStatus="status" >
							<tr onclick="setPrgmInfo('${data.MEDA_CODE}', '${data.BASE_DATE}', '${data.PROG_CODE}', '${data.ORPG_CODE}', '${data.ORPG_NAME}', '${data.MEDA_CDNM}');" style="cursor:pointer;">
								<td>${data.MEDA_CDNM}</td>
								<td>${data.PROG_CODE}</td>
								<td>${data.ORPG_NAME}</td>
								<td>${comFn:formatDate(data.BASE_DATE,'yyyyMMdd','yyyy-MM-dd')}</td>
							</tr>
						</c:forEach>
						</c:if>
					</tbody> 
				</table>
			</div> 
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${prgmListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<c:if test="${param.setMulti eq 'Y'}"> 
        			<button type="button" class="btn btn_02 btn_revise"   id="choiceBtn">선택</button>
        		</c:if>
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->
</div> <!--// pop_wrap E -->

<%--page move form --%>
<form id="detailFrm" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_NTT_SN" >
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>

