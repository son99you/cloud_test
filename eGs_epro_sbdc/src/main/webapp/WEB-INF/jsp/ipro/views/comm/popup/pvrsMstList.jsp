<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 수의계약사유(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_pvrsMstList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/pvrsMstList.js"></script>
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">수의계약사유</h1>
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
							<col width="20%" align="left">
							<col width="80%" align="left">
						</colgroup>
						<tr>
							<td>수의계약사유</td>
							<td>
								<input type="text" size="20" maxlength="600" id="P_PVCT_RSNM_S" name="P_PVCT_RSNM_S" value="${param.P_PVCT_RSNM_S}">
							</td>
						</tr>
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
		</form>
		
		<div class="btn_wrap mt10">
			<button type="button" class="btn btn_m btn_c2" id="searchBtn">조회</button>
		</div> <!--// btn_wrap E -->
		
		<!-- Data Total Count -->
	    <div class="pop_list_wrap">
			<div class="pop_list_top">
	        	<p class="list_count">총 <span>${comFn:nvl(pvrsMstListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>수의계약사유 목록</caption>
		            <colgroup>
		                <col width="5%">
		                <col width="15%">
		                <col width="80%">
		            </colgroup>			
					<thead>
		                <tr>
		                	<th class="noBg">선택</th>
		                    <th>수의계약공사규정</th>
		                    <th>수의계약공사규정내용</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(pvrsMstListTotcnt, 0) == 0}">
							<tr>
								<td colspan="3" class="txtc">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${pvrsMstListTotcnt > 0}">
							<c:forEach var="data" items="${pvrsMstList}" varStatus="status" >
								<tr class="row" style="cursor: pointer;">
									<td class="txtc">
										<label for="chargerCbx${status.count }" class="blind">체크박스</label>
										<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
										<input type="hidden" name="P_PVCT_RSN_NO" value="${data.PVCT_RSN_NO}">
									</td>
									<td>
										${data.PVCT_RSN_NO}
									</td>
									<td class="pl5 txtl">
										${data.PVCT_RSNM}
										<input type="hidden" name="P_PVCT_RSNM" value="${data.PVCT_RSNM}"> 
										<input type="hidden" name="P_PVCT_RSN_SECD" value="${data.PVCT_RSN_SECD}"> 
									</td>
								</tr>
							</c:forEach>
						</c:if>
					
					</tbody> 
				</table>
			</div> 
			<!-- Data Paging -->
			<div class="pop_list_bottom">
				<div class="pop_pager">
					<comTag:pagingIpro totalCount="${pvrsMstListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<c:if test="${param.setMulti eq 'Y'}"> 
        			<button type="button" class="btn btn_m btn_orange" id="choiceBtn">선택</button>
        		</c:if>
				<button type="button" class="btn btn_m btn_del" id="closeBtn" onclick="window.close();">닫기</button>
			</div>
		</div> <!--// list_wrap E -->
	</div> <!--// pop_container E -->

	
<%--page move form --%>
<form id="detailFrm" method="POST" >
	<input type="hidden" name="contextPath" value="${contextPath}" >
	<input type="hidden" name="P_NTT_SN" >
	<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}" >
	<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
	<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
</form>         
</div> <!--// pop_wrap E -->

