<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_yearList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/yearList.js"></script>  

<div id="windowPopup" class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">연간발주계획 목록</h1>
	</div> <!--// pop_header E -->
	<!-- <h3 class="windowTitle">연간발주계획 목록</h3> -->
	<div class="pop_container">
		<form id="searchFrm" class="search_form" method="POST" >
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<%-- <input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }"> --%>
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="P_YYYY" name="P_YYYY" value="${param.P_YYYY}">
			<input type="hidden" id="P_CTRCT_SE_CD" name="P_CTRCT_SE_CD" value="${param.P_CTRCT_SE_CD}">
			<input type="hidden" id="P_MY_AUTHOR_CD" value="${ sessionScope.loginResult.AUTHOR_CD}">
			<input type="hidden" id="P_ORDER_COMPT_AT" name="P_ORDER_COMPT_AT" value = "N"/>
			<input type="hidden" id="P_CNTRCT_BPLC_CD" name="P_CNTRCT_BPLC_CD" value="${sessionScope.loginResult.BRFFC_CD }"/>
			<input type="hidden" id="P_TODAY" value="${P_TODAY }">
			<input type="hidden" id="P_TODAY_FOM" value="${P_TODAY_FOM }">
		<!-- <br/><br/> --> &nbsp;
		<div class="view_wrap typeC">
			<div class="view_area m0 typeB">
			<%-- <table class="contable2">
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
												
												발주계획명
											</td>
											
												<input type="text" class="lineTxt" id="P_ORDER_PLAN_NM" name="P_ORDER_PLAN_NM" style="width: 80%;" value="${param.P_ORDER_PLAN_NM}" maxlength="100">
											</td>
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
			</table> --%>
			<table>
				<colgroup>
					<col style="width: 15%;">
					<col style="width: auto;">
				</colgroup>
				<tr>
					<th>발주계획명</th>
					<td>
						<input type="text" name="P_ORDER_PLAN_NM" id="P_ORDER_PLAN_NM" value="${param.P_ORDER_PLAN_NM}" maxlength="600">
					</td>
				</tr>
			</table>
			</div>
		</div>
		</form>
		
		<div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn"><img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회</button>
		</div> <!--// btn_wrap E -->
           
	    <!-- Data List -->
		<div class="pop_list_wrap"> 
			<div class="pop_list_conts">
			<table>
	            <caption>발주계획 목록</caption>
	            <colgroup>
	                <%-- <col width="5%"/> --%>
                   <col width="200px">
	                <col width="80px">
	                <col width="80px">
	                <%-- <col width="80px">
	                <col width="100px"> --%>
	                <c:if test="${param.P_CTRCT_SE_CD eq '0'}">
	                	<col width="80px">
	                	<col width="180px">
	                </c:if>
	                <c:if test="${param.P_CTRCT_SE_CD eq '1'}">
	                	<col width="80px">
	                	<col width="180px">
	                </c:if> 
	                <c:if test="${param.P_CTRCT_SE_CD eq '2'}">
	                	<col width="80px">
	                	<col width="60px">
	                	<col width="100px">
	                	<col width="100px">
	                	<col width="100px">
	                	<col width="100px">
	                </c:if>
	            </colgroup>			
				<thead>
	                <tr>
			            <th>발주계획명</th>
			            <th>발주시기</th>
			            <th>계약방법</th>
			            <c:if test="${param.P_CTRCT_SE_CD eq '0'}">
							<th scope="col">물품수량</th>                    	
		                    <th scope="col">물품예산금액</th>
	                    </c:if>
	                    <c:if test="${param.P_CTRCT_SE_CD eq '1'}">
		                	<th scope="col">용역구분</th>                    	
		                    <th scope="col">예산금액</th>
		                </c:if>
		                <c:if test="${param.P_CTRCT_SE_CD eq '2'}">
		                	<th scope="col">업무유형</th>                    	
		                    <th scope="col">공종</th>
		                    <th scope="col">발주도급금액</th>                    	
		                    <th scope="col">발주관급자재비</th>
		                    <th scope="col">발주기타금액</th>
		                    <th scope="col">발주합계금액</th>
		                </c:if>
	                </tr>
	            </thead>
				<tbody>
					<c:if test="${ empty orderPlanList}">
						<tr>
							<c:if test="${param.P_CTRCT_SE_CD eq '0'}">
								<td colspan="5" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</c:if>
							<c:if test="${param.P_CTRCT_SE_CD eq '1'}">
								<td colspan="5" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</c:if>
							<c:if test="${param.P_CTRCT_SE_CD eq '2'}">
								<td colspan="9" style="text-align: center;">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
							</c:if>
						</tr>
					</c:if>
					<c:if test="${not empty orderPlanList}">
						<c:forEach var="data" items="${orderPlanList}" varStatus="status" >
							<tr class="row" onclick="setOrderPlanInfo('${data.ORPL_NO}', '${data.PRCURE_MTHD }','${data.ORDER_PLAN_NM}','${data.ORDER_GVSL_AMOUNT}','${data.PURCHS_PLNPRC}','${data.ORDER_OUTSRC_AMOUNT }','${data.ORPR_ERA_YM }','${comFn:formatDate(data.ORPR_ERA_YM,'yyyyMM','yyyy-MM')}','${data.BSNS_NM}')" style="cursor: pointer;">
								<%-- <td>${data.RNUM}</td> --%>
								<%-- <td>${data.CNTRCT_BPLC_NM}</td> --%> 
								<c:if test="${param.P_CTRCT_SE_CD eq '0'}"> 
									<td style="text-align: left;" title="${data.ORDER_PLAN_NM}">${comFn:toShorten(data.ORDER_PLAN_NM, 80)}</td>
									<td>${comFn:formatDate(data.ORPR_ERA_YM,'yyyyMM','yyyy-MM')}</td>
									<td>${data.CNTRCT_MTH_NM}</td>
									<td style="text-align: right;">${comFn:formatMoney(data.THNG_QY)}</td>
									<td style="text-align: right;">${comFn:formatMoney(data.PURCHS_PLNPRC)}원</td>
								</c:if>
								<c:if test="${param.P_CTRCT_SE_CD eq '1'}">
									<td style="text-align: left;" title="${data.ORDER_PLAN_NM}">${comFn:toShorten(data.ORDER_PLAN_NM, 80)}</td>
									<td>${comFn:formatDate(data.ORPR_ERA_YM,'yyyyMM','yyyy-MM')}</td>
									<td>${data.CNTRCT_MTH_NM}</td>
									<td>${data.SERVC_SE_NM}</td>
									<td style="text-align: right;">${comFn:formatMoney(data.PURCHS_PLNPRC)}원</td>
								</c:if>
								<c:if test="${param.P_CTRCT_SE_CD eq '2'}">
									<td style="text-align: left;" title="${data.ORDER_PLAN_NM}">${comFn:toShorten(data.ORDER_PLAN_NM, 40)}</td>
									<td>${comFn:formatDate(data.ORPR_ERA_YM,'yyyyMM','yyyy-MM')}</td>
									<td>${data.CNTRCT_MTH_NM}</td>
									<td>${data.JOB_TY_NM}</td>
									<td>${data.CNTRWK_KND_NM}</td>
									<td  style="text-align: right;">${comFn:formatMoney(data.ORDER_OUTSRC_AMOUNT)}원</td>
									<td  style="text-align: right;">${comFn:formatMoney(data.ORDER_GVSL_AMOUNT)}원</td>
									<td  style="text-align: right;">${comFn:formatMoney(data.ORDER_ETC_AMOUNT)}원</td>
									<td  style="text-align: right;">${comFn:formatMoney(data.PURCHS_PLNPRC)}원</td>
								</c:if>
								<%-- <td>${data.ORDER_COMPT_NM}</td> --%>
							</tr>
						</c:forEach>
					</c:if>
				</tbody> 
			</table>
			</div>
		</div> 
		<br>
		<div class="pop_list_bottom">
    		<div class="btn_wrap view_btn">
    		<c:if test="${param.setMulti eq 'Y'}">
        		<button type="button" class="btn btn_02 btn_revise"  id="choiceBtn">선택</button>
        	</c:if>
        	<button type="button" style="width: 90px;" class="btn btn_02 btn_revise" id="moveOrderPlanBtn">발주계획등록</button>
            <button type="button" class="btn btn_02 btn_close" id="closeBtn">닫기</button>
		   		<%-- <c:if test="${param.setMulti eq 'Y'}">
	        		<button type="button" class="btn btn_02 btn_revise" id="choiceBtn">선택</button>
	        	</c:if>
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button> --%>
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
