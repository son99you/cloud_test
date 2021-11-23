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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/vendChargerList.js"></script>
<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">업체담당자목록</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" >
		<br/><br/> &nbsp;
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
			<input type="hidden" id="P_VEND_REG_NO" name="P_VEND_REG_NO" value="${param.P_VEND_REG_NO}" >
			<!-- 값을 받는 방법이 TEXT인경우 td에 id로 적혀있는 경우 -->
			<input type="hidden" id="P_TEXT_GBN" name="P_TEXT_GBN" value="${param.P_TEXT_GBN}" >
			<div class="view_wrap typeC">
				<div class="view_area m0 typeB">
					<table>
						<colgroup>
							<col width="15%" align="left">
							<col width="85%" align="left">
							<col width="15%" align="left">
							<col width="35%" align="left">
						</colgroup>
						<tr height="29px">
							<td>
								담당자명
							</td>
							<td colspan="3">
								<input type="text" size="20" maxlength="600" id="P_USR_NM" name="P_USR_NM" value="${param.USR_NM}">
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
	        	<p class="list_count">총 <span>${comFn:nvl(entrpsInqireListTotcnt, 0)}</span>건</p>
	    	</div> <!--// pop list_top E -->
		    <!-- Data List -->
			<div class="pop_list_conts">
				<table>
		            <caption>업체 목록</caption>
		            <colgroup>
		                <col width="10%">
		                <col width="15%">
		                <col width="15%">
		                <col width="15%">
		                <col width="15%">
		                <col width="15%">
		                <col width="15%">
		            </colgroup>			
					<thead>
		                <tr>
							<th class="noBg">번호</th>
		                    <th>사업자번호</th>
		                    <th>업체명</th>
		                    <th>담당자명</th>
							<th>이메일</th>
							<th>휴대전화번호</th>
							<th>전화번호</th>
		                </tr>
		            </thead>
					<tbody>
						<c:if test="${comFn:nvl(entrpsInqireListTotcnt, 0) == 0}">
							<tr>
								<td colspan="7" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</tr>
						</c:if>
						<c:if test="${entrpsInqireListTotcnt > 0}">
							<c:forEach var="data" items="${entrpsInqireList}" varStatus="status" >
								<tr class="row" <c:if test="${param.setMulti ne 'Y'}">onclick="setEntrpsInfo('${data.VEND_REG_NO}','${data.BIZRNO }', '${data.VEND_NM}', '${data.RPRS_NM}', '${data.ADDR_NM}', '${data.BCNM}','${data.BTNM}','${data.TEL_NO}','${data.EMAL_ADDR}','${data.USR_NM}', '${data.RPRS_NM}', '${data.ADDR_NM}', '${data.ADDR_DENM}', '${data.CHRGR_USR_NM}', '${data.CHRGR_TEL_NO}', '${data.CHRGR_EMAL_ADDR}') "</c:if> style="cursor: pointer;">
									<td>${data.RNUM}&nbsp;</td>
									<td>
										${comFn:formatBizNumber(data.BIZRNO)}&nbsp;
										<input type="hidden" name="P_BIZRNO" value="${data.BIZRNO}"> 
									</td>
									<td class="txtl pl5"> 
										${data.VEND_NM}&nbsp; 
										<input type="hidden" name="P_VEND_NM" value="${data.VEND_NM}"> 
									</td>
									<td class="txtl pl5">
										${data.CHRGR_USR_NM}&nbsp;
										<input type="hidden" name="P_USR_NM" value="${data.CHRGR_USR_NM}"> 
									</td>
									<td> 
										${data.CHRGR_EMAL_ADDR}&nbsp;
										<input type="hidden" name="P_EMAL_ADDR" value="${data.CHRGR_EMAL_ADDR}">
									</td>
									<td> 
										${data.CHRGR_TEL_NO}&nbsp;
										<input type="hidden" name="P_TEL_NO" value="${data.CHRGR_TEL_NO}">
									</td>
									<td> 
										${data.CHRGR_CP_NO}&nbsp;
										<input type="hidden" name="P_CP_NO" value="${data.CHRGR_CP_NO}">
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
					<comTag:pagingIpro totalCount="${entrpsInqireListTotcnt}" pageNo="${comFn:nvl(param.P_PAGE_NO, 1)}" pageSize="${comFn:nvl(param.P_PAGE_SIZE, 10)}" clickPage="pageObj.clickPage"/>
				</div>
			</div> <!--// list_pager E -->
			<div class="btn_wrap view_btn">
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
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

