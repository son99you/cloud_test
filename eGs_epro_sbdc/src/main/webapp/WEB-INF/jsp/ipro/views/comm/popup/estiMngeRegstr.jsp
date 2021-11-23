<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--
 * 공통 > 견적확인
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

<script type="text/javascript" src="${jsPath}/ipro/views/comm/popup/estiMngeRegstr.js"></script>

<div class="pop_wrap">
	<div class="pop_header">
		<h1 class="tit">견적확인</h1>
	</div> <!--// pop_header E -->
	<div class="pop_container">
		<div class="view_wrap typeC">
			<div class="tit_area">
					<h2 class="tit01">견적제출 1차</h2>
				</div> <!--// tit_area E -->
			<div class="view_area">
				<table>
					<colgroup>
						<col width="15%" align="left">
						<col width="35%" align="left">
						<col width="15%" align="left">
						<col width="35%" align="left">
					</colgroup>
					<tr height="29px">
						<th>
							담당자
						</th>
						<td>
							홍길동
						</td>
						<th>
							
							담당자연락처
						</th>
						<td>
							Tel(010-4578-8475)<br> Mail(hci1135@naver.com)
						</td>
					</tr>
					<tr height="29px">
						<th>
							
							견적금액
						</th>
						<td>
							15,000,000원
						</td>
						<th>
							견적제출일시
						</th>
						<td>
							2018-08-30 17:04:11
						</td>
					</tr>
					<tr height="29px">
						<th>
							
							견적파일
						</th>
						<td colspan="3">
							<span style="" >견적제출.docx</span>
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${estiMngeRegistDetail.CNTRCT_SE_CD eq 0 }">
			<div class="view_area typeA">
				<table>
		            <caption>물품 목록</caption>
		            <colgroup>
		                <col width="25%">
		                <col width="*">
		                <col width="10%">
		                <col width="10%">
		                <col width="15%">
		                <col width="15%">
		            </colgroup>			
					<thead>
		                <tr>
		                   <th class=" txtc">품명</th>
		                    <th class=" txtc">규격</th>
		                    <th class=" txtc">단위</th>
		                    <th class=" txtc">수량</th>
		                    <th class=" txtc">단가</th>
		                    <th class=" txtc">금액</th>
		                </tr>
		            </thead>
					<tbody>
						<c:forEach var="data" items="${selectTEsVendItemDetail}" varStatus="status" >
							<tr>
								<td  style="text-align: center;">${data.ITEM_NM }</td>
								<td  style="text-align: center;">${data.STND_NM }</td>
								<td  style="text-align: center;">${data.UNIT_CD }</td>
								<td  style="text-align: right;">${comFn:formatMoneyDp(data.QTY) }</td>
								<td  style="text-align: right;">${comFn:formatMoney(data.UPRC) }</td>
								<td  style="text-align: right;">${comFn:formatMoney(data.AMT) }</td>
							</tr>
						</c:forEach>
					</tbody> 
				</table>
			</div>
			</c:if>	
			<div class="tit_area">
					<h2 class="tit01">견적제출 2차</h2>
				</div> <!--// tit_area E -->
			<div class="view_area">
				<table>
					<colgroup>
						<col width="15%" align="left">
						<col width="35%" align="left">
						<col width="15%" align="left">
						<col width="35%" align="left">
					</colgroup>
					<tr height="29px">
						<th>
							담당자
						</th>
						<td>
							홍길동
						</td>
						<th>
							
							담당자연락처
						</th>
						<td>
							Tel(010-4578-8475) <br>Mail(hci1135@naver.com)
						</td>
					</tr>
					<tr height="29px">
						<th>
							
							견적금액
						</th>
						<td>
							15,000,000원
						</td>
						<th>
							견적제출일시
						</th>
						<td>
							2018-08-30 17:04:11
						</td>
					</tr>
					<tr height="29px">
						<th>
							
							견적파일
						</th>
						<td colspan="3">
							<span style="" >견적제출.docx</span>
						</td>
					</tr>
				</table>
			</div>
			<c:if test="${estiMngeRegistDetail.CNTRCT_SE_CD eq 0 }">
			<div class="view_area typeA">
				<table>
		            <caption>물품 목록</caption>
		            <colgroup>
		                <col width="25%">
		                <col width="*">
		                <col width="10%">
		                <col width="10%">
		                <col width="15%">
		                <col width="15%">
		            </colgroup>			
					<thead>
		                <tr>
		                   <th class=" txtc">품명</th>
		                    <th class=" txtc">규격</th>
		                    <th class=" txtc">단위</th>
		                    <th class=" txtc">수량</th>
		                    <th class=" txtc">단가</th>
		                    <th class=" txtc">금액</th>
		                </tr>
		            </thead>
					<tbody>
						<c:forEach var="data" items="${selectTEsVendItemDetail}" varStatus="status" >
							<tr>
								<td  style="text-align: center;">${data.ITEM_NM }</td>
								<td  style="text-align: center;">${data.STND_NM }</td>
								<td  style="text-align: center;">${data.UNIT_CD }</td>
								<td  style="text-align: right;">${comFn:formatMoneyDp(data.QTY) }</td>
								<td  style="text-align: right;">${comFn:formatMoney(data.UPRC) }</td>
								<td  style="text-align: right;">${comFn:formatMoney(data.AMT) }</td>
							</tr>
						</c:forEach>
					</tbody> 
				</table>
			</div>
			</c:if>	
			<br>
			<br>	
	    	<div class="btn_wrap view_btn" style="margin-top: 5px;">
				<button type="button" class="btn btn_02 btn_close" id="closeBtn" onclick="window.close();">닫기</button>
	    	</div>
		</div> 
	</div>
</div>

	<form id="downloadFrm" method="POST" action="">
   		<input type="hidden" id="P_ATCHMNFL_SN" name="P_ATCHMNFL_SN" value="">
   	</form>