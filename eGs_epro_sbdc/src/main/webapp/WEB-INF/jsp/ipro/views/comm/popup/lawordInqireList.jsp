<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 요구부서 조회(팝업)
 *
 * <pre>
 * comm 
 *  |_popup
 *   |_rqstDeptList.jsp
 * 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="comTag" uri="/WEB-INF/tlds/comTag.tld" %>
<%@ taglib prefix="comFn" uri="/WEB-INF/tlds/comFunctionTag.tld" %>

<%-- <link rel="stylesheet" type="text/css" href="${cssPath}/ipro/windowPop.css">
<link rel="stylesheet" type="text/css" href="${cssPath}/ipro/template/pagingPlace.css"> --%>

  
<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/lawordInqireList.js"></script> 
 
<div id="" class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">근거법령 목록</h1>
	</div> <!--// pop_header E -->
	<!-- <h3 class="windowTitle">근거법령 목록</h3> -->
	<div class="pop_container"> 
		<form id="searchFrm" class="search_form" method="POST" >
			<!-- <br/><br/>  -->
			&nbsp;
			<input type="hidden" id="P_PAGE_NO" name="P_PAGE_NO" value="${comFn:nvl(param.P_PAGE_NO, 1)}">
			<input type="hidden" id="P_PAGE_SIZE" name="P_PAGE_SIZE" value="${comFn:nvl(param.P_PAGE_SIZE, 10)}">
			<input type="hidden" id="P_CTRTC" name="P_CTRTC" value="${param.P_CTRTC }">
			<input type="hidden" id="btnId" name="searchGbnId" value="${param.searchGbnId}">
			<input type="hidden" id="gbnDept" name="searchGbnDept" value="${param.searchGbnDept }">
			<input type="hidden" id="setMulti" name="setMulti" value="${param.setMulti}">
			<input type="hidden" id="P_CNTRCT_MTH_CD_S" name="P_CNTRCT_MTH_CD_S" value="${param.P_CNTRCT_MTH_CD_S}">
			<input type="hidden" id="LAWORD_CD_NM" name="LAWORD_CD_NM" value="">
			<input type="hidden" id="LAWORD_CD_DETAIL" name="LAWORD_CD_DETAIL" value="">
			
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
								
								근거법령
							</td>
							<td colspan="3">
								<input type="text" size="20" maxlength="600" id="P_LAWORD_CD_NM_S" name="P_LAWORD_CD_NM_S" value="${param.P_LAWORD_CD_NM_S}">
							</td>
							<%-- <td>
								사업자번호
							</td>
							<td>
								<input type="text" size="20" maxlength="10" id="P_BIZRNO_S" name="P_BIZRNO_S" value="${param.P_BIZRNO_S}">
							</td> --%>
						</tr>
					</table>
				</div> <!--// view_area E -->
			</div> <!--// view_wrap E -->
			
			
			<%-- <table class="contable2">
				<tr>
					<td>
						<table class="contable">
							<tr>
								<td>
									<table>
										<caption>근거법령 조회</caption>
										<colgroup>
											<col style="width: 20%;">
											<col style="width: 60%;">
											<col style="width: 20%;">
										</colgroup>
										<tr height="29px">
											<td>
												
												<label for="P_LAWORD_CD_NM_S">근거법령</label>
											</td>
											
												<input type="text" name="P_LAWORD_CD_NM_S" id="P_LAWORD_CD_NM_S" style="width: 90%;" value="${param.P_LAWORD_CD_NM_S}" maxlength="600">
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
		</form>
           
        <div class="btn_wrap mt10">
			<button type="button" class="btn_p btn_p1 btn_lookup" id="searchBtn">
				<img src="${imagePath}/ipro/icon/btn_icon02.png" alt="">조회
			</button>
		</div> <!--// btn_wrap E -->
		<!-- Data Total Count -->
	<div class="pop_list_wrap">
	    <div class="pop_list_top">
	        <p class="list_count">총 <span>${comFn:nvl(lawordInqireListTotcnt, 0)}</span>건</p>
	    </div>
	       
	    <!-- Data List -->
		<div class="pop_list_conts">
			<table>
	            <caption>근거법령 목록</caption>
	            <colgroup>
	                <col width="10%">
					<col width="45%">
					<col width="50%">
	            </colgroup>			
				<thead>
		            <tr>
						<th class="noBg">
							<label for="chargerAllCbx" class="blind">체크박스</label>
							<input type="checkbox" id="chargerAllCbx" name="chargerCbx" onclick="FwkCmmnUtil.setAllCheck('chargerAllCbx','chargerCbx');">
						</th>
						<th>근거법령</th>
						<th>근거법령상세</th>
					</tr>
	            </thead> 
				<tbody>
					<c:if test="${comFn:nvl(lawordInqireListTotcnt, 0) == 0}">
						<tr>
							<td colspan="3">검색 조건에 해당하는 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					<c:if test="${lawordInqireListTotcnt > 0}">
						<c:forEach var="data" items="${lawordInqireList}" varStatus="status">
							<tr class="row" style="cursor: pointer;">
								<td>
									<label for="chargerCbx${status.count }" class="blind">체크박스</label>
									<input type="checkbox" id="chargerCbx${status.count }" name="chargerCbx">
								</td>
								
								<td onclick="setLawordInfo('${data.LAWORD_CD}', '${data.LAWORD_CD_NM}', '${data.LAWORD_CD_DETAIL}');" style="text-align: left;">
									<input type="hidden" name="P_LAWORD_CD_NM" value="${data.LAWORD_CD_NM }">${data.LAWORD_CD_NM}&nbsp;
								</td>
								
								<td style="text-align: left;" onclick="setLawordInfo('${data.LAWORD_CD}', '${data.LAWORD_CD_NM}', '${data.LAWORD_CD_DETAIL}');">
									<input type="hidden" name="P_LAWORD_CD_DETAIL" value="${data.LAWORD_CD_DETAIL }">${data.LAWORD_CD_DETAIL}&nbsp;
								</td>
							</tr> 
						</c:forEach>
					</c:if>
				</tbody> 
			</table>  
			
		</div> 
		<br>
	    <div class="btn_wrap view_btn">
        	<button type="button" class="btn btn_02 btn_close" id="choiceBtn">선택</button>
			<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
	    </div>
	    <br>
	</div>
	</div> 
</div>

	<%--page move form --%> 
	<form id="detailFrm" method="POST">
		<input type="hidden" name="contextPath" value="${contextPath}">
		<input type="hidden" name="P_NTT_SN">
		<input type="hidden" name="P_LOGIN_ID" value="${loginResult.LOGIN_ID}">
		<input type="hidden" id="resourceName" name="resourceName" value="${param.resourceName}">
		<input type="hidden" id="resourceDesc" name="resourceDesc" value="${param.resourceDesc}">
	</form> 
